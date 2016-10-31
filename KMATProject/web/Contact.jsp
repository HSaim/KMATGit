<%-- 
    Document   : Contact
    Created on : Oct 26, 2016, 12:11:44 PM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">        
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Knowledge Management for All Tool (KMAT)" />
	<meta name="keywords" content="KMAT, resource, concept, concept map, list, tool" />
	<meta name="author" content="KMAT Team" />        
        
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
        
        <link rel="stylesheet" href="css/top-banner.css">
        <!-- Modernizr JS -->
<script type="text/javascript" src="js/modernizr-2.6.2.min.js"></script>
        
        <title>Contact Us</title>
    </head>
    <body>
        <!-- START wrapper -->
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START: header -->                
                <jsp:include page="includes/header.jsp" />                      
                <!-- END: header -->
                
                <!-- START: Banner-->
                <aside id="hero" class="js-fullheight">
			<div class="flexslider js-fullheight">
				<ul class="slides">
			   	<li style="background-image: url(images/hero4.jpg);">
			   		<div class="overlay-gradient"></div>
			   		<div class="container">
			   			<div class="col-md-10 col-md-offset-1 text-center js-fullheight slider-text">
			   				<div class="slider-text-inner desc">
			   					<h2 class="heading-section">Contact KMAT people</h2>			   					
			   				</div>
			   			</div>
			   		</div>
			   	</li>
			  	</ul>
		  	</div>
		</aside>                
                <!-- END: Banner-->
                
                <!-- START: Contact Section -->
                <div id="contact-section">
			<div class="container">
				<form action="#">
					<div class="row">
						<div class="col-md-6 col-md-push-6">
							<h3 class="section-title">Our Address</h3>
							
							<ul class="contact-info">
								<li><i class="icon-location-pin"></i>KADE Lab, LUMS, Lahore</li>
								<li><i class="icon-phone2"></i>+92 42 3560 8000</li>
								<li><i class="icon-mail"></i><a href="#">info@kmat.com</a></li>
								<li><i class="icon-globe2"></i><a href="#">www.kmat.com</a></li>
							</ul>
						</div>
						<div class="col-md-6 col-md-pull-6">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Name">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="text" class="form-control" placeholder="Email">
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<textarea name="" class="form-control" id="" cols="30" rows="7" placeholder="Message"></textarea>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="submit" value="Send Message" class="btn btn-primary">
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!--<div id="map" class="map"></div>-->
                <!-- END: Contact Section -->
                
                <!-- Google Map 
	<script src="https://www.google.com/maps/place/Syed+Babar+Ali+School+of+Science+and+Engineering/@31.4704134,74.4101586,17z/data=!4m5!3m4!1s0x391908a8ade1af71:0x72856b1733b2a6f9!8m2!3d31.471905!4d74.4112104?hl=en"></script>
	<script src="js/google_map.js"></script>
-->
                 <!-- adds js -->                
                <jsp:include page="includes/js.jsp" /> 
                <!-- footer.jspf integrates here -->
                
