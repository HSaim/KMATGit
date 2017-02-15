<%-- 
    Document   : ProcessLadder
    Created on : Feb 15, 2017, 4:30:44 PM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="utf-8">        
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="KMAT Process Ladder Introduction" />
	<meta name="keywords" content="KMAT, process, ladder" />
	<meta name="author" content="KMAT Team" />
        
        
        <jsp:include page="includes/link.jsp" />    
        <link rel="stylesheet" href="css/banner.css">
        <title>Process Ladder</title>       
    </head>
    
    <body>
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START: header -->                
                <jsp:include page="includes/header.jsp" />                      
                <!-- END: header -->
                
                <!-- START: Banner -->
                 <aside id="banner" style="background-image: url(images/banner1.jpg);">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2">
                                <div class="banner-wrap">
                                    <div class="banner-intro">
                                        <h2>Process Ladders<span></span></h2>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
		</aside>
                <!-- END: Banner-->
                
                <!-- START: Recover Account Form -->
                <div id="contact-section">
                    <div class="container">
                        <div class="row text-center">                            
                            <h2 >What is a process ladder</h2> 
                        </div>
                        <p>
                            A Process is a set of interrelated activities that interact to achieve a result, and is simply known as "program in excitation".
                            <br/>
                            It is a data structure that can be used to model a complete process such as student’s course enrollment, Point-of-Sale transaction, forest preservation/management etc.
                            <br/>
                            The process ladder is composed of pre-existing or already defined functions that let users complete a job. 
                            An example of a process ladder is shown in the figure above, where the process for monitoring crops health is given.
                        </p>
                        
                    </div>
                </div>
                <!-- END: Recover Account Form -->
                
                 <%-- adds js --%>
                <jsp:include page="includes/js.jsp" /> 
                
                
                <%-- footer.jspf integrates here --%>

