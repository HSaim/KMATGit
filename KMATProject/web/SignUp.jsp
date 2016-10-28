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
                <!-- END: Banner-->
                
                <!-- START: Signup Form -->
                <div id="contact-section">
                    <div class="container">
                        <div class="row text-center">                            
                            <h2 >Create an account</h2>                            
                        </div>
                        <form action="#">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="First Name">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="Last Name">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">                                            
                                        <input type="text" class="form-control" placeholder="User Name">                                    
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                            <!--<textarea name="" class="form-control" id="" cols="30" rows="7" placeholder="Message"></textarea>-->
                                            <input type="password" class="form-control" placeholder="Password">
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                            <!--<textarea name="" class="form-control" id="" cols="30" rows="7" placeholder="Message"></textarea>-->
                                            <input type="password" class="form-control" placeholder="Re-enter Password">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Primary Email">
                                        </div>
                                </div>
                                <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Secondary Email">
                                        </div>
                                </div>
                                <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Postal Address">
                                        </div>
                                </div>
                                <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Permanent Address">
                                        </div>
                                </div>
                                <div class="col-md-4">
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Work Phone">
                                        </div>
                                </div>
                                <div class="col-md-4">
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Mobile Phone">
                                        </div>
                                </div>
                                <div class="col-md-4">
                                        <div class="form-group">
                                            <input type="text" class="form-control" placeholder="Home Phone">
                                        </div>
                                </div>
                                <div class="col-md-12">
                                        <div class="form-group">
                                            <input type="submit" value="Creat Account" class="btn btn-primary">
                                        </div>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
                <!-- END: Signup Form -->
                 <%-- adds js --%>
                <jsp:include page="includes/js.jsp" /> 

                <%-- footer.jspf integrates here --%>
