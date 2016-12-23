/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *Query KMAT DB to get and insert resource's record
 * Insert new resource.
 * 
 * @author Fahad Akhtar
 */

import java.io.PrintWriter;
import java.text.*;
import java.util.*;
import java.sql.*;


public class ResourceDAO {
    
    static Connection currentCon = null;
    static ResultSet rs = null;
    static Statement stmt = null;
    
    
    //Add new Resourse in KMAT DB
    
    public static ResourceBean insertResource(ResourceBean bean){
        int resource_tbl_Insertion = 0;
        int resource_upload_tbl_Insertion = 0;
        int resource_link_tbl_Insertion = 0;
        int resourceId = 0;
        
        String resourceName = bean.getResourceName();
        String resourceDiscription = bean.getResourceDiscription();
        String userID = bean.getUserID();
        String resourceType = bean.getResourceType();
        String resourceLink = bean.getResourceLink();
        String resourceFile = bean.getFileName();
        int resourceSize = bean.getResourceSize();
        String resourceFormat = bean.getResourceFormat();
        String resourcePath = bean.getResourcePath();
        
        //String userID = bean.getUserID();
        
        
        String searchResourceName ="select resource_name from resource_tbl where resource_name='"+resourceName+"'";
        
        String insertQuery1 = "insert into resource_tbl(user_idfk,resource_name,description,create_dt,update_dt)"+"VALUES('"+userID+"','"+resourceName+"',"
                + "'"+resourceDiscription+"',NOW(),NOW())";
        String searchResourceId = "select resource_id from resource_tbl where resource_name ='"+resourceName+"'";
        
        
        try{
            if(resourceName!="" && resourceDiscription!=""){
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchResourceName);
            boolean more = rs.next();
            
            if(more){
                bean.setDuplicateResource(true);                
            }
            else{
                bean.setDuplicateResource(false);
                //insertion in resource table
                resource_tbl_Insertion = stmt.executeUpdate(insertQuery1);
                rs = stmt.executeQuery(searchResourceId);
                if(rs.next()){
                    resourceId=rs.getInt("resource_id");
                    if(resourceFile.equals("none")){
                        String insertQuery2 = "Insert into resource_link_tbl (resource_idfk, link) Values (?, ?)";
                        //resource_link_tbl_Insertion = stmt.executeUpdate(insertQuery2);
                        PreparedStatement pst=currentCon.prepareStatement(insertQuery2);
                        pst.setInt(1,resourceId);
                        pst.setString(2,resourceLink);
                        pst.executeUpdate();
                        
                    }
                    else if(resourceLink.equals("none")){
                      // String insertQuery3 = "INSERT INTO resource_upload_tbl(resource_idfk, path, type, format, Size) VALUES("+resourceId+", '"+resourcePath+"', '"+resourceType+"', '"+resourceFormat+"', "+resourceSize+")";
                       //String inserQ = "Insert into resource_upload_tbl (resource_idfk, path) Values (" +resourceId +", 'c:\\resourcePath\\cvbvb\\')";
                       String inserQ2 = "Insert into resource_upload_tbl (resource_idfk, path, type, format, Size) Values (?, ?, ?, ?, ?)";
                       //resource_upload_tbl_Insertion = stmt.executeUpdate(inserQ);
                       PreparedStatement pst=currentCon.prepareStatement(inserQ2);
                        pst.setInt(1,resourceId);
                        pst.setString(2,resourcePath);
                        pst.setString(3, resourceType);
                        pst.setString(4, resourceFormat);
                        pst.setInt(5, resourceSize);
                        pst.executeUpdate();
                    }
                    if(resource_tbl_Insertion != 0&& (resource_upload_tbl_Insertion != 0 || resource_link_tbl_Insertion != 0)){
                        bean.setAdded(true);
                        
                    }
                    else{
                        bean.setAdded(false);
                    }
                    
                }
            }
            
            
        }
        }
        catch(Exception ex){
            System.out.println("Resource Addition Failed: an Exception Has Occured1"+ex);
            
        }
        
        
        //some exception handling
        finally{       
            
            closeConnection();
        }

        
        
        return bean;
    }

private static void closeConnection(){
        if (rs != null){
            try {
                rs.close();
            } 
            catch (Exception e) {}
                rs = null;
        }
       if (stmt != null) {
        try {
           stmt.close();
        } 
        catch (Exception e) {}
           stmt = null;
        }   
       ConnectionManager.putConnection(currentCon);
    }
public static ArrayList<ResourceBean> getResources(){
    ArrayList<ResourceBean> list = new ArrayList<ResourceBean>();
    return list;
}
public static ResourceBean getResource(String resourceName){
    ResourceBean resource = new ResourceBean();
    return resource;
}
    
}

