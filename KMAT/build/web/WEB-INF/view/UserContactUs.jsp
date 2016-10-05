<%-- 
    Document   : UserContactUs
    Created on : Aug 17, 2016, 10:18:19 AM
    Author     : Saim
--%>

<!-- Code to prevent user from accessing any user specific page after logout/session-end -->
<%
    response.setHeader("Pragma","no-cache"); 
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader("Expires",-1);
    if(session.getAttribute("CurrentSessionUser")==null){
    
        response.sendRedirect("login.jsp");
    }
%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <title>Contact Us</title>
    </head>
    <body class = "contact">
        <div class = "wrapper">
            <%-- Menubar starts --%>
            <jsp:include page="../../includes/menubarUser.jsp" />
            <%-- Menubar Ends --%>

            <%-- Slider starts --%>
            <div id = simple>  
                 <jsp:include page="../../includes/topSliderImages.jsp" />
            </div>
            <%-- Slider ends --%>
            
            <h1>User's Contact Us Page is under construction</h1>
            
            <%-- Sidebar Menu Starts 
            <jsp:include page="../../includes/verticalUserMenu.jsp" />       
            <%-- Sidebar Menu Ends --%>

             <%-- adds Top Slider, login functions --%>
             <jsp:include page="../../includes/js.jsp" /> 

             <%-- footer.jspf integrates here --%>