package com.arvin.feifeiyu.win.util;

import java.util.ArrayList;

import com.arvin.feifeiyu.win.listener.CommandListener;
import com.arvin.feifeiyu.win.model.CommandLineProgram;
import com.arvin.feifeiyu.win.model.SelectedManager;

public class CommandUtils {
	
	public static final String ADB_HOME = "D:\\JAVA\\Android\\platform-tools\\";
	
	public static void pushCommand(String localPath, String currectPath, CommandListener listener) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(" push ");
		stringBuilder.append(localPath);
		stringBuilder.append(" ");
		stringBuilder.append(currectPath);
		commandUpdateMessage(stringBuilder.toString(), listener);
	}
	
	public static void commandUpdateMessage(String command, CommandListener listener) {
		System.out.println("-----commandUpdateMessage: " + command);
		CommandUtils.commandUpdateMessage(command, SelectedManager.getSelectedManager().getDevices(), listener);
	}
	
	public static void commandUpdateMessage(String value, String device, CommandListener listener) {
		String adb = getAdb(device);
		StringBuffer stringbuffer = new StringBuffer(adb).append(" ").append(value);
		String command = stringbuffer.toString();
		
		if (StringUtils.isNullOrEmpty(command)) {
			listener.onLineListener(CommandLineProgram.ERROR, "command is null");
			return;
		}
		command(command, listener);
	}

	private static String getAdb(String device) {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append(ADB_HOME).append("adb");
		if (device != null && device.length() > 0) {
			stringbuffer.append(" -s ").append(device).append(" ");
		}
		return stringbuffer.toString();
	}

	private static void command(String command, CommandListener listener) {
		if (command == null || command.length() == 0) {
			return;
		}
		CommandLineProgram commandLineProgram = new CommandLineProgram();
		commandLineProgram.run(command, listener);
	}

	private static void command(String command) {
		command(command, new CommandListener() {

			@Override
			public void onLineListener(String type, String message) {
				if (type.equals(CommandLineProgram.STDOUT)) {
				} else if (type.equals(CommandLineProgram.ERROR)) {
				} else if (type.equals(CommandLineProgram.FINISH)) {
					
				}

			}
		});

	}
}
