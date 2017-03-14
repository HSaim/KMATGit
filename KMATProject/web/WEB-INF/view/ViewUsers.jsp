<%-- 
    Document   : ViewUsers
    Created on : Nov 15, 2016, 11:50:32 AM
    Author     : Habiba Saim
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.UserBean"%>
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
	<meta name="description" content="Users in Knowledge Management for All Tool (KMAT)" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
        
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" /> 
        
        <title>Users</title>
    </head>
    
    <body class = "view-users">
        <!-- START wrapper -->
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START header -->
                <jsp:include page="../../includes/header.jsp" />                 
                <!-- END header -->
                
                <!-- START: Page heading-->
                <aside class="heading-bg" > <!--style="background: url(images/heading-bg1.jpg) repeat;">-->
                    <div class="container">
                        <!--<h1 class="page-heading-lead">-->
                       Users
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <!--Access users from session -->
                <% 
                    ArrayList<UserBean> rusers = new ArrayList<UserBean>();
                     ArrayList<UserBean> uusers = new ArrayList<UserBean>();
                    if (session.getAttribute("rusers") != null) {
                        rusers = (ArrayList<UserBean> ) session.getAttribute("rusers");
                    }
                    
                    if (session.getAttribute("uusers") != null) {
                        uusers = (ArrayList<UserBean> ) session.getAttribute("uusers");
                    }
                    //else{
                      //   users = (ArrayList<UserBean> ) session.getAttribute("users");
                    //}
               %>
                <div class="container">
                    <div class="row">                        
                        <div class="col-md-10 col-md-push-2">
                            <!-- Display of user names, edit and delete buttons for registered users-->
                            <h2 class="page-heading-lead">
                                <span class="border"></span>
                                Registered Users
                                <span class="border"></span>
                            </h2>
                            <div class = "row">
                                <b>
                                <div class="col-md-6">
                                    <b>User Name</b>                                    
                                </div>
                                <div class = "col-md-3">
                                    View/Edit Details
                                </div>
                                <div class = "col-md-3">
                                    Delete
                                </div>
                                </b>
                            </div>
                            <hr style="height:1px;border:none;color:#333;background-color:#a9a9a9;" />
                         
                            <c:forEach var="user" items="${rusers}" varStatus="loop">
                                <div class = "row">
                                    <div class="col-md-6">
                                        <h3><c:out value="${user.getUserName()}" /></h3>
                                        <%--<input type = "hidden" value = "${user.getUserName()}" name = "hidden">--%>
                                        <%--<input type = "hidden" value = "username" name = "hide">--%>
                                    </div>
                                    <div class = "col-md-3">
                                        <%--<input type = "hidden" value = "username" name = "hidden">--%>
                                        
                                        <%--<a href = "edit-user?x=${user.getUserName()}">--%>
                                        <a href = "GetUsersController?action=get-user&userName=${user.getUserName()}">
                                            <h2><i class="icon-edit"></i></h2>
                                        </a>
                                    </div>
                                    <div class = "col-md-3">
                                        <a href ="GetUsersController?action=del-user&userName=${user.getUserName()}">
                                            <h2><i class="icon-remove-user"></i></h2>
                                        </a>
                                    </div>
                                </div>
                                
                                <c:if test="${not loop.last}"><hr/></c:if> <%-- Add an <hr/ > if it is not the last item --%>
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
                            <!-- Display of user names, edit and delete buttons for unregistered users-->
                            <hr style="height:2px;border:none;color:#00c5cf;background-color:#00c5cf;" />
                            <h2 class="page-heading-lead">
                                <span class="border"></span>
                                Unregistered Users
                                <span class="border"></span>
                            </h2>
                            <div class = "row">
                                <b>
                                <div class="col-md-6">
                                    <b>User Name</b>                                    
                                </div>
                                <div class = "col-md-3">
                                    Register
                                </div>
                                <div class = "col-md-3">
                                    Delete
                                </div>
                                </b>
                            </div>
                            <hr style="height:1px;border:none;color:#333;background-color:#a9a9a9;" />
                            
                            <c:forEach var="uuser" items="${uusers}">
                                <div class = "row">
                                    <div class="col-md-6">
                                        <h3><c:out value="${uuser.getUserName()}" /></h3>
                                        <%--<input type = "hidden" value = "${user.getUserName()}" name = "hidden">--%>
                                        <%--<input type = "hidden" value = "username" name = "hide">--%>
                                    </div>
                                    <div class = "col-md-3">
                                        <%--<input type = "hidden" value = "username" name = "hidden">--%>
                                        
                                        <%--<a href = "edit-user?x=${user.getUserName()}">--%>
                                        <a href = "GetUsersController?action=get-uuser&userName=${uuser.getUserName()}">
                                            <h2><i class="icon-add-user"></i></h2>
                                        </a>
                                    </div>
                                    <div class = "col-md-3">
                                        <a href ="GetUsersController?action=del-uuser&userName=${uuser.getUserName()}&userEmail=${uuser.getPriEmail()}&userFName=${uuser.getFirstName()}">
                                            <h2><i class="icon-remove-user"></i></h2>
                                        </a>
                                    </div>
                                </div>
                                <hr/>
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

