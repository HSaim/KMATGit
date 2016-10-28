/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Connects to KMAT DB
 * @author Habiba Saim
 */

import java.sql.*;

public class ConnectionManager {
    
    static Connection con;
    static String url;
            
    public static Connection getConnection(){    

        try{
            String username = "kmat";
            String password = "kmat";            
            String url = "jdbc:mysql://localhost:3306/kmat";    

            Class.forName("com.mysql.jdbc.Driver");
            try{                     	
               con = DriverManager.getConnection(url, username, password);  
            }

            catch (SQLException ex){            
               ex.printStackTrace();
            }
        }

        catch(ClassNotFoundException e){
       
          System.out.println(e);
        }
        return con;
    }    
    
}
