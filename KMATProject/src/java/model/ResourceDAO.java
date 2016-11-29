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
        String resourcePath = bean.getResourcePath();
        String resourceType = bean.getResourceType();
        String resourceFormat = bean.getResourceFormat();
        String resourceSize = bean.getResourceSize();
        String resourceLink = bean.getResourceLink();
        
        
        String searchResourceName ="select resourcename from resource_tbl where resource_name='"+resourceName+"'";
        
        //String insertQuery1 = "insert into resource_tbl(resource_name,description,create_dt,update_dt)"+"VALUES('")";
        
        
        
        return bean;
    }
}
