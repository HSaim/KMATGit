<%-- 
    Document   : EditResource
    Created on : Mar 30, 2017, 3:44:41 PM
    Author     : Fahad Akhtar
--%>

<%@page import="model.ResourceBean"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@ page session="true" %>

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
	<meta name="description" content="Update the profile of a Resource" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
        
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <title>Edit Resource</title>
    </head>
    
   <body class = "view-resources">
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
                        Edit Resource
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
                           <%-- <input type = text value = "<%=request.getParameter("x")%>"> --%>
                           <%
                                ResourceBean resource = new ResourceBean();
                                //user = request.getParameter("user");
                                resource = (ResourceBean) session.getAttribute("ret-resource");
                            %>
                            <%--
                           <c:set var="currentResource" value="${resource}" />
                           <c:out value="${currentResource.getResourceName()}"/>
                            --%>
                    
                           <br/>
                           <form name = "editResource" method="post" enctype="multipart/form-data" action="GetResourcesController?action=update-resource&&resourceId=<%=resource.getResourceID()%>">                               
                            
     <input type="hidden" name="hidden" value="AddResource">
    <row>
    <div class="col-md-12">
        <div class="form-group">
            <input type = "text" class="form-control"placeholder="Name of Resource" name="addname" id="add-name" value = "<%=resource.getResourceName()%>" onkeyup="nospaces(this);">
    
        </div>
    </div>
        </row>
   
    <row>
    <div class="col-md-12">
        <div class="form-group">
            <textarea class="form-control" rows="10" id="discrp" name="add-description" value = "<%=resource.getResourceDiscription()%>" onkeyup="nospaces(this);"></textarea>
    
        </div>
    </div>
    </row>
    <row>
    <div class="col-md-1">
        <div class = "form-group">
            <input type="radio" class = "form-check-input" name="chk" value="file" id="chk1" onclick="enableDisable(this.checked,'inputbtn')">
        </div>
    </div>
    <div class="col-md-11">
        <div class="form-group">
            
            <input type = "file" name="datafile" id="inputbtn" class="form-control-file" value = "<%=resource.getFileName()%>" onkeyup="nospaces(this);">
    
        </div>
    </div>
        </row>
    
     <row>
    <div class="col-md-1">
        <div class = "form-group">
            <input type="radio" name="chk" id="chk1" onclick="enableDisable(this.checked,'view')"value="link" class = "form-check-input">
        </div>
    </div>
   
    <div class="col-md-8">
        <div class="form-group">
            
            <input type = "text" name="add-link" id="add-link" class="form-control"placeholder="Link of Resource" value = "<%=resource.getResourceLink()%>" onkeyup="nospaces(this);">
    
        </div>
    </div>
    <div class="col-md-3">
        <div class="form-group">
            
            <input type = "button" id="view" onclick="preview()" disabled class="form-control"value="Preview">
    
        </div>
    </div>
          </row>
   
     <row>
    <div class="col-md-12" >   
       <iframe id="frame-1" name="f1" style="width:100%; height:100%;"></iframe>
    </div>
            
       
 </row>
    
    <div class="row" style="margin-top: 5px">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="submit" value="Update" class="btn btn-primary">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="button" value="Cancel" class="btn btn-primary" onclick="window.location='view-resource'" >
                                        </div>
                                    </div>
                                </div>


</form>    
                                 
                                        
                                
                                    
                           
                            
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
                <script src="js/signup-verifications.js"></script> 
                
                <!-- footer.jspf integrates here -->


