package com.arvin.feifeiyu.win.json;

import java.io.Serializable;

public class Onyx implements Serializable{

    private Ereader ereader;
    private Common common;
    private Sample sample;
    private Alreader alreader;
    public void setEreader(Ereader ereader) {
         this.ereader = ereader;
     }
     public Ereader getEreader() {
         return ereader;
     }

    public void setCommon(Common common) {
         this.common = common;
     }
     public Common getCommon() {
         return common;
     }

    public void setSample(Sample sample) {
         this.sample = sample;
     }
     public Sample getSample() {
         return sample;
     }

    public void setAlreader(Alreader alreader) {
         this.alreader = alreader;
     }
     public Alreader getAlreader() {
         return alreader;
     }
     
     public String toString() {
      	StringBuilder stringBuilder = new StringBuilder();
      	stringBuilder.append("Onyx: ")
      		.append("Path").append(ereader.getPath().toString()).append("\n")
      		.append("App").append(ereader.getApp().toString()).append("\n")
      		.append("Confusion").append(ereader.getConfusion().toString());
      	return stringBuilder.toString();
      }
     
     public String gatPath(String name) {
    	 if (name.equals("ereader")) {
    		 return ereader.getPath();
    	 } else if (name.equals("common")) {
    		 return common.getPath();
    	 } else if (name.equals("sample")) {
    		 return sample.getPath();
    	 } else if (name.equals("alreader")) {
    		 return alreader.getPath();
    	 }
    	 return null;
     }

}