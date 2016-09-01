<%--
    Document   : InsertUser
    Created on : Aug 2, 2016, 3:30:10 PM
    Author     : Habiba Saim
    Inserts a new user in the DB
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

 <!DOCTYPE html>
<html>   
    <head>
        
    
    </head>
    <body>
<%
    
    try {
        Connection connection = null;
        PreparedStatement pstatement = null;
        PreparedStatement pstatement1 = null;
        PreparedStatement pstatement2 = null;
        PreparedStatement pstatement3 = null;
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        int user_tbl_InsertQuery = 0;
        int user_details_tbl_InsertQuery = 0;
        int userId  =0;
        
        String mySQLuser = "root";
        String mySQLpwd = "century_77";
        //out.println("Before Class.forName() <br>");
        //Class.forName("com.mysql.jdbc.Driver").newInstance();
        //out.println("\nClass.forName executed" + "<br>");
        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kmat",  mySQLuser, mySQLpwd) ;
        //out.println("\nConnected to DB" + "<br>");
        String userName = request.getParameter("user_name");
        String priEmail = request.getParameter("p_email");
        String secEmail = request.getParameter("s_email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String password = request.getParameter("password");
       // out.println("Password: " + password);
        String postalAddress = request.getParameter("postal_address");
        String perAddress = request.getParameter("per_address");
        String workPhone = request.getParameter("w_phone");
        String homePhone = request.getParameter("h_phone");
        String mobPhone = request.getParameter("m_phone");
        String hidden = request.getParameter("hidden");
        
        if(userName!="" && password!="" && firstName!="" && lastName!="" && priEmail!="" ) {
           
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/kmat",  mySQLuser, mySQLpwd);
                
                //Check if the user name already exists
                String duplicatesValidationQuery = "Select username from user_tbl where username = ?";
                pstatement = connection.prepareStatement(duplicatesValidationQuery);
                pstatement.setString(1, userName);
                ResultSet rs1 = pstatement.executeQuery();
                if(rs1.next())  { 
                    if (hidden.equalsIgnoreCase("adduser")){
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('User name already exists, select a new one');");
                        out.println("location='AddUser.jsp';");
                        out.println("</script>");
                        //response.sendRedirect("SignUp.jsp");
                    }
                    else if(hidden.equalsIgnoreCase("signup")){
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('User name already exists, select a new one');");
                        out.println("location='SignUp.jsp';");
                        out.println("</script>");
                    }
                }
                /////////////////////////////////////////
                
                //Insertion in 'user_tbl' table
                String insertQuery1 = "INSERT INTO user_tbl(username, password, create_dt, update_dt) VALUES ( ?, ?, NOW(), NOW())";
                pstatement1 = connection.prepareStatement(insertQuery1);
                pstatement1.setString(1, userName);
                pstatement1.setString(2, password);
                user_tbl_InsertQuery = pstatement1.executeUpdate();
                
                //Retrieval of user id of recently entered user in 'user_tbl'table
                String selectQuery = "Select user_id from user_tbl where username = ?";
                pstatement2 = connection.prepareStatement(selectQuery);
                pstatement2.setString(1, userName);
                ResultSet rs2 = pstatement2.executeQuery();
                if(rs2.next())  {  
                    userId = rs2.getInt("user_id");
                   // out.println("Id: " + userId);
                }
                
                //Insertion in 'user_details_tbl' table
                String insertQuery2 = "INSERT INTO user_details_tbl(user_idfk, first_name, last_name, email1, email2, address1,"
                        + " address2, work_phone, mobile_phone, home_phone, details, create_dt, update_dt) "
                        + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?,?, ?, 'details', NOW(), NOW())";
               
                pstatement3 = connection.prepareStatement(insertQuery2);
                pstatement3.setInt(1, userId);
                pstatement3.setString(2, firstName);
                pstatement3.setString(3, lastName);
                pstatement3.setString(4, priEmail);
                pstatement3.setString(5, secEmail);
                pstatement3.setString(6, postalAddress);
                pstatement3.setString(7, perAddress);
                pstatement3.setString(8, workPhone);
                pstatement3.setString(10, homePhone);
                pstatement3.setString(9, mobPhone);
                
                user_details_tbl_InsertQuery = pstatement3.executeUpdate();
               
                if (user_tbl_InsertQuery != 0 && user_details_tbl_InsertQuery != 0) {
                    if (hidden.equalsIgnoreCase("signup")){
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('One record inserted successfully');");
                        out.println("location='login.jsp';");
                        out.println("</script>");
                    %>
                        <!--alert("Congratulations '"+ firstName + "! Your account is created" );
                        var r=confirm("Congratulations '"+ firstName + "! Your account is created");-->
                        <!--
                        <script type="text/javascript">

                            //function alertName(){
                                alert(firstName + "! Your account is created");                       
                            //}
                            //alert("Congratulations '"+ firstName + "! Your account is created" );
                            
                            window.location.href = "login.jsp";
                            // alertName();
                        </script> --> 
                    <%
                    }  
                    else if(hidden.equalsIgnoreCase("adduser")){
                   
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('One record inserted successfully');");
                        out.println("location='UserHome.jsp';");
                        out.println("</script>");
                         %>
                        <!--
                        <script type="text/javascript">

                            function alertName(){
                                alert(firstName + " is added");                       
                            }
                            //alert("Congratulations '"+ firstName + "! Your account is created" );
                            window.location.href = "UserHome.jsp";
                            alertName();
                        </script>
                        -->
                    <%
                    }
                }
            }
        }
            catch(Exception e){       
                out.println("Error: " + e.getMessage());       
            }
 %>

 </body>
</html>