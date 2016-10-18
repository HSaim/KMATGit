<%-- 
    Document   : home
    Created on : Oct 13, 2016, 12:12:56 PM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <meta charset="utf-8">
        
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>KMAT-Home</title>
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
                <div id = "header">
                    <!-- START header-section -->
                    <header id="header-section">
                        <!-- START container -->
                        <div class="container">
                            <div class="nav-header">
                                <a href="#" class="js-nav-toggle nav-toggle"><i></i></a>
                                <h1 id="logo"><a href="home.jsp">KMAT</a></h1>
                                <!-- START #menu-wrap -->
                                <nav id="menu-wrap" role="navigation">
                                    <ul class="sf-menu" id="primary-menu" >
                                       
                                        <li><a class="active" href="home.jsp">Home</a></li>
                                        <li><a href="">News</a></li>
                                        <li>
                                            <a href="" class="fh5co-sub-ddown">Site Map </a>
                                            <ul class="sub-menu">
                                                <li><a href="">sub menu 1</a></li>
                                                
                                                <li>
                                                    <a href="#" class="fh5co-sub-ddown">sub menu 2</a>
                                                    <ul class="sub-menu">
                                                            <li><a href="" >Build</a></li>
                                                            <li><a href="">Work</a></li>
                                                            <li><a href="">Light</a></li>
                                                            <li><a href="">Relic</a></li>
                                                            <li><a href="">Display</a></li>
                                                            <li><a href="">Sprint</a></li>
                                                    </ul>
                                                </li>                                                
                                            </ul>
                                        </li>
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                    <li class="cta"><a href="">Login</a></li>
                                </ul>
                                </nav>
                                <!-- END menu-wrap -->
                            </div>
                        </div>
                        <!-- END container -->
                    </header> 
                    <!-- END header-section -->
                </div>
                <!-- END header -->
                
                <!-- Slider Starts-->
		<aside id="hero" class="js-fullheight">
                    <div class="flexslider js-fullheight">
                        <ul class="slides">
                            <li style="background-image: url(images/slide_5.jpg);">
                                <div class="overlay-gradient"></div>
                                <div class="container">
                                        <div class="col-md-10 col-md-offset-1 text-center js-fullheight slider-text">
                                                <div class="slider-text-inner">
                                                        <h2>Knowledge Management for All Tool</h2>
                                                        <p><a href="#" class="btn btn-primary btn-lg">Get started</a></p>
                                                </div>
                                        </div>
                                </div>
                            </li>
                            <li style="background-image: url(images/slide_2.jpg);">
                                    <div class="overlay-gradient"></div>
                                <div class="container">
                                        <div class="col-md-10 col-md-offset-1 text-center js-fullheight slider-text">
                                                <div class="slider-text-inner">
                                                        <h2>Composition Ladder</h2>
                                                        <p><a href="#features" class="btn btn-primary btn-lg">Learn more</a></p>
                                                </div>
                                        </div>
                                </div>
                            </li>
                            <li style="background-image: url(images/slide_3.jpg);">
                                <div class="overlay-gradient"></div>
                                <div class="container">
                                        <div class="col-md-10 col-md-offset-1 text-center js-fullheight slider-text">
                                                <div class="slider-text-inner">
                                                        <h2>Concept map</h2>
                                                        <p><a href="#features" class="btn btn-primary btn-lg">Learn more</a></p>
                                                </div>
                                        </div>
                                </div>
                            </li>
                            <li style="background-image: url(images/slide_4.jpg);">
                                <div class="overlay-gradient"></div>
                                <div class="container">
                                        <div class="col-md-10 col-md-offset-1 text-center js-fullheight slider-text">
                                                <div class="slider-text-inner">
                                                        <h2>Resources</h2>
                                                        <p><a href="#features" class="btn btn-primary btn-lg">Learn more</a></p>
                                                </div>
                                        </div>
                                </div>
                            </li>
                        </ul>
                    </div>
		</aside>
                <!-- Slider Ends -->
                
                <!-- KMAT Components Starts-->
                <div id="features">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2 text-center features-heading animate-box" data-animate-effect="fadeIn">
                                <h2>KMAT Features</h2>
                                <p>KMAT provides following basic components. <br> Signup to use them.</p>
                            </div>
                           
                            <div class="col-md-4 item-block animate-box" data-animate-effect="fadeIn">
                                <div class="sub-feature">

                                    <figure>
                                        <img src="images/slide_3.jpg" alt="Free Website Templates FreeHTML5.co" class="img-responsive">
                                        <!--<a href="#" class="tag">For Sale</a>-->
                                    </figure>
                                    <div class="sub-feature-innter">
                                        <h3><a href="#">Process Ladders</a></h3>
                                        <div class="price-status">
                                            <span class="price">Organization's processes</span>
                                        </div>
                                        <p>Represent the processes being run in an organization</p>
                                    </div>
                                    <p class="sub-feature-specification">
                                            <!--
                                            <span><strong>3500</strong> Sq Ft</span>  <span><strong>3</strong> Beds</span>  <span><strong>3.5</strong> Baths</span>  <span><strong>2</strong> Garages</span>
                                            -->
                                    </p>
                                </div>					
                            </div>
                            
                            <div class="col-md-4 item-block animate-box" data-animate-effect="fadeIn">
                                <div class="sub-feature">

                                    <figure>
                                        <img src="images/slide_2.jpg" alt="Free Website Templates FreeHTML5.co" class="img-responsive">
                                        <!--<a href="#" class="tag">For Sale</a>-->
                                    </figure>
                                    <div class="sub-feature-innter">
                                        <h3><a href="#">Composition Ladders</a></h3>
                                        <div class="price-status">
                                            <span class="price">Organization's processes</span>
                                        </div>
                                        <p>Represent the processes being run in an organization</p>
                                    </div>
                                    <p class="sub-feature-specification">
                                            <!--
                                            <span><strong>3500</strong> Sq Ft</span>  <span><strong>3</strong> Beds</span>  <span><strong>3.5</strong> Baths</span>  <span><strong>2</strong> Garages</span>
                                            -->
                                    </p>
                                </div>					
                            </div>
                           
                            <div class="col-md-4 item-block animate-box" data-animate-effect="fadeIn">
                                <div class="sub-feature">

                                    <figure>
                                        <img src="images/slide_3.jpg" alt="Free Website Templates FreeHTML5.co" class="img-responsive">
                                        <!--<a href="#" class="tag">For Sale</a>-->
                                    </figure>
                                    <div class="sub-feature-innter">
                                        <h3><a href="#">Concept Maps</a></h3>
                                        <div class="price-status">
                                            <span class="price">Organization's processes</span>
                                        </div>
                                        <p>Represent the processes being run in an organization</p>
                                    </div>
                                    <p class="sub-feature-specification">
                                            <!--
                                            <span><strong>3500</strong> Sq Ft</span>  <span><strong>3</strong> Beds</span>  <span><strong>3.5</strong> Baths</span>  <span><strong>2</strong> Garages</span>
                                            -->
                                    </p>
                                </div>					
                            </div>
                        </div>
                        
                        <div class="row">
                            
                            <div class="col-md-4 item-block animate-box" data-animate-effect="fadeIn">
                                <div class="sub-feature">
                                    <figure>
                                        <img src="images/slide_3.jpg" alt="Free Website Templates FreeHTML5.co" class="img-responsive">
                                        <!--<a href="#" class="tag">For Sale</a>-->
                                    </figure>
                                    <div class="sub-feature-innter">
                                        <h3><a href="#">Resources</a></h3>
                                        <div class="price-status">
                                            <span class="price">Organization's processes</span>
                                        </div>
                                        <p>Represent the processes being run in an organization</p>
                                    </div>
                                    <p class="sub-feature-specification">
                                            <!--
                                            <span><strong>3500</strong> Sq Ft</span>  <span><strong>3</strong> Beds</span>  <span><strong>3.5</strong> Baths</span>  <span><strong>2</strong> Garages</span>
                                            -->                                   
                                    </p>
                                </div>					
                            </div>
                            
                            <div class="col-md-4 item-block animate-box" data-animate-effect="fadeIn">
                                <div class="sub-feature">
                                    <figure>
                                        <img src="images/slide_2.jpg" alt="Free Website Templates FreeHTML5.co" class="img-responsive">
                                        <!--<a href="#" class="tag">For Sale</a>-->
                                    </figure>
                                    <div class="sub-feature-innter">
                                        <h3><a href="#">Tools</a></h3>
                                        <div class="price-status">
                                            <span class="price">Organization's processes</span>
                                        </div>
                                        <p>Represent the processes being run in an organization</p>
                                    </div>
                                    <p class="sub-feature-specification">
                                        <!--
                                        <span><strong>3500</strong> Sq Ft</span>  <span><strong>3</strong> Beds</span>  <span><strong>3.5</strong> Baths</span>  <span><strong>2</strong> Garages</span>
                                        -->
                                    </p>
                                </div>					
                            </div>
                           
                            <div class="col-md-4 item-block animate-box" data-animate-effect="fadeIn">
                                <div class="sub-feature">
                                    <figure>
                                        <img src="images/slide_3.jpg" alt="Free Website Templates FreeHTML5.co" class="img-responsive">
                                        <!--<a href="#" class="tag">For Sale</a>-->
                                    </figure>
                                    <div class="sub-feature-innter">
                                        <h3><a href="#">Concept Maps</a></h3>
                                        <div class="price-status">
                                            <span class="price">Organization's processes</span>
                                        </div>
                                        <p>Represent the processes being run in an organization</p>
                                    </div>
                                    <p class="sub-feature-specification">
                                        <!--
                                        <span><strong>3500</strong> Sq Ft</span>  <span><strong>3</strong> Beds</span>  <span><strong>3.5</strong> Baths</span>  <span><strong>2</strong> Garages</span>
                                        -->
                                    </p>
                                </div>					
                            </div>

                        </div>
                    </div>
            </div>            
            <!-- KMAT Components Ends-->
                
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

	<!-- Main JS (Do not remove) -->
	<script src="js/main.js"></script>
    </body>
</html>
