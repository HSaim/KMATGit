<%-- 
    Document   : UserHome
    Created on : Oct 18, 2016, 11:58:49 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <meta charset="utf-8">
        
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>KMAT User-Home</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Knowledge Management for All Tool (KMAT)" />
	<meta name="keywords" content="KMAT, resource, concept, concept map, list, tool" />
	<meta name="author" content="KMAT Team" />
        
        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">
        
       <!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Simple Line Icons -->
	<link rel="stylesheet" href="css/simple-line-icons.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Superfish -->
	<link rel="stylesheet" href="css/superfish.css">
	<!-- Flexslider  -->
	<link rel="stylesheet" href="css/flexslider.css">
        <!-- Homeslider  
	<link rel="stylesheet" href="css/homeslider.css">-->
	<link rel="stylesheet" href="css/style.css">
        
        <!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
    </head>
    <body>
         <!-- START wrapper -->
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START header -->
                <jsp:include page="includes/user-header.jsp" />   
                
                <!-- END header -->
                
                <!--
                <aside class="page-heading">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h1 class="page-heading-lead">
                                    Welcome User!
                                    <span class="border"></span>
                                </h1>
                            </div>
                        </div>
                    </div>
                </aside>
                -->
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
                            <h1 class="page-heading-lead">
                                User's Home Page
                                <span class="border"></span>
                            </h1>
                            <h2>Knowledge Management for All Tool (K-MAT) basic features </h2>
                            
                            <p>
                               <br/>
                                * Provide industry with a low cost KM software solution for SMEs
                                <br/>
                                * Readily integrate to most common existing DIMS, and legacy systems
                                <br/>
                                * Adapt to any SME sector with complete KM control
                                <br/>
                                * Will be easily manageable by SMEs without incurring specialized human resource cost.
                            </p>                          
                        </div>
                        <div class="col-md-2 col-md-pull-10" style="background-color:lavender;">
                            <%-- <jsp:include page="includes/verticalUserMenu.jsp" />  --%>
                            <!-- Left side bar includes here -->
                            <jsp:include page="includes/left-sidebar.jsp" />
                        </div>               
                        
                    </div>
                </div>
                          
                 <!-- START footer -->
                <footer id="footer">
                    <div class="container">
			<div class="row">
                            <div class="col-md-4">
				<div class="copyright">
                                    <p><small>
                                        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
                                        <jsp:useBean id="date" class="java.util.Date" />
                                        <fmt:formatDate value="${date}" pattern="yyyy" /> 
                                        &copy; Rice Lab,<br/> Lahore University of Management Sciences, Lahore, Pakistan. <br/>All Rights Reserved. 
                                    </small></p>
				</div>
                            </div>
                                        
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-5">
                                        <h3>About Us</h3>
                                        <p>We provide knowledge management system for SME</p>
                                        <p><a href="#" class="btn btn-primary btn-outline with-arrow btn-sm">I'm button <i class="icon-arrow-right"></i></a></p>
                                    </div>
                                    <div class="col-md-5">
                                            <h3>KMAT Features</h3>
                                            <ul class="link">
                                                    <li><a href="#">Process Ladders</a></li>
                                                    <li><a href = "">Composition Ladders</a></li>
                                                    <li><a href="#">Concept Maps</a></li>
                                                    <li><a href="#">Resources</a></li>
                                                    <li><a href="#">Tools</a></li>
                                            </ul>
                                    </div>

                                </div>
                            </div>
                            <div class="col-md-2">
                                    <ul class="social-icons">
                                            <li>
                                                    <a href="#"><i class="icon-twitter-with-circle"></i></a>
                                                    <a href="#"><i class="icon-facebook-with-circle"></i></a>
                                                    <a href="#"><i class="icon-instagram-with-circle"></i></a>
                                                    <a href="#"><i class="icon-linkedin-with-circle"></i></a>
                                            </li>
                                    </ul>
                            </div>
                        </div>
                    </div>
		</footer>                
                <!-- END footer -->	
                </div>
            <!-- END page -->
        </div>
        <!-- END wrapper -->
        
       <!-- jQuery -->

	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Superfish -->
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.js"></script>
	<!-- Flexslider -->
	<script src="js/jquery.flexslider-min.js"></script>
	<!-- Stellar -->
	<script src="js/jquery.stellar.min.js"></script>
	<!-- Counters -->
	<script src="js/jquery.countTo.js"></script>

	<!-- Main JS  -->
	<script src="js/main.js"></script>
    </body>
</html>
