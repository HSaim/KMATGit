<%-- 
    Document   : AddLadder
    Created on : Sep 22, 2016, 10:17:31 AM
    Author     : Habiba Saim
--%>


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
        <title>Add Ladder</title>
    </head>
    <body class = "addladder">
        <div class = "wrapper">
            <%-- Menubar starts --%>
           <jsp:include page="../../includes/menubarUser.jsp" />
           <%-- Menubar Ends --%>

           <%-- Slider starts --%>
           <div id = simple>  
                <jsp:include page="../../includes/topSliderImages.jsp" />
           </div>

           <%-- Sidebar Menu Starts --%>
           <jsp:include page="../../includes/verticalUserMenu.jsp" />
           <%-- Sidebar Menu Ends --%>

           <%-- Add Resource Block Starts --%>   
           <div align = "center" id = "Login">
             <h3> Add Ladder page is under construction.</h3>
           </div>
           <%-- Add Resource Block Ends --%> 

           <%-- adds Top Slider, login functions --%>
           <jsp:include page="../../includes/js.jsp" /> 
           
           <%-- footer.jspf integrates here --%>
