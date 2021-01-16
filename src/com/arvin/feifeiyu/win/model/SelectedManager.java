package com.arvin.feifeiyu.win.model;

import com.arvin.feifeiyu.win.json.DefaultJson;
import com.arvin.feifeiyu.win.json.Platforms;

public class SelectedManager {
	
	public static final String SYSTEM = "system";
	public static final String FRAMEWORK = "framework";
	public static final String PRIV_APP = "priv-app";
	public static final String APP = "app";
	public static final String ONYX = "onyx";
	
	public static final String DEBUG = "debug";
	public static final String RELEASE = "release";
			
	private static SelectedManager selectedManager;
	private String platformDir;
	private String models;
	
	private String applicationType;
	private String systemPrivate;
	private String applicationName;
	
	private String sourcePath;
	private String app;
	private String confusion;
	
	private String devices;
	
	private String targetPath;
	private String packagesString;
	
	public static SelectedManager getSelectedManager() {
		if (selectedManager == null) {
			selectedManager = new SelectedManager();
			DefaultJson defaultJson = DefaultJson.init();
		}
		return selectedManager;
	}
	
	public SelectedManager() {
		
	}	
	
	public String getPlatformDir() {
		return platformDir;
	}

	public void setPlatformDir(String platformDir) {
		System.out.println("setPlatformDir: " + platformDir);
		this.platformDir = platformDir;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getSystemPrivate() {
		return systemPrivate;
	}

	public void setSystemPrivate(String systemPrivate) {
		System.out.println("---setSystemPrivate: " + systemPrivate);
		this.systemPrivate = systemPrivate;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	
	public String getSourcePath() {
		return this.sourcePath;
	}

	public String getTargetPath() {
		return targetPath;
	}

	public void setTargetPath(String targetPath) {
		System.out.println("setTargetPath: " + targetPath);
		this.targetPath = targetPath;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getConfusion() {
		return confusion;
	}

	public void setConfusion(String confusion) {
		this.confusion = confusion;
	}

	public String getDevices() {
		return devices;
	}

	public void setDevices(String devices) {
		this.devices = devices;
	}

	public String getPackagesString() {
		return packagesString;
	}

	public void setPackagesString(String packagesString) {
		this.packagesString = packagesString;
	}

	public String getLocalPath() {

		StringBuilder stringBuilder = new StringBuilder();
		if (getApplicationType().equals(SYSTEM)) {
			stringBuilder.append(getPlatformDir()).append(getModels()).append(getApplicationType()).append("\\");
			stringBuilder.append(getSystemPrivate());
			if (getSystemPrivate().equals(FRAMEWORK)) {
				
			} else if (getSystemPrivate().equals(PRIV_APP)
					||getSystemPrivate().equals(APP)) {
				stringBuilder.append("\\");
				stringBuilder.append(getApplicationName());
			} 
		} else if (getApplicationType().equals(ONYX)) {
			String path = getSourcePath().replace("**", getApp());
			stringBuilder.append(path).append(getConfusion()).append("\\").append(getApp()).append("-").append(getConfusion()).append(".apk");
		}
		
		return stringBuilder.toString();
	}

}
