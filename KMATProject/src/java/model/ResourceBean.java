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
    private String resourcename;
    private String resourcedescription;
    private String resourcepath;
    private String resourcetype;
    private String resourceformat;
    private String resourcesize;
    private String resourcelink;
    private String hidden;
    
    public boolean added;
    public boolean duplicateResource;
    
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
        resourcename = discrp;
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
    
    public String getResourceSize(){
        return resourcesize;
    }
    public void setResourceSize(String size){
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
