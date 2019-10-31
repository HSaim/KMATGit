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
import java.io.PrintWriter;
import java.text.*;
import java.util.*;
import java.sql.*;


public class ToolDAO {
    static Connection currentCon = null;
    static ResultSet rs = null;
    static Statement stmt = null;
        
    //Add new tool in the Database.
    public static ToolBean insertTool(ToolBean bean){
        int tool_tbl_insertion = 0;
        //int tool_type_tbl_insertion = 0;
        int tool_details_insertion = 0;
        int tool_id = 0;
        
        String toolName = bean.getToolName();
        String toolDescription = bean.getToolDescription();
        int userID = bean.getuserId();
        String toolType = bean.getToolType();
        String main = bean.getMain();
        String mainExt = bean.getMainExt();
        String tool_path = bean.getToolPath();
        boolean input = bean.getInput();
        boolean output = bean.getOutput();
        int toolTypeID = bean.getToolTypeID();
                
        String searchToolName = "select tool_name from tool_details where tool_name = '"+toolName+"'";
        String insert_query = "insert into tool_details(tool_idfk, tool_name, tool_description, main_name, main_ext,"
                +" tool_path, user_idfk, tool_input, tool_output, tool_added, tool_updated)"
                +"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
        String getToolTypeID = "Select tool_type_id from tool_type_tbl where tool_type_name = '"+toolType+"'";
        try{
            if(toolName != "" && toolDescription != "" ){
                currentCon = ConnectionManager.getConnection();
                stmt = currentCon.createStatement();
                rs = stmt.executeQuery(searchToolName);
                boolean more = rs.next();
                if(more){
                    bean.setDuplicateResource(true);
                }
                else{
                    bean.setDuplicateResource(false);
                    
                    rs = stmt.executeQuery(getToolTypeID);
                    if(rs.next()){
                        toolTypeID = rs.getInt("tool_type_id");
                        PreparedStatement pst = currentCon.prepareStatement(insert_query);
                        pst.setInt(1, toolTypeID);
                        pst.setString(2, toolName);
                        pst.setString(3, toolDescription);
                        pst.setString(4, main);
                        pst.setString(5, mainExt);
                        pst.setString(6, tool_path);
                        pst.setInt(7, userID);
                        pst.setBoolean(8, input);
                        pst.setBoolean(9, output);
                        pst.executeUpdate();
                        tool_details_insertion = 1;
                        
                    }
                    if(tool_details_insertion !=0){
                        bean.setAdded(true);
                    }
                    else{
                        bean.setAdded(false);
                        
                    }
                    
                }
                
            }
            
        }
        catch(Exception ex){
            System.out.println("Tool Addition Failed: An Exception has occured."+ex);
            
        }
        finally{
            closeConnection();
        }
        
    
        return bean;
    }
    public static int getToolTypeID(String ToolName){
        int id = 0;
        String query = "select tool_type_id from tool_type_tbl where tool_type_name = '"+ToolName+"'";
        currentCon = ConnectionManager.getConnection();
        boolean more;
        try{
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(query);
            more = rs.next();
            if(more){
                id = rs.getInt("tool_type_id");
                
            }
            
        }
        catch(Exception ex){
            System.out.println("An Exception Has Occured:"+ex);
            
        }
        
        return id;
    }
    public static ToolBean getTool(String toolName){
        ToolBean tool = new ToolBean();
        String getQuery = "Select * from tool_details where tool_name = '"+toolName+"'";
        try{
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(getQuery);
            int toolID = 0;
            int toolType = 0;
            if(rs.next()){
                toolID = rs.getInt("tool_details_id");
                tool.setToolID(toolID);
                int toolTypeID = rs.getInt("tool_idfk");
                tool.setToolTypeID(toolTypeID);
                String Name = rs.getString("tool_name");
                tool.setToolName(Name);
                String Description = rs.getString("tool_description");
                tool.setToolDescription(Description);
                String mainName = rs.getString("main_name");
                tool.setMain(mainName);
                String mainExtention = rs.getString("mainext");
                tool.setMainExt(mainExtention);
                int userID = rs.getInt("user_idfk");
                tool.setUserId(userID);
                boolean input = rs.getBoolean("tool_input");
                tool.setInput(input);
                boolean output = rs.getBoolean("tool_output");
                tool.setOutput(output);
                
                
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
            
        }
        finally{
            closeConnection();
            
        }
        
        return tool;
    }
    public static ToolBean getTool(int toolID){
        ToolBean tool = new ToolBean();
        String getQuery = "Select * from tool_details where tool_details_id = '"+toolID+"'";
        try{
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(getQuery);
            String toolName = "";
            int toolType = 0;
            if(rs.next()){
                
                int toolTypeID = rs.getInt("tool_idfk");
                tool.setToolTypeID(toolTypeID);
                String Name = rs.getString("tool_name");
                tool.setToolName(Name);
                String Description = rs.getString("tool_description");
                tool.setToolDescription(Description);
                String mainName = rs.getString("main_name");
                tool.setMain(mainName);
                String mainExtention = rs.getString("mainext");
                tool.setMainExt(mainExtention);
                String toolPath = rs.getString("tool_path");
                tool.setToolPath(toolPath);
                int userID = rs.getInt("user_idfk");
                tool.setUserId(userID);
                boolean input = rs.getBoolean("tool_input");
                tool.setInput(input);
                boolean output = rs.getBoolean("tool_output");
                tool.setOutput(output);
                
                
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
            
        }
        finally{
            closeConnection();
            
        }
        
        return tool;
    }
    
    public static ArrayList<ToolBean> getTools(String curntUser){
        ArrayList<ToolBean> list = new ArrayList<ToolBean>();
        String qurey = "Select kmat.user_tbl.user_id from kmat.user_tbl where kmat.user_tbl.username = '"+curntUser+"'";
        int uid = 0;
        
        try{
            boolean more;
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(qurey);
            more = rs.next();
            if(more){
                uid = rs.getInt("user_id");
            }
            String q2 = "select tool_details_id, tool_name from tool_details where user_idfk = '"+uid+"'";
            System.out.println(q2);
            rs = stmt.executeQuery(q2);
            while(rs.next()){
                ToolBean bean = new ToolBean();
                bean.setToolID(rs.getInt("tool_details_id"));
                bean.setToolName(rs.getString("tool_name"));
                list.add(bean);
                int id = rs.getInt("tool_details_id");
                String name = rs.getString("tool_name");
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
    public static int deleteTool(String toolName){
        
        int toolId=0;
        PreparedStatement ps1, ps2;
        int done=0; //deletion done or not flag
        String searchToolId = "select tool_details_id from tool_details where tool_name='"+ toolName+"'"; 
        String deleteFromToolDetailsTable = "delete from tool_details where tool_name =  '"+toolName+"'";
        
         try{ 
            currentCon = ConnectionManager.getConnection();  
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchToolId);
            
           if(rs.next())  {  
                toolId = rs.getInt("tool_details_id");
                ps1 =currentCon.prepareStatement(deleteFromToolDetailsTable);
                ps1.setInt(1, toolId);
                done = ps1.executeUpdate();
                
                //stmt.executeUpdate(deleteFromUserDetailsTable);
                //stmt.executeUpdate(deleteFromUserTable); 
            
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
        return done;
    }
    public static ToolBean updateTool(ToolBean bean){
        int toolID = bean.getToolID();
        int userId = bean.getuserId();
        int toolTypeID = bean.getToolTypeID();
        boolean input = bean.getInput();
        boolean output = bean.getOutput();
        String name = bean.getToolName();
        String descrp = bean.getToolDescription();
        String main = bean.getMain();
        String mainExt = bean.getMainExt();
        
        String q1 = "update tool_details set tool_idfk = '"+toolTypeID+"', tool_name = '"+name+"', tool_description = '"+descrp+"',"
                + "main_name = '"+main+"', main_ext = '"+mainExt+"', tool_input = '"+input+"', tool_output = '"+output+"', tool_updated = NOW()";
        try{
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            stmt.executeUpdate(q1);
              
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
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
    
}
