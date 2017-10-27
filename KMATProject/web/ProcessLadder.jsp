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
	<meta name="keywords" content="KMAT, process, ladder, function" />
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
                <%--<aside id="banner" style="background-image: url(images/DESIGN-PROCESS1.jpg); background-repeat: no-repeat; background-position:center;">
                   
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
                    
		</aside>--%>
                
                <aside id="hero" class="js-fullheight">
                    <div class="flexslider js-fullheight">
                        <ul class="slides">
                            <li style="background-image: url(images/DESIGN-PROCESS1.jpg); background-repeat: no-repeat; background-position:center; background-size: contain;">
                                <div class="overlay-gradient"></div>
                                <div class="container">
                                    <div class="col-md-10 col-md-offset-1 text-center js-fullheight slider-text">
                                        <div class="slider-text-inner desc">
                                               <h2 class="heading-section">Process Ladders</h2>			   					
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
		</aside>   
                <!-- END: Banner-->
                
                <!-- START: Process Ladder Contents -->
                <div id="contact-section">
                    <div class="container">
                        <div class="row text-center">                            
                            <h1 class = "subTitle">What is a Process Ladder?</h1>
                        </div>
                        
                        <table class=" tWiz" style="width: 100%" cellPadding=5 align=left>
                            <tbody>
                                <tr>
                                    A Process is a set of interrelated activities that interact to achieve a result, and is simply known as "program in excitation".
                            
                            It is a data structure that can be used to model a complete process such as student’s course enrollment, Point-of-Sale transaction, forest preservation/management etc.
                                </tr>
                                <tr>
                                    <td style="width: 40%; vertical-align: top; text-align:  center">
                                            <img id="component_img" src="images/process-ladder-example.png">
                                    </td>
                                    <td style="vertical-align: top; text-align: left">
                                        The process ladder is composed of pre-existing or already defined functions that let users complete a job. 
                                        An example of a process ladder is shown in the figure, where the process for monitoring crops health is given.

                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- END: Process Ladder Contents -->
                
                 <%-- adds js --%>
                <jsp:include page="includes/js.jsp" /> 
                
                
                <%-- footer.jspf integrates here --%>

