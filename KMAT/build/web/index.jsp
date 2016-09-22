<%-- 
    Document   : index
    Created on : Jul 26, 2016, 11:46:29 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <title>KMAT-Home</title>
        
    </head>
    <body class = "home">
        <div class = "wrapper">
            <%-- Menubar starts --%>
            <jsp:include page="includes/menubar.jsp" />
            <%-- Menubar Ends --%>

            <%-- Slider starts --%>
            <div id = simple>  
                 <jsp:include page="includes/topSliderImages.jsp" />
            </div>
            <%-- Slider ends --%>

             <%-- adds Top Slider, login functions --%>
             <jsp:include page="includes/js.jsp" /> 

             <%-- footer.jspf integrates here --%>
        
   
