package com.arvin.feifeiyu.win.json;

import java.io.Serializable;
import java.util.List;

public class Platforms implements Serializable{

    private String name;
    private String dir;
    private List<String> models;
    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setDir(String dir) {
         this.dir = dir;
     }
     public String getDir() {
         return dir;
     }

    public void setModels(List<String> models) {
         this.models = models;
     }
     public List<String> getModels() {
         return models;
     }

}