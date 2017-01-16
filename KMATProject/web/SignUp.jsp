<%-- 
    Document   : SignUp
    Created on : Oct 24, 2016, 1:05:39 PM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">        
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="KMAT Sign up page" />
	<meta name="keywords" content="KMAT, sign up, name, email, password, address, phone" />
	<meta name="author" content="KMAT Team" />
        
        
        <jsp:include page="includes/link.jsp" />    
        <link rel="stylesheet" href="css/banner.css">
        <title>Sign Up</title>       
        
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
                <%--
                <aside id="hero" class="js-fullheight">
                    <div class="flexslider js-fullheight">
                        <ul class="slides">
                            <li style="background-image: url(images/hero4.jpg);">
                                <div class="overlay-gradient"></div>
                                <div class="container">
                                    <div class="col-md-10 col-md-offset-1 text-center js-fullheight slider-text">
                                        <div class="slider-text-inner desc">
                                               <h2 class="heading-section">Sign up for KMAT</h2>			   					
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
		</aside>   
                --%>
                <aside id="banner" style="background-image: url(images/banner1.jpg);">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2">
                                <div class="banner-wrap">
                                    <div class="banner-intro">
                                        <h2>Sign up for <span></span></h2>
                                        <h1>KMAT</h1>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
		</aside>
                <!-- END: Banner-->
                
                <!-- START: Signup Form -->
                <div id="contact-section">
                    <div class="container">
                        <div class="row text-center">                            
                            <h2 >Create an account</h2>                            
                        </div>
                        <form name = "signup" method="post" action="InsertUserController" onSubmit="return verifyEmail()">
                            <input type="hidden" name="hidden" value="SignUp">
                            <!-- Includes Sign Up Form -->
                            <jsp:include page="includes/signup-form.jsp" />
                        </form>
                    </div>
                </div>
                <!-- END: Signup Form -->
                 <%-- adds js --%>
                <jsp:include page="includes/js.jsp" /> 
                <script src="js/signup-verifications.js"></script>
                <%-- footer.jspf integrates here --%>
