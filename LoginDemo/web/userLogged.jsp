<%-- 
    Document   : userLogged
    Created on : Sep 7, 2016, 10:14:32 AM
    Author     : Habiba Saim
--%>
 <%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="controller.UserBean"
   %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logged in</title>
    </head>
    <body>
        <center>
            <% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%>
			
            Welcome <%= currentUser.getUsername() %>
         </center>
    </body>
</html>
