<%-- 
    Document   : CompositionLadder
    Created on : Feb 16, 2017, 12:07:49 PM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">        
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="KMAT Composition Ladder Introduction" />
	<meta name="keywords" content="KMAT, composition, ladder, hierarchy" />
	<meta name="author" content="KMAT Team" />
        
        
        <jsp:include page="includes/link.jsp" />    
        <link rel="stylesheet" href="css/banner.css">
        <title>Composition Ladder</title>       
    </head>
    
    <body>
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START: header -->                
                <jsp:include page="includes/header.jsp" />                      
                <!-- END: header -->
                
                <!-- START: Banner -->
                <%--
                <div id="banner" style="background-image:  url(images/CompositionLadder3.jpg); background-repeat: no-repeat; background-position:center; background-size: contain;">
                    
                     <div class="container">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2">
                                <div class="banner-wrap">
                                    <div class="banner-intro">
                                        <h2>Composition Ladders<span></span></h2>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
		</div>
                --%>
                <!--<div class="da-slider" style="background-image: url(images/CompositionLadder3.jpg); background-repeat: no-repeat; background-position:center;"></div>-->
                <aside id="hero" class="js-fullheight">
                    <div class="flexslider js-fullheight">
                        <ul class="slides">
                            <li style="background-image: url(images/CompositionLadder2.jpg); background-repeat: no-repeat; background-position:center; background-size: contain;">
                                <div class="overlay-gradient"></div>
                                <div class="container">
                                    <div class="col-md-10 col-md-offset-1 text-center js-fullheight slider-text">
                                        <div class="slider-text-inner desc">
                                               <h2 class="heading-section">Composition Ladders</h2>			   					
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
		</aside>  
                <!-- END: Banner-->
                
                <!-- START: Composition Ladder Contents-->
                <div id="contact-section">
                    <div class="container">
                        <div class="row text-center">                            
                            <h1 class = "subTitle">What is a Composition Ladder?</h1>
                        </div>
                        
                        <table class=" tWiz" style="width: 100%" cellPadding=5 align=left>
                            <tbody>
                                <tr>
                                   A composition ladder is a useful way of understanding complex entities such as machines, organizations and documents. 
                                </tr>
                                <tr>
                                    
                                    <td style="width: 50%; vertical-align: top; text-align: left">
                                        A composition is basically the proportion of different parts to make a whole; 
                                        or the general makeup of something. 
                                        It is a data structure that shows the parts a knowledge object is made of and 
                                        the hierarchy of composition that they form. 
                                        All relationships in the ladder are the ‘has’ part or part-of relationship. 
                                        An example is in linguistics, where the formation of compound words from separate words 
                                        make such a ladder. Figure 2 shows an example of a composition ladder for a human brain.

                                    </td>
                                    <br/><br/>
                                    <td style="vertical-align: top; text-align:  center">
                                            <img id="component_img" src="images/composition-ladder-example.png">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- END: Composition Ladder Contents -->
                
                 <%-- adds js --%>
                <jsp:include page="includes/js.jsp" /> 
                
                
                <%-- footer.jspf integrates here --%>

