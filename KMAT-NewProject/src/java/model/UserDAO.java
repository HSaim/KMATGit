/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Query KMAT DB to get and insert user's record
 * @author Habiba Saim
 */
public class UserDAO {
    
    static Connection currentCon = null;
    static ResultSet rs = null;  
		
    //Validate username and password while login
    public static UserBean login(UserBean bean) {
	
        //preparing some objects for connection 
        Statement stmt = null;    

        String username = bean.getUsername();    
        String password = bean.getPassword();   

        String searchQuery =
              "select * from user_tbl where username='"
                       + username
                       + "' AND password='"
                       + password
                       + "'";	    
       

        try{            
        
           //connect to DB 
           currentCon = ConnectionManager.getConnection();
           stmt=currentCon.createStatement();
           rs = stmt.executeQuery(searchQuery);	        
           boolean more = rs.next();

           // if user does not exist set the isValid variable to false
           if (!more) 
           {
              //System.out.println("Sorry, you are not a registered user! Please sign up first");
              bean.setValid(false);
           } 

           //if user exists set the isValid variable to true
           else if (more) 
           {
              //String firstName = rs.getString("FirstName");
              //String lastName = rs.getString("LastName");

              System.out.println("Welcome " + username);
              //bean.setFirstName(firstName);
             // bean.setLastName(lastName);
              bean.setValid(true);
           }
        } 

        catch (Exception ex) {
        
           System.out.println("Log In failed: An Exception has occurred! " + ex);
        } 

        //some exception handling
        finally{
            
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

             if (currentCon != null) {
                try {
                   currentCon.close();
                } 
                catch (Exception e) {
                }
                currentCon = null;
             }
          }

        return bean;

    }	
    
    
}
