package com.arvin.feifeiyu.win.json;

import java.io.Serializable;

public class Application implements Serializable{

    private System system;
    private Onyx onyx;
    public void setSystem(System system) {
         this.system = system;
     }
     public System getSystem() {
         return system;
     }

    public void setOnyx(Onyx onyx) {
         this.onyx = onyx;
     }
     public Onyx getOnyx() {
         return onyx;
     }
     
     
     public String toString() {
     	StringBuilder stringBuilder = new StringBuilder();
     	stringBuilder.append("System: ")
     		.append("Framework ").append(system.getFramework().toString()).append(" \n")
     		.append("PrivApp ").append(system.getPrivApp().toString()).append(" \n")
     		.append("App ").append(system.getApp().toString());
     	return stringBuilder.toString();
     }


}