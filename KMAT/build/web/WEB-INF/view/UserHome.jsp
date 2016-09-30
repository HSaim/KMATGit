<%-- 
    Document   : UserHome
    Created on : Jul 29, 2016, 11:52:55 AM
    Author     : Habiba Saim

    Logged in user's home page
--%>
<!-- To prevent user from accessing any user specific page after logout/seesion end -->
<%
    response.setHeader("Pragma","no-cache"); 
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader("Expires",-1);
    if(session.getAttribute("CurrentSessionUser")==null){
    
        response.sendRedirect("login.jsp");
    }
%> 

<%@page import="Model.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <title>KMAT User</title>
    </head>
    <body class = "home"> <!-- onload="loading();"> -->
        <div class = "wrapper">
           
            <%--
                String user = request.getParameter("hidden");
                out.println("user name: " + user);
            --%>
              
            <%-- Menubar starts --%>
            <jsp:include page="../../includes/menubarUser.jsp" />
            <%-- Menubar Ends --%>

            <%-- Slider starts --%>
         
            <div id = simple>  
                 <jsp:include page="../../includes/topSliderImages.jsp" />
            </div>
            <%-- Slider ends --%>
            
            <%-- Sidebar Menu Starts --%>                            
            <jsp:include page="../../includes/verticalUserMenu.jsp" />                 
            
            <%-- Sidebar Menu Ends --%>
            
             <%-- adds Top Slider, login functions --%>
             <jsp:include page="../../includes/js.jsp" /> 

             <%-- footer.jspf integrates here --%>