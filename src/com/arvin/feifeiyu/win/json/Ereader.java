package com.arvin.feifeiyu.win.json;

import java.io.Serializable;
import java.util.List;

public class Ereader implements Serializable{

    private String path;
    private List<String> app;
    private List<String> confusion;
    public void setPath(String path) {
         this.path = path;
     }
     public String getPath() {
         return path;
     }

    public void setApp(List<String> app) {
         this.app = app;
     }
     public List<String> getApp() {
         return app;
     }

    public void setConfusion(List<String> confusion) {
         this.confusion = confusion;
     }
     public List<String> getConfusion() {
         return confusion;
     }

}