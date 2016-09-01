<%--
    Document   : InsertUser
    Created on : Aug 2, 2016, 3:30:10 PM
    Author     : Saim
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

       
<%
    
    try {
        Connection connection = null;
        PreparedStatement pstatement1 = null;
        PreparedStatement pstatement2 = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        int user_tbl_InsertQuery = 0;
        int user_details_tbl_InsertQuery = 0;

        String mySQLuser = "root";
        String mySQLpwd = "century_77";
        out.println("Before Class.forName() <br>");
        //Class.forName("com.mysql.jdbc.Driver").newInstance();
        //out.println("\nClass.forName executed" + "<br>");
        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kmat",  mySQLuser, mySQLpwd) ;
        //out.println("\nConnected to DB" + "<br>");
        String email = request.getParameter("email");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");
       // out.println("Password: " + password);
        String address1 = request.getParameter("address");
        String workphone = request.getParameter("contactNumber");
        
        if(email!="" && password!="" && firstname!="" && lastname!="" ) {
           
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kmat",  mySQLuser, mySQLpwd);
                
                String queryString1 = "INSERT INTO user_tbl(username, password, create_dt, update_dt) VALUES ( ?, ?, NOW(), NOW())";
                
               
                pstatement1 = connection.prepareStatement(queryString1);
                pstatement1.setString(1, email);
                pstatement1.setString(2, password);
                user_tbl_InsertQuery = pstatement1.executeUpdate();
                if (user_tbl_InsertQuery != 0) {
                    out.println("1 row inserted successfully in user_tbl");
                    }
                
                String queryString2 = "INSERT INTO user_details_tbl(user_idfk, first_name, last_name, email1, email2, address1,"
                        + " address2, work_phone, mobile_phone, home_phone, details, create_dt, update_dt) "
                        + "VALUES ( ?, ?, ?, 'email1', 'email2', ?, 'address2', ?,'mobile_phone', 'home_phone', 'details', NOW(), NOW())";
               
                pstatement2 = connection.prepareStatement(queryString2);
                pstatement2.setString(1, email);
                pstatement2.setString(2, firstname);
                pstatement2.setString(3, lastname);
                pstatement2.setString(4, address1);
                pstatement2.setString(5, workphone);
                user_details_tbl_InsertQuery = pstatement2.executeUpdate();
               // pstatement.setString(4, full_name);
                //pstatement.setString(5, ulevel);
                //pstatement.setString(6, team_id);
                
                
                if (user_details_tbl_InsertQuery != 0) {
                    out.println("1 row inserted successfully in user_details_tbl");
                    }
                }
    }
            catch(Exception e){       
                out.println("Error: " + e.getMessage());       
            }
 %>
