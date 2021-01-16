package com.arvin.feifeiyu.win.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.arvin.feifeiyu.win.util.FastJSONUtils;
import com.arvin.feifeiyu.win.util.StringUtils;

public class DefaultJson implements Serializable{

	static private DefaultJson globalInstance;
	
	private List<Platforms> platforms;
	private Application application;
	
	public static  DefaultJson init() {
        if (globalInstance == null) {
            globalInstance = buildDeviceConfig();
        }
        return globalInstance;
    }
    
    private static DefaultJson buildDeviceConfig() {
        String modelJSONConfig = getConfigJSONByName();
        DefaultJson defaultJson = new DefaultJson();
        if (StringUtils.isNotBlank(modelJSONConfig)) {
        	defaultJson = FastJSONUtils.parseObject(modelJSONConfig, DefaultJson.class);
        }
        return defaultJson;
    }
    
    private static String getConfigJSONByName() {
    	String path = "res/json/defaultJson.json";
        String jsonStr = "";
        try {
            File jsonFile = new File(path);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
	public DefaultJson() {
		
	}

   public void setPlatforms(List<Platforms> platforms) {
        this.platforms = platforms;
    }
    public List<Platforms> getPlatforms() {
        return platforms;
    }

   public void setApplication(Application application) {
        this.application = application;
    }
    public Application getApplication() {
        return application;
    }
}