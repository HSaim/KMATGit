<%-- 
    Document   : ViewResources
    Created on : Nov 16, 2016, 10:43:19 AM
    Author     : Habiba Saim, Fahad Akhtar
--%>



<%@page import="model.ResourceBean"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	<meta name="description" content="Resources available in Knowledge Management for All Tool - KMAT" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
        
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <title>Resources</title>
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
                        View Resources
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                 <!--Access users from session -->
                <%-- 
                    ArrayList<ResourceBean> vresource = new ArrayList<ResourceBean>();
                    if (session.getAttribute("resources") != null) {
                        vresource = (ArrayList<ResourceBean> ) session.getAttribute("resources");
                    }                
                   
               --%>
               <div class="container">
                   <div class="row">
                       <div class="col-md-10 col-md-push-2">
                           <!--Display of Resource Names, Edit and Delete Buttons. -->
                           
                           <div class="row">
                               <b>
                                   <div class="col-md-6">
                                       <b>Resource Name</b>
                                   </div>
                                   <div class="col-md-3">
                                       <b>View/Edit Details</b>
                                   </div>
                                   <div class="col-md-3">
                                       <b>Delete</b>
                                   </div>
                               </b>
                           </div>
                           <hr style="height:1px;border: none;color: #333;background-color: #a9a9a9;"/>                            
                            <% 
                    ArrayList<ResourceBean> resource1 = new ArrayList<ResourceBean>();
                    if (session.getAttribute("resources") != null) {
                        resource1 = (ArrayList<ResourceBean> ) session.getAttribute("resources");
                    }
                    request.setAttribute("resource1", resource1);
                   
               %>
                            <c:forEach  items="${resource1}" var="res">
                               <div class = "row">
                                    <div class="col-md-6">
                                        <h3> <a href = "GetResourceController?action=get-resource=${res.getResourceName()}">
                                            <c:out value="${res.getResourceName()}" /></a></h3>
                                        <%--<input type = "hidden" value = "${user.getUserName()}" name = "hidden">--%>
                                        <%--<input type = "hidden" value = "username" name = "hide">--%>
                                    </div>
                                    <div class = "col-md-3">
                                        <%--<input type = "hidden" value = "username" name = "hidden">--%>
                                        
                                        <%--<a href = "edit-user?x=${user.getUserName()}">--%>
                                        <a href = "GetResourceController?action=get-resource&resourceName=${res.getResourceName()}">
                                            <h2><i class="icon-edit"></i></h2>
                                        </a>
                                    </div>
                                    <div class = "col-md-3">
                                        <a href ="GetUsersController?action=del-resource&resourceName=${res.getResourceName()}">
                                            <h2><i class="icon-cross3"></i></h2>
                                        </a>
                                    </div>
                                </div>
                               <%--<c:if test="${not loop.last}"><hr/></c:if>  Add an <hr/ > if it is not the last item --%>
                           </c:forEach>
                                        
                       </div>
                       <div class="col-md-2 col-md-pull-10 back-color" >
                            <jsp:include page="../../includes/left-sidebar.jsp" />
                       
                       </div>
                      
                   </div>
                   
               </div>
               

                
                

                

                
                          

                <!--End Page contents -->        

                <!-- adds js -->                
                <jsp:include page="../../includes/js.jsp" /> 
                
                <!-- footer.jspf integrates here -->


