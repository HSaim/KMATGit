
package model;

/**
 * Query KMAT DB to get and insert user's record
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
    static Statement stmt = null;
		
    //Validate username and password while login
    public static LoginUserBean login(LoginUserBean bean) {
	
        //preparing some objects for connection 
            

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
           if (!more) {           
              //System.out.println("Sorry, you are not a registered user! Please sign up first");
              bean.setValid(false);
           } 

           //if user exists set the isValid variable to true
           else if (more) {           
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
            closeConnection();            
          }

        return bean;
    }	
    
    //Add a new user in KMAT DB
    public static UserBean insertUser(UserBean bean) {
        
        
        //preparing some objects for connection 
        // Connection con = null;
        // ResultSet rs1 = null;  
		
        //Statement stmt = null; 
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
            
            closeConnection();
          }

        
        return bean;
    }
    
    /*
    Updates a user profile
    */
    public static UserBean updateUser(UserBean bean){
       
        String userId = bean.getUserId();
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
        /*
        String searchUserName =
              "select username from user_tbl where username='"
                       + userName+"'";
        */
        String query1 = "Update user_tbl set  password ='" + password +"' , update_dt = NOW() where user_id = '" + userId +"'";
        String query2 = "Update user_details_tbl set first_name ='" +firstName + "', last_name ='" + lastName +"', email1 ='" + priEmail 
                +"', email2 ='" + secEmail+ "', address1 ='" + postalAddress + "', address2 ='" +perAddress + "', work_phone = '" + workPhone +"', "
                + "mobile_phone ='" +mobPhone +"', home_phone ='" + homePhone + "', update_dt = NOW() where user_idfk = '" + userId +"'";
        //preparing some objects for connection 
        try{
            currentCon = ConnectionManager.getConnection();
            stmt=currentCon.createStatement();
            //rs = stmt.executeQuery(query);
           // rs = stmt.executeQuery(searchUserName);	        
            //boolean more = rs.next();

           // if(more){ // If username already exists 
                //bean.setDuplicateUser(true);
            //}
            //else{
             //   bean.setDuplicateUser(false);
                stmt.executeUpdate(query1);
                stmt.executeUpdate(query2);
            //}
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            closeConnection();
        }
        
        return bean;
    }
    /*
    Reurns an arraylist of all users
    */
    public static ArrayList<UserBean> getUsers(){
        ArrayList<UserBean> list = new ArrayList<UserBean>();
        
         String query = "Select user_tbl.username, user_tbl.password, user_details_tbl.first_name, user_details_tbl.last_name, "
                 + "user_details_tbl.email1, user_details_tbl.email2, "
                + "user_details_tbl.address1,user_details_tbl.address2, user_details_tbl.work_phone, user_details_tbl.home_phone, user_details_tbl.mobile_phone "
                + "from user_tbl, user_details_tbl  "
                + "where user_tbl.user_id = user_details_tbl.user_idfk";
        
        //preparing some objects for connection 
        try{
            currentCon = ConnectionManager.getConnection();
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(query);	        
           // boolean more = rs.next();
            while (rs.next()){
               UserBean user = new UserBean();
               
               user.setUserName(rs.getString("username"));
               user.setPassword(rs.getString("password"));
               user.setFirstName(rs.getString("first_name"));
               user.setLastName (rs.getString("last_name"));
               user.setPriEmail(rs.getString("email1"));
               user.setSecEmail(rs.getString("email2"));
               user.setPosAddress(rs.getString("address1"));
               user.setPerAddress(rs.getString("address2"));
               user.setWorkPhone(rs.getString("work_phone"));
               user.setMobPhone(rs.getString("mobile_phone"));
               user.setHomePhone(rs.getString("home_phone"));

               list.add(user);    
            }
        }
        catch(Exception ex){
            
        }
        finally{
            closeConnection();
        }
        return list;
    }
    /*
    Returns a user with respect to username
    */
    public static UserBean getUser(String userName){
        UserBean user = new UserBean();
        
         String query = "Select user_tbl.user_id, user_tbl.username, user_tbl.password, user_details_tbl.first_name, user_details_tbl.last_name, "
                 + "user_details_tbl.email1, user_details_tbl.email2, "
                + "user_details_tbl.address1,user_details_tbl.address2, user_details_tbl.work_phone, user_details_tbl.home_phone, user_details_tbl.mobile_phone "
                + "from user_tbl, user_details_tbl  "
                + "where user_tbl.username = '"+ userName+"' AND user_tbl.user_id = user_details_tbl.user_idfk";
                       
        
        //preparing some objects for connection 
        try{
            currentCon = ConnectionManager.getConnection();
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(query);	        
           // boolean more = rs.next();
            //while (rs.next()){
              // UserBean user = new UserBean();
            if (rs.next()){
               user.setUserId(rs.getString("user_id"));
               user.setUserName(rs.getString("username"));
               user.setPassword(rs.getString("password"));
               user.setFirstName(rs.getString("first_name"));
               user.setLastName (rs.getString("last_name"));
               user.setPriEmail(rs.getString("email1"));
               user.setSecEmail(rs.getString("email2"));
               user.setPosAddress(rs.getString("address1"));
               user.setPerAddress(rs.getString("address2"));
               user.setWorkPhone(rs.getString("work_phone"));
               user.setMobPhone(rs.getString("mobile_phone"));
               user.setHomePhone(rs.getString("home_phone"));
               //list.add(user);    
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            closeConnection();
        }
        return user;
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

    