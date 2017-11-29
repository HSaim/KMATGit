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
    private String toolType;
    private String main;
    private String mainExtention;
    private int userId;
    private String hidden;
    private int toolID;
    private int toolTypeID;
    private boolean input;
    private boolean output;
    
    public boolean added;
    public boolean duplicateTool;
    
    public String getToolType(){
        return toolType;
    }
    public void setToolType(String name){
        toolType = name;
    }
    public String getMain(){
        return main;
    }
    public void setMain(String name){
        main = name;
    }
    public String getMainExt(){
        return mainExtention;
    }
    public void setMainExt(String name){
        mainExtention = name;
    }
    public boolean getInput(){
        return input;
    }
    public void setInput(boolean name){
        input = name;
    }
    public boolean getOutput(){
        return output;
    }
    public void setOutput(boolean name){
        output = name;
    }
    public int getToolID(){
        return toolID;
    }
    public void setToolID(int id){
        toolID = id;
    }
    public int getToolTypeID(){
        return toolTypeID;
    }
    public void setToolTypeID(int id){
        toolTypeID = id;
    }
    
    public int getuserId(){
        return userId;
    }
    public void setUserId(int id){
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
