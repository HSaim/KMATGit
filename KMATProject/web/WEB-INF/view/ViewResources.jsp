<%-- 
    Document   : ViewResources
    Created on : Nov 16, 2016, 10:43:19 AM
    Author     : Habiba Saim, Fahad Akhtar
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.ResourceBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Code to prevent user from accessing any user specific page after logout/session-end -->
<%
    response.setHeader("Pragma","no-cache"); 
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader("Expires",-1);
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
        
        <title>View Resources</title>
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
                        Resources
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                 <!--Access users from session -->
                <% 
                    ArrayList<ResourceBean> resource = new ArrayList<ResourceBean>();
                    if (session.getAttribute("resource") != null) {
                        resource = (ArrayList<ResourceBean> ) session.getAttribute("resource");
                    }
                    //else{
                      //   users = (ArrayList<UserBean> ) session.getAttribute("users");
                    //}
               %>
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
                            <!--Display of Resourcename Edit Delete Buttons-->
                            <div class = "row">
                                <b>
                                <div class="col-md-6">
                                    <b>Resource Name</b>                                    
                                </div>
                                <div class = "col-md-3">
                                    Edit
                                </div>
                                <div class = "col-md-3">
                                    Delete
                                </div>
                                </b>
                            
                        </div>
                        <hr/>
                        
                        <c:forEach var="user" items="${users}">
                                <div class = "row">
                                    <div class="col-md-6">
                                        <h3><c:out value="${resource.getResourceName()}" /></h3>
                                        <%--<input type = "hidden" value = "${resource.getResourceName()}" name = "hidden">--%>
                                        <input type = "hidden" value = "resourcename" name = "hide">
                                    </div>
                                    <div class = "col-md-3">
                                        <input type = "hidden" value = "resourcename" name = "hidden">
                                        
                                        <%--<a href = "edit-user?x=${user.getUserName()}">--%>
                                        <a href = "GetResourceController?action=get-user&userName=${resource.getResourceName()}">
                                            <h2><i class="icon-edit"></i></h2>
                                        </a>
                                    </div>
                                    <div class = "col-md-3">
                                        <a href ="">
                                        <h2><i class="icon-remove-user"></i></h2>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>
                        
                        <%--
                            <% 
                                ArrayList<UserBean> users = new ArrayList<UserBean>();
                                if (session.getAttribute("users") != null) {
                                    users = (ArrayList<UserBean> ) session.getAttribute("users");
                                }
                                //else{
                                  //   users = (ArrayList<UserBean> ) session.getAttribute("users");
                                //}
                           %>
                        <div align="center">
                                <table border="1" cellpadding="5">
                                    <caption><h2>List of users</h2></caption>
                                    <tr>
                                        <th>ID</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Profession</th>
                                    </tr>
                                    <c:forEach var="user" items="${users}">
                                        <tr>
                                            <td><c:out value="${user.getUserName()}" /></td>
                                            <%--<td><c:out value="${user.name}" /></td>
                                            <td><c:out value="${user.email}" /></td>
                                            <td><c:out value="${user.profession}" /></td>
                                           
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                                            --%>
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


