
package controller;

/**
 *
 * @author Habiba Saim
 * 
 * Connects to KMAT DB
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
