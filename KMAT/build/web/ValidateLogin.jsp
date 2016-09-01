<%-- 
    Document   : validateLogin
    Created on : Jul 29, 2016, 10:18:59 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>


<%
    try {
        String mySQLuser = "root";
        String mySQLpwd = "century_77";

        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kmat", mySQLuser, mySQLpwd);
        String user = request.getParameter("username");
        String pwd = request.getParameter("password");

        PreparedStatement pst = conn.prepareStatement("Select username, password from user_tbl where username=? and password=?");
        pst.setString(1, user);
        pst.setString(2, pwd);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {      //User exists   
            //out.println("Valid login credentials"); 
            session.setAttribute("userName", user);
%>
<form method="post" action="menubarUser.jsp">    
    <input type="hidden" name="hidden" value="user-name">  
</form>
<script type="text/javascript">
    window.location.href = "UserHome";
</script>
<%} else {
%>
<script type="text/javascript">
    window.location.href = "LoginAgain.jsp";
</script>
<% }
    } catch (Exception e) {
        out.println("Error: " + e.getMessage());
    }
%>


