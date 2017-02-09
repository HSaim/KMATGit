/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fahad Akhtar
 */
public class ResourceBean implements java.io.Serializable{
    
    //Variable Declaration
    private int resourceID;
    private String resourcename;
    private String resourcedescription;
    private String resourcepath;
    private String resourcetype;
    private String resourceformat;
    private int resourcesize;
    private String resourcelink;
    private String resourcefile;
    private String userid;
    private String hidden;
    private String fileType;
    
    public boolean added;
    public boolean duplicateResource;
    
    public int getResourceID(){
        return resourceID;
    }
    public void setResourceID(int id){
        resourceID = id;
    }
    
    public String getFileType(){
        return fileType;
    }
    public void setFileType(String fType){
        fileType = fType;
    }
    public String getUserID(){
        return userid;
    }
    public void setUserID(String uid){
        userid = uid;
    }
    
    public String getFileName(){
        return resourcefile;
    }
    public void setFileName(String file){
        resourcefile = file;
    }
            
    
    public String getResourceName(){
        return resourcename;
    }
    public void setResourceName(String name){
        resourcename = name;
    }
    
    public String getResourceDiscription(){
        return resourcedescription;
    }
    public void setResourceDiscription(String discrp){
        resourcedescription = discrp;
    }
    
    public String getResourcePath(){
        return resourcepath;
    }
    public void setResourcePath(String path){
        resourcepath = path;
    }
    
    public String getResourceType(){
        return resourcetype;
    }
    public void setResourceType(String type){
        resourcetype = type;
    }
    
    public int getResourceSize(){
        return resourcesize;
    }
    public void setResourceSize(int size){
        resourcesize = size;
    }
    
    public String getResourceFormat(){
        return resourceformat;
    }
    public void setResourceFormat(String format){
        resourceformat = format;
    }
    
    public String getResourceLink(){
        return resourcelink;
    }
    public void setResourceLink(String link){
        resourcelink = link;
    }
    
    public String getHidden() {
       return hidden;
    }
    public void setHidden(String newHidden) {
       hidden = newHidden;
    }
    
    public boolean isAdded() {
       return added;
    }
    public void setAdded(boolean newAdded) {
       added = newAdded;
    }
    
    public boolean isDuplicateResource() {
       return duplicateResource;
    }
    public void setDuplicateResource(boolean newDuplicateResource) {
       duplicateResource = newDuplicateResource;
    }
}
