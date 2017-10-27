<%-- 
    Document   : ViewTools
    Created on : Nov 16, 2016, 10:03:33 AM
    Author     : Fahad Akhtar
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.ToolBean"%>
<%@page import="java.util.ArrayList"%>
<!-- Code to prevent user from accessing any user specific page after logout/session-end -->
<%
    response.setHeader("Cache-Control","no-cache");  //Forces caches to obtain a new copy of the page from the origin server
    response.setHeader("Cache-Control","no-store");  //Directs caches not to store the page under any circumstance
    response.setDateHeader("Expires",-1);            //Causes the proxy cache to see the page as "stale"
    response.setHeader("Pragma","no-cache");         //HTTP 1.0 backward compatibility
    
    if(session.getAttribute("CurrentSessionUser")==null){
    
        response.sendRedirect("Home.jsp");
    }
%>  

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Tools available in Knowledge Management for All Tool - KMAT" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
        
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <title>View Tools</title>
    </head>
    
   <body class = "view-tools">
        <!-- START wrapper -->
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START header -->
                <jsp:include page="../../includes/user-header.jsp" />                 
                <!-- END header -->
                
                <!-- START: Page heading-->
                <aside class="heading-bg" > <!--style="background: url(images/heading-bg1.jpg) repeat;">-->
                    <div class="container">
                        <!--<h1 class="page-heading-lead">-->
                         View Tools
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <div class="container">
                    <div class="row">
                       
                            <div class="col-md-10 col-md-push-2">
                                <div class="row">
                                    <b>
                                        <div class="col-md-6">
                                            <b>
                                                Tool Name
                                            </b>
                                        </div>
                                        <div class="col-md-3">
                                            <b>View Details</b>
                                        </div>
                                        <div class="col-md-3">
                                            <b>Delete</b>
                                        </div>
                                    </b>    
                                </div>
                                <hr style="height:1px;border:none;color:#333;background-color:#a9a9a9;"/>
                                <%
                                    ArrayList<ToolBean> tool1 = new ArrayList<ToolBean>();
                                    if(session.getAttribute("tools")!= null){
                                        tool1 = (ArrayList<ToolBean>)session.getAttribute("tools");
                                        
                                        
                                    }
                                    request.setAttribute("tool1", tool1);
                                    %>
                                    <c:forEach items = "${tool1}" var ="res">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <h3>
                                                    <a href="GetToolController?action=get-tool=${res.gettoolName()}">
                                                        <c:out value="${res.getToolName()}"/>
                                                    </a>
                                                </h3>
                                            </div>
                                            <div class="col-md-3">
                                                <a href="GetToolController?action=get-tool&toolName=${res.getToolname()}">
                                                    <h2>
                                                        <i class="icon-edit"></i>
                                                    </h2>
                                                </a>
                                            </div>       
                                            <div class="col-md-3">
                                                <a href="GetToolController?action=del-tool&toolName=${res.getToolname()}">
                                                    <h2>
                                                        <i class="icon-cross3"></i>
                                                    </h2>
                                                </a>
                                            </div>     
                                        </div>
                                    </c:forEach>
                                        
                            </div>
                            <div class="col-md-2 col-md-pull-10 back-color" >
                                <%-- <jsp:include page="includes/verticalUserMenu.jsp" />  --%>
                                <!-- Left side bar includes here -->
                                <jsp:include page="../../includes/left-sidebar.jsp" />
                            </div>
                                                
                    </div>
                </div>
                          
                <!-- adds js -->                
                <jsp:include page="../../includes/js.jsp" /> 
                
                <!-- footer.jspf integrates here -->


