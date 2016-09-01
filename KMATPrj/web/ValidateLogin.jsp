<%-- 
    Document   : validate
    Created on : Jul 29, 2016, 10:18:59 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

       
       <%
        try{
            String mySQLuser = "root";
            String mySQLpwd = "century_77";
            //out.println("Before Class.forName() <br>");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //out.println("\nClass.forName executed" + "<br>");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kmat",  mySQLuser, mySQLpwd) ;
            //out.println("\nConnected to DB" + "<br>");
            String user = request.getParameter("username");
            //out.println("\nUser Name: "+ username);
            //out.println("<br>");
            String pwd = request.getParameter("password");
            //out.println("\nPassword: " + pwd + "<br>");
            
            PreparedStatement pst = conn.prepareStatement("Select username, password from user_tbl where username=? and password=?");
            pst.setString(1, user);
            pst.setString(2, pwd);
            ResultSet rs = pst.executeQuery();                        
            if(rs.next())  {         
               //out.println("Valid login credentials"); 
               session.setAttribute("userName", user);
               %>
                <form method="post" action="menubarUser.jsp">    
                     <input type="hidden" name="userName" value="${user}"> 
                </form>
                <script type="text/javascript">
                    window.location.href = "UserHome.jsp";
                </script>
            <%}
            else{
                %>
              <script type="text/javascript">
                window.location.href = "LoginAgain.jsp";
            </script>
            <% }
        }
        catch(Exception e){       
         out.println("Error: " + e.getMessage());       
        }
        %>
        
        
