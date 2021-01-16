package com.arvin.feifeiyu.win.json;

import java.io.Serializable;
import java.util.List;

public class System implements Serializable{

    private List<String> framework;
    private List<String> privApp;
    private List<String> app;
    public void setFramework(List<String> framework) {
         this.framework = framework;
     }
     public List<String> getFramework() {
         return framework;
     }

    public void setPrivApp(List<String> privApp) {
         this.privApp = privApp;
     }
     public List<String> getPrivApp() {
         return privApp;
     }

    public void setApp(List<String> app) {
         this.app = app;
	}
	public List<String> getApp() {
		return app;
	}
	
	public List<String> getList(String name){
		if (name.equals("framework")) {
			return framework;
		} else if (name.equals("privApp")
				||name.equals("priv-app")) {
			return privApp;
		} else if (name.equals("app")) {
			return app;
		}
		return null;
	}

}