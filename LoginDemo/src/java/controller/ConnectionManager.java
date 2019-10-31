/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Habiba Saim
 */
import java.sql.*;
import java.util.*;

public class ConnectionManager {
    static Connection con;
    static String url;
            
    public static Connection getConnection(){    

        try{
            String username = "kmat";
            String password = "kmat";
           // String url = "jdbc:odbc:" + "//localhost:3306/kmat_db1"; 
           String url = "jdbc:mysql://localhost:3306/kmat_db1";
            // assuming "DataSource" is your DataSource name

            //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Class.forName("com.mysql.jdbc.Driver");//.newInstance();
            try{
                     	
               con = DriverManager.getConnection(url, username, password); 

            // assuming your SQL Server's	username is "username"               
            // and password is "password"

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
