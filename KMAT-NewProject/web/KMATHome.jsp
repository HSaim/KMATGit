<%-- 
    Document   : KMATHome
    Created on : Oct 28, 2016, 11:31:45 AM
    Author     : Saim
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
        
        <!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
    </head>
    <body>
        <!-- START wrapper -->
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START header -->
                 <jsp:include page="includes/header.jsp" />    
                <!-- END header -->
                 <!-- Slider Starts-->
                <jsp:include page="includes/slider.jsp" /> 
                <!-- Slider Ends -->
                <!-- Slider Starts-
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
            <!-- END: KMAT Components -->
            
            <!-- START: Sign in 
            <div class="parallax" style="background-image: url(images/hero4.jpg);" >
                <div class="overlay"></div>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 col-md-offset-0 col-sm-12 col-sm-offset-0 col-xs-12 col-xs-offset-0 text-center table">
                                <div class="intro table-cell animate-box">
                                    <h2 class="text-styled">Sign in</h2>
                                    <p>Made with love by the fine folks at <a href="http://freehtml5.co">FreeHTML5.co</a></p>
                                    <form class="form-inline">
                                        <div class="col-md-4 col-sm-4">
                                            <div class="form-group">
                                                <label for="name" class="sr-only">Name</label>
                                                <input type="text" class="form-control" id="name" placeholder="Name">
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-sm-4">
                                            <div class="form-group">
                                                <label for="email" class="sr-only">Email</label>
                                                <input type="email" class="form-control" id="email" placeholder="Email">
                                            </div>
                                        </div>
                                        <div class="col-md-4 col-sm-4">
                                            <button type="submit" class="btn btn-default btn-block">Login</button>
                                        </div>
					</form>
                                    <p><a href="#" class="btn btn-primary btn-lg">Get started</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
		</div>
            <!-- END: Sign in-->
                <!-- START: Sign in -->
                <div id="get-signin">
                    <div class="container">
                        <div class="row text-center signin-heading">
                            <div class="col-md-8 col-md-offset-2">
                                    <h2 class="text-styled">Sign in</h2>

                            </div>
                        </div>
                        <div class="get-signin">
                            <form class="form-inline">
                                <div class="col-md-4 col-sm-4">
                                    <div class="form-group">
                                        <label for="email" class="sr-only">Email</label>
                                        <input type="email" class="form-control" id="name" placeholder="Email">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <div class="form-group">
                                        <label for="password" class="sr-only">Password</label>
                                        <input type="password" class="form-control" id="email" placeholder="Password">
                                    </div>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <button type="submit" class="btn btn-default btn-block">Login</button>
                                </div>
                            </form>                            
                        </div>
                        <div class="row text-center">
                            <div class="col-md-8 col-md-offset-2">                                    
                                    <p><a href="#" class="btn btn-primary btn-lg">Sign Up</a></p>

                            </div>
                        </div>
                        
                    </div>
                </div>
                <!-- END: Sign in -->
                
                <!-- adds js -->                
                <jsp:include page="includes/js.jsp" /> 
                
                