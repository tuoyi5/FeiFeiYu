package com.arvin.feifeiyu.win.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.crypto.spec.DHPrivateKeySpec;

import com.arvin.feifeiyu.win.listener.CommandListener;

public class CommandLineProgram {
	public static final String ERROR = "error";
	public static final String STDOUT = "stdout";
	public static final String FINISH = "finish";
	
	public String run(String command, CommandListener listener) {
		System.out.println("run: command: " + command);
		Process process;
		try {
			process = Runtime.getRuntime().exec(command);
			StreamThread errorStreamThread = new StreamThread();
			errorStreamThread.setInputStream(process.getErrorStream()).setType(ERROR).setCommandListener(listener);
			errorStreamThread.start();
			
            StreamThread outStreamThread = new StreamThread();
            outStreamThread.setInputStream(process.getInputStream()).setType(STDOUT).setCommandListener(listener);
            outStreamThread.start();
            
    	} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			return null;
		}
	}

	public class StreamThread extends Thread {
		InputStream inputStream;
		String type;
		CommandListener commandListener;

		public StreamThread() {
			
		}
		
		public InputStream getInputStream() {
			return inputStream;
		}

		public StreamThread setCommandListener(CommandListener commandListener) {
			this.commandListener = commandListener;
			return this;
		}

		public StreamThread setInputStream(InputStream inputStream) {
			this.inputStream = inputStream;
			return this;
		}

		public String getType() {
			return type;
		}

		public StreamThread setType(String type) {
			this.type = type;
			return this;
		}

		@Override
    	public void run() {
    		  InputStreamReader isr = null;
    		  BufferedReader br = null;
    		   try {
					isr = new InputStreamReader(inputStream, "GBK");
					br = new BufferedReader(isr);
					String line = null;
					StringBuilder stringBuilder = new StringBuilder();
					while ( (line = br.readLine()) != null)  {
    	                System.out.println("print >>>>>> " + type + " : " + line);
    	                stringBuilder.append(line);
    	                if (type.equals(STDOUT) && commandListener != null) {
    	                	commandListener.onLineListener(type, line);
    	                }
    	            }
					if (type.equals(ERROR) && commandListener != null) {
						commandListener.onLineListener(type, stringBuilder.toString());
    	            }
					
    	        } catch (IOException io) {
    	            io.printStackTrace();
    	        } catch (Exception e) {
    	        	e.printStackTrace();
    	        } finally {
    	            try {
    	                br.close();
    	                isr.close();
    	            } catch (IOException ex) {
    	                
    	            }
    	        }
    		   if (commandListener != null) {
					commandListener.onLineListener(FINISH, null);
	            }
    	}
	}

}
