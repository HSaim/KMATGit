<%-- 
    Document   : Home
    Created on : Oct 28, 2016, 11:56:54 AM
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
        
        <jsp:include page="includes/link.jsp" /> 
        
        
    </head>
    <body class = "home">
        <!-- START wrapper -->
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START header -->
                 <%--<jsp:include page="includes/header.jsp" /> --%> 
                 <%@ include file="includes/header.jsp" %>
                <!-- END header -->
                 <!-- Slider Starts-->
                
                <%@ include file="includes/slider.jsp" %>
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
                                        <img src="images/slide_2.jpg" alt="Composition Ladder" class="img-responsive">
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
                                        <img src="images/slide_3.jpg" alt="slide" class="img-responsive">
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
                                        <img src="images/slide_2.jpg" alt="slide" class="img-responsive">
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
                                        <img src="images/slide_3.jpg" alt="slide" class="img-responsive">
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
            <!-- END: KMAT Components -->
           
                <!-- START: Sign in -->
                <div id="get-signin">
                    <div class="container">
                        <div class="row text-center signin-heading">
                            <div class="col-md-8 col-md-offset-2">
                                <!--<lable id = "invlalid-signin"></lable>-->
                                <h2 class="text-styled">Sign in</h2>
                            </div>
                        </div>
                        <div class="get-signin">
                            <form class="form-inline" method="post" action="LoginController" >
                                <div class="col-md-4 col-sm-4">
                                    <div class="form-group">
                                        <label for="username" class="sr-only">User Name</label>
                                        <input type="text" class="form-control" name="username" id="name" placeholder="User Name">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <div class="form-group">
                                        <label for="password" class="sr-only">Password</label>
                                        <input type="password" class="form-control" name = "password" id="password" placeholder="Password">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <button type="submit" class="btn btn-default btn-block">Login</button>
                                </div>
                            </form>                            
                        </div>
                        <div class="row text-center">
                            <div class="col-md-8 col-md-offset-2">                                    
                                    <p>No account? <a href="SignUp.jsp" class="btn btn-primary btn-lg">Sign Up</a></p>

                            </div>
                        </div>
                        
                    </div>
                </div>
                <!-- END: Sign in -->
                
                <!-- adds js -->                
                <%@ include file="includes/js.jsp" %>
                
                <!-- footer.jspf integrates here -->
                
                