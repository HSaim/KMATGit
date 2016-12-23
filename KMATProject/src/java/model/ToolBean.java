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
public class ToolBean implements java.io.Serializable{
    private String toolName;
    private String toolDescription;
    private String toolPath;
    private String toolLink;
    private String toolType;
    private String userId;
    private String hidden;
    
    public boolean added;
    public boolean duplicateTool;
    
    public String getuserId(){
        return userId;
    }
    public void setUserId(String id){
        userId = id;
    }
    public String getToolName(){
        return toolName;
    }
    public void setToolName(String name){
        toolName = name;
    }
    public String getToolDescription(){
        return toolDescription;
    }
    public void setToolDescription(String descrp){
        toolDescription = descrp;
    }
    public String getToolPath(){
        return toolPath;
    }
    public void setToolPath(String path){
        toolPath = path;
    }
    public String getToolType(){
        return toolType;
    }
    public void setToolType(String type){
        toolType = type;
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
       return duplicateTool;
    }
    public void setDuplicateResource(boolean newDuplicateResource) {
       duplicateTool = newDuplicateResource;
    }
    
    
    
}
