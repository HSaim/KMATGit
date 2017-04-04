<%-- 
    Document   : ConceptMap
    Created on : Feb 16, 2017, 6:38:52 PM
    Author     : Habiba Saim
--%>

<!-- Code to prevent user from accessing any user specific page after logout/session-end -->
<%
    response.setHeader("Cache-Control","no-cache");  //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store");  //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires",-1);            //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");         //HTTP 1.0 backward compatibility
%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">        
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="KMAT Concept Maps Introduction" />
	<meta name="keywords" content="KMAT, concepts, maps, relations,  relationships, nodes, edges, graphical, model" />
	<meta name="author" content="KMAT Team" />
        
        
        <jsp:include page="includes/link.jsp" />    
        <link rel="stylesheet" href="css/banner.css">
        <title>Concept Map</title>       
    </head>
    <body>
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START: header -->                
                <jsp:include page="includes/header.jsp" />                      
                <!-- END: header -->
                
                <!-- START: Banner -->
                
                 <div id="banner" style="background-image:  url(images/conceptmappingbanner.jpg); background-repeat: no-repeat; background-position:center; background-size: cover;">
                    <%--
                     <div class="container">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2">
                                <div class="banner-wrap">
                                    <div class="banner-intro">
                                        <h2>Concept Maps<span></span></h2>                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    --%>
		</div>
                
                <!--<div class="da-slider" style="background-image: url(images/CompositionLadder3.jpg); background-repeat: no-repeat; background-position:center;"></div>
                <!-- END: Banner-->
                
                <!-- START: Recover Account Form -->
                <div id="contact-section">
                    <div class="container">
                        <div class="row text-center">                            
                            <h1 class = "subTitle">What is a Concept Map?</h1>
                        </div>
                        
                         <div class="row">
                            <div class="col-md-12">
                                   A concept map is a way of representing relationships between ideas, images, or words 
                                   in the same way that a sentence diagram represents the grammar of a sentence, 
                                   a road map represents the locations of highways and towns, 
                                   and a circuit diagram represents the workings of an electrical appliance. 
                                   
                                   In a concept map, each word or phrase connects to another, and links back to the original idea, 
                                   word, or phrase. 
                                   
                                   It is a graphical model used to organize and structure knowledge. 
                                   
                                   Different concepts are represented by nodes and the relationship between them is shown with edges. 
                                   
                                   The relationship needs to be visible so that the viewer knows how the two nodes are linked. 
                                   This is different from a composition ladder or a process ladder since each has specified relationships between them.
                                   
                                   Risk management concept map example is given below:
                                </div>
                            </div>
                            <div class="row text-center">
                                <div class="col-md-3">
                                     </div>
                                <div class="col-md-6">        
                                    
                                   
                                            <img  id = "component_img" src="images/Concept-map--Risk-management1.png">
                                </div>
                                <div class="col-md-3">
                                     </div>
                            </div>
                    </div>
                </div>
                <!-- END: Recover Account Form -->
                
                 <%-- adds js --%>
                <jsp:include page="includes/js.jsp" /> 
                
                
                <%-- footer.jspf integrates here --%>

