/* To change this license header, choose License Headers in Project Properties.
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
        int userID = bean.getUserID();
        String resourceType = bean.getResourceType();
        String resourceLink = bean.getResourceLink();
        String resourceFile = bean.getFileName();
        int resourceSize = bean.getResourceSize();
        String resourceFormat = bean.getResourceFormat();
        String resourcePath = bean.getResourcePath();
        
        //String userID = bean.getUserID();
        
        
        String searchResourceName ="select resource_name from resource_tbl where resource_name='"+resourceName+"'";
        
        String insertQuery1 = "insert into resource_tbl(user_idfk,resource_name,description, resource_type, create_dt,update_dt)"+"VALUES('"+userID+"','"+resourceName+"',"
                + "'"+resourceDiscription+"','"+resourceType+"',NOW(),NOW())";
        String searchResourceId = "select resource_id from resource_tbl where resource_name ='"+resourceName+"'";
        
        
        try{
            if(resourceName!="" && resourceDiscription!="" && resourceType!=""){
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
                    System.out.println("Resource Type : "+resourceType);
                    if(resourceType.equals("link")){
                        String insertQuery2 = "Insert into resource_link_tbl (resource_idfk, link) Values (?, ?)";
                        //resource_link_tbl_Insertion = stmt.executeUpdate(insertQuery2);
                        PreparedStatement pst=currentCon.prepareStatement(insertQuery2);
                        pst.setInt(1,resourceId);
                        pst.setString(2,resourceLink);
                        pst.executeUpdate();
                        
                    }
                    else if(resourceType.equals("file")){
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

public static ResourceBean getResource(String resourceName){
    ResourceBean resource = new ResourceBean();

    String query1 ="Select * from resource_tbl where resource_tbl.resource_name='"+resourceName+"'";
    
    try{
        currentCon = ConnectionManager.getConnection();
        stmt = currentCon.createStatement();
        rs = stmt.executeQuery(query1);
        
        int resourceID = 0;
        String resourceType = "";
        if(rs.next()){
            
            resourceType = rs.getString("resource_type"); 
            resource.setResourceType(resourceType);
            resourceID = rs.getInt("resource_id");
            resource.setResourceID(resourceID);
            String discr = rs.getString("description");
            resource.setResourceDiscription(discr);
            System.out.println("Discription: "+discr);
            resource.setResourceName(rs.getString("resource_name"));
            //resource.setResourceDiscription(rs.getString("description"));
            if(resourceType.equals("link")|| resourceType.equals("Link")){
                String link= "select * from kmat.resource_link_tbl where resource_idfk = '"+resourceID+"'";
                rs = stmt.executeQuery(link);
                if(rs.next()){
                    resource.setResourceLink(rs.getString("link"));
                }
            }
            else if(resourceType.equals("file")||resourceType.equals("File")){
                String file = "select * from kmat.resource_upload_tbl where resource_idfk = '"+resourceID+"'";
                
                resource.setFileName(rs.getString("file_name"));
                resource.setFileType(rs.getString("type"));
                resource.setResourceFormat(rs.getString("format"));
                resource.setResourcePath(rs.getString("path"));
                resource.setResourceSize(rs.getInt("size"));
                            
            }
            
            
            
            
        }
    }
    catch(Exception ex){
        ex.printStackTrace();
    }
    finally{
        closeConnection();
    }
    
    return resource;
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
public static ArrayList<ResourceBean> getResources(String currentUsername)
{
    ArrayList<ResourceBean> list = new ArrayList<ResourceBean>();
    String query = "Select kmat.user_tbl.user_id from kmat.user_tbl where kmat.user_tbl.username = '"+currentUsername+"'";
    String uid="";
    
    
    
    try{
        boolean more;
        currentCon = ConnectionManager.getConnection();
        stmt=currentCon.createStatement();
        rs = stmt.executeQuery(query);	        
        more = rs.next();
        if(more){
            uid = rs.getString("user_id");
            
        }
        String query1 = "select kmat.resource_tbl.resource_id, kmat.resource_tbl.resource_name From kmat.resource_tbl where kmat.resource_tbl.user_idfk = '"+uid+"'";
        System.out.println(query1);
        rs = stmt.executeQuery(query1);
       // more = rs.next();
        while(rs.next()){
            ResourceBean resource = new ResourceBean();
            resource.setResourceID(rs.getInt("resource_id"));
            resource.setResourceName(rs.getString("resource_name"));
            list.add(resource);
            int id = rs.getInt("resource_id");
            String name = rs.getString("resource_name");
            System.out.println(id);
            System.out.println(name);
        } 
    }
    catch(Exception ex){
        ex.printStackTrace();
        
    }
    finally{
        
    }
    return list;

    
}

public static ResourceBean updateResource(ResourceBean bean){
    int resourceID = bean.getResourceID();
    int userID = bean.getUserID();
    String resourceName = bean.getResourceName();
    String resourceDescription = bean.getResourceDiscription();
    String resourceType = bean.getResourceType();
    String link = "";
    String fileName = "";
    String filePath = "";
    String fileType = "";
    String fileFormat = "";
    int fileSize = 0;
    if (resourceType.equals("link")){
        link = bean.getResourceLink();
        
    }
    else if (resourceType.equals("file")){
        fileName = bean.getFileName();
        filePath = bean.getResourcePath();
        fileType = bean.getFileType();
        fileFormat = bean.getResourceFormat();
        fileSize = bean.getResourceSize();
    }
    String query1 = "update resource_tbl set user_idfk= '"+userID+"', resource_name = '"+resourceName+"',description = '"+resourceDescription+"', resource_type = '"+resourceType+"', update_dt = NOW() where resource_id = '"+resourceID+"'";
    String query2 = "update resource_link set link = '"+link+"' where resource_idfk = '"+resourceID+"'";
    String query3 = "update resource_upload_tbl set file_name = '"+fileName+"', path = '"+filePath+"', type = '"+fileType+"', format = '"+fileFormat+"', size = '"+fileSize+"' where resource_idfk = '"+resourceID+"'";
    try {
        currentCon = ConnectionManager.getConnection();
        stmt = currentCon.createStatement();
        stmt.executeUpdate(query1);
        if(resourceType.equals("link")){
            stmt.executeUpdate(query2);
        }
        else if(resourceType.equals("file")){
            stmt.executeUpdate(query3);
        }
        
    
        
    }
    catch(Exception ex){
        
        ex.printStackTrace();
    }
    finally{
        closeConnection();
    }
    
    
    return bean;
    
}


    
}

