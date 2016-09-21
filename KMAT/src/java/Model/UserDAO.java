
package Model;

/**
 * Query KMAT DB to confirm username and password
 * Insert new user 
 * 
 * @author Habiba Saim
 */
import java.io.PrintWriter;
import java.text.*;
import java.util.*;
import java.sql.*;
//import java.sql.Connection;

public class UserDAO {
  
    static Connection currentCon = null;
    static ResultSet rs = null;  
		
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
    
    public static AddUserBean insertUser(AddUserBean bean) {
        
        
        //preparing some objects for connection 
        // Connection con = null;
        // ResultSet rs1 = null;  
		
        Statement stmt = null; 
        int user_tbl_Insertion = 0;
        int user_details_tbl_Insertion = 0;
        int userId  = 0;
        
        String userName = bean.getUserName();
        String priEmail = bean.getPriEmail();
        String secEmail = bean.getSecEmail();
        String firstName = bean.getFirstName();
        String lastName = bean.getLastName();
        String password = bean.getPassword();       
        String postalAddress = bean.getPosAddress();
        String perAddress = bean.getPerAddress();
        String workPhone = bean.getWorkPhone();
        String homePhone = bean.getHomePhone();
        String mobPhone = bean.getMobPhone();
       // String hidden = bean.getHidden();
        
        String searchUserName =
              "select username from user_tbl where username='"
                       + userName+"'";
        
        String insertQuery1 = 
                "insert into user_tbl(username, password, create_dt, update_dt) "
                + "VALUES ( '" + userName +"', '" + password + "' , NOW(), NOW())";
        
        String searchUserId =
                 "select user_id from user_tbl where username='"
                       + userName+"'";
        
        try{ 
            if(userName!="" && password!="" && firstName!="" && lastName!="" && priEmail!="" ) {

                currentCon = ConnectionManager.getConnection();
                stmt=currentCon.createStatement();
                rs = stmt.executeQuery(searchUserName);	        
                boolean more = rs.next();
                
                if(more){ // If username already exists 
                    bean.setDuplicateUser(true);
                }
                else{
                    bean.setDuplicateUser(false);
                    //Insertion in 'user_tbl' table
                    user_tbl_Insertion = stmt.executeUpdate(insertQuery1);
                    rs = stmt.executeQuery(searchUserId);
                    
                    if(rs.next())  {  
                        userId = rs.getInt("user_id");
                   
                        String insertQuery2 = 
                            "INSERT INTO user_details_tbl(user_idfk, first_name, last_name, email1, email2, address1,"
                            + " address2, work_phone, mobile_phone, home_phone, details, create_dt, update_dt) "
                            + "VALUES ( " + userId + ", '" + firstName +"', '" + lastName + "', '" + priEmail + "', '" + secEmail + "', '" + postalAddress
                            + "', '" + perAddress + "', '" + workPhone + "', '" + mobPhone + "', '" + homePhone + "', 'details', NOW(), NOW())";
                        user_details_tbl_Insertion = stmt.executeUpdate(insertQuery2);
                        
                        if (user_tbl_Insertion != 0 && user_details_tbl_Insertion != 0) {
                            bean.setAdded(true);                            
                        }
                        else{
                            bean.setAdded(false);
                        }
                    }
                }
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

    