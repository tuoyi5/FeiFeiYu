package com.arvin.feifeiyu.win.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.felix.resolver.util.ArrayMap;
import org.eclipse.ui.internal.progress.FinishedJobs.KeptJobsListener;
import org.osgi.framework.launch.Framework;

public class AppPathManager {
	private static String SDM_PATH = "android\\out\\target\\product\\";
	private static String RK_PATH = "out\\target\\product\\";
	private static String ONYX_EREADER = "C:\\WorkSpac\\onyx\\kepler\\android\\kreader\\app\\ereader\\";
	private static String ONYX_COMMON = "C:\\WorkSpac\\onyx\\kepler\\android\\kreader\\app\\common\\";
	private static String ONYX_SAMPLE = "C:\\WorkSpac\\onyx\\OnyxAndroidSample\\app\\";
	private static String OLD_ALREADER = "C:\\\\WorkSpac\\onyx\\alreader\\";
	
	private static AppPathManager appPathManager;
	public static AppPathManager getAppPathManager() {
		if (appPathManager == null) {
			appPathManager = new AppPathManager();
		}
		
		return appPathManager;
	}

	public AppPathManager() {
		initPlatformMap();
		initDeviceMap();
		initApplication();
	}
	public HashMap<String, Platform> platformMap = new HashMap<>();
	public HashMap<String, Devices> deviceMap = new HashMap<>();
	public HashMap<String, ApplicationType> applicationTypeMap = new HashMap<>();
	public HashMap<String, SystemPrivate> systemPrivateMap = new HashMap<>();
	public HashMap<String, ApplicationName> applicationNameMap = new HashMap<>();

	public void initPlatformMap() {
		platformMap.put("RK_3128", new Platform("RK_3128", "Z:\\3128_android_rom\\"));
		platformMap.put("RK_3288", new Platform("RK_3288", "Z:\\3288_android_rom\\"));
		platformMap.put("SDM_8953", new Platform("SDM_8953", "Z:\\msm8953_android_rom\\android\\"));
		platformMap.put("SDM_636", new Platform("SDM_636", "Z:\\sdm636_android_rom\\android\\"));
	}

	public void initDeviceMap() {
		deviceMap.put("MC_C68CTM", new Devices("MC_C68CTM", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_C68PCTM", new Devices("MC_C68CTM", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_DARWIN6", new Devices("MC_DARWIN6", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_DARWIN7", new Devices("MC_DARWIN7", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_Caesar3", new Devices("MC_Caesar3", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_Caesar4", new Devices("MC_Caesar4", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_Cristo4", new Devices("MC_Cristo4", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_Cristo5", new Devices("MC_Cristo5", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_FAUST", new Devices("MC_FAUST", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_FAUST2", new Devices("MC_FAUST2", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_Viking", new Devices("MC_Viking", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("LIVINGSTONE", new Devices("LIVINGSTONE", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_SILVER", new Devices("MC_SILVER", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("VOLTA", new Devices("VOLTA", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("VOLTA2", new Devices("VOLTA2", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MyFirstBook", new Devices("MyFirstBook", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_C68", new Devices("MC_C68", platformMap.get("RK_3128"), RK_PATH));
		deviceMap.put("MC_C68S", new Devices("MC_C68S", platformMap.get("RK_3128"), RK_PATH));

		deviceMap.put("MC_EUCLID", new Devices("MC_EUCLID", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_EUCLID_S", new Devices("MC_EUCLID_S", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_GULLIVER", new Devices("MC_GULLIVER", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_Max2", new Devices("MC_Max2", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_Max2Pro", new Devices("MC_Max2Pro", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_Note", new Devices("MC_Note", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_NotePro", new Devices("MC_NotePro", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_NoteS", new Devices("MC_NoteS", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_NoteSL", new Devices("MC_NoteSL", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_NovaPlus", new Devices("MC_NovaPlus", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_NovaPro", new Devices("MC_NovaPro", platformMap.get("RK_3288"), RK_PATH));
		deviceMap.put("MC_THOR", new Devices("MC_THOR", platformMap.get("RK_3288"), RK_PATH));

		deviceMap.put("Poke2Color", new Devices("Poke2Color", platformMap.get("SDM_8953"), SDM_PATH));
		deviceMap.put("KON_TIKI", new Devices("KON_TIKI", platformMap.get("SDM_8953"), SDM_PATH));

		deviceMap.put("Kon_Tiki2", new Devices("Kon_Tiki2", platformMap.get("SDM_636"), SDM_PATH));
		deviceMap.put("Lomonosov", new Devices("Lomonosov", platformMap.get("SDM_636"), SDM_PATH));
	}

	public void initApplication() {
		applicationTypeMap.put("framework", new ApplicationType("framework"));
		applicationTypeMap.put("system", new ApplicationType("system"));
		applicationTypeMap.put("onyx", new ApplicationType("onyx"));

		systemPrivateMap.put("privApp", new SystemPrivate("privApp", applicationTypeMap.get("system")));
		systemPrivateMap.put("app", new SystemPrivate("app", applicationTypeMap.get("system")));
		
		systemPrivateMap.put("onyx", new SystemPrivate("onyx", applicationTypeMap.get("onyx")));

		applicationNameMap.put("SystemUI", new ApplicationName("SystemUI", systemPrivateMap.get("privApp"), null));
		applicationNameMap.put("SettingsProvider",
				new ApplicationName("SettingsProvider", systemPrivateMap.get("privApp"), null));
		applicationNameMap.put("Settings", new ApplicationName("Settings", systemPrivateMap.get("privApp"), null));

		applicationNameMap.put("Email", new ApplicationName("Email", systemPrivateMap.get("privApp"), null));
		applicationNameMap.put("SoundRecorder",
				new ApplicationName("SoundRecorder", systemPrivateMap.get("privApp"), null));
		applicationNameMap.put("Browser", new ApplicationName("Browser", systemPrivateMap.get("privApp"), null));
		applicationNameMap.put("Bluetooth", new ApplicationName("Bluetooth", systemPrivateMap.get("privApp"), null));
		applicationNameMap.put("Music", new ApplicationName("Music", systemPrivateMap.get("privApp"), null));
		applicationNameMap.put("DownloadProviderUi",
				new ApplicationName("DownloadProviderUi", systemPrivateMap.get("privApp"), null));
		applicationNameMap.put("SystemPrivate",
				new ApplicationName("SystemPrivate", systemPrivateMap.get("privApp"), null));

		applicationNameMap.put("kcb", new ApplicationName("kcb", systemPrivateMap.get("onyx"), ONYX_EREADER));
		applicationNameMap.put("kreader2", new ApplicationName("kreader2", systemPrivateMap.get("onyx"), ONYX_EREADER));
		applicationNameMap.put("knote2", new ApplicationName("knote2", systemPrivateMap.get("onyx"), ONYX_EREADER));
		applicationNameMap.put("floatingbutton", new ApplicationName("floatingbutton", systemPrivateMap.get("onyx"), ONYX_EREADER));
		applicationNameMap.put("app-market", new ApplicationName("app-market", systemPrivateMap.get("onyx"), ONYX_EREADER));

		applicationNameMap.put("OnyxIME", new ApplicationName("OnyxIME", systemPrivateMap.get("onyx"), ONYX_COMMON));
		applicationNameMap.put("dict", new ApplicationName("dict", systemPrivateMap.get("onyx"), ONYX_COMMON));
		applicationNameMap.put("monitor", new ApplicationName("monitor", systemPrivateMap.get("onyx"), ONYX_COMMON));
		applicationNameMap.put("ProductionTest", new ApplicationName("ProductionTest", systemPrivateMap.get("onyx"), ONYX_COMMON));

		applicationNameMap.put("alReader3", new ApplicationName("OLD_ALREADER", systemPrivateMap.get("onyx"), ONYX_COMMON));

	}

	public class Platform {
		String name;
		String dir;

		private Platform(String name, String dir) {
			this.name = name;
			this.dir = dir;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDir() {
			return dir;
		}

		public void setDir(String dir) {
			this.dir = dir;
		}
		
	}

	public class Devices {
		String name;
		Platform platform;
		String dir;

		Devices(String name, Platform platform, String dir) {
			this.name = name;
			this.platform = platform;
			this.dir = dir;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Platform getPlatform() {
			return platform;
		}

		public void setPlatform(Platform platform) {
			this.platform = platform;
		}

		public String getDir() {
			return dir;
		}

		public void setDir(String dir) {
			this.dir = dir;
		}
		
	}

	public class ApplicationType {

		String type;

		ApplicationType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
		
	}

	public class SystemPrivate {
		String[] confusion = {"debug", "release"};
		String systemPrivate;
		ApplicationType applicationType;

		public SystemPrivate(String systemPrivate, ApplicationType applicationType) {
			this.systemPrivate = systemPrivate;
			this.applicationType = applicationType;
		}

		public String getSystemPrivate() {
			return systemPrivate;
		}

		public void setSystemPrivate(String systemPrivate) {
			this.systemPrivate = systemPrivate;
		}

		public ApplicationType getApplicationType() {
			return applicationType;
		}

		public void setApplicationType(ApplicationType applicationType) {
			this.applicationType = applicationType;
		}

		public String[] getConfusion() {
			return confusion;
		}

		public void setConfusion(String[] confusion) {
			this.confusion = confusion;
		}
	}

	public class ApplicationName {
		String name;
		String path;
		SystemPrivate systemPrivate;

		ApplicationName(String name, SystemPrivate systemPrivate, String path) {
			this.name = name;
			this.systemPrivate = systemPrivate;
			this.path = path;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public SystemPrivate getSystemPrivate() {
			return systemPrivate;
		}

		public void setSystemPrivate(SystemPrivate systemPrivate) {
			this.systemPrivate = systemPrivate;
		}
		
		
	}
	
	public class AppLicationInfo {
	}
	
	

}
