<%-- 
    Document   : EditUser
    Created on : Nov 25, 2016, 11:03:10 AM
    Author     : Habiba Saim
    Displays selected user profile to be edited by the knowledge engineer
--%>

<%@page import="model.UserBean"%> 
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
	<meta name="description" content="Update the profile of a KMAT user" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
        
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <title>Edit User</title>
    </head>
    
   <body class = "view-users">
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
                        Edit User
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
                                UserBean user = new UserBean();
                                //user = request.getParameter("user");
                                user = (UserBean) session.getAttribute("ret-user");
                            %>
                            <%--
                           <c:set var="currentUser" value="${user}" />
                           <c:out value="${currentUser.getUserName()}"/>
                            --%>
                    
                           <br/>
                           <form name = "editUser" method="post" action="GetUsersController?action=update-user&&userId=<%=user.getUserId()%>" onSubmit="return verifyEmail()">                               
                                
                                 <div class="row">
                                     
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="firstname">First Name*</label>
                                            <input type="text" class="form-control" id = "firstname" name="firstname" placeholder ="null" placeholder ="null" value = "<%=user.getFirstName() %>" required>
                                            
                                        </div>
                                    </div>
                                            
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="lastname">Last Name*</label>
                                            <input type="text" class="form-control" id = "lastname" name="lastname" placeholder ="null" value = "<%=user.getLastName() %>"  required>
                                          
                                        </div>
                                    </div>
                                            
                                    <div class="col-md-12">
                                        <div class="form-group">   
                                            <label for="user_name">User Name/User Id*</label>
                                            <input type="text" class="form-control" id = "user_name" name="user_name" placeholder ="null"  readonly value = "<%=user.getUserName() %>"  required>                                    
                                        </div>
                                    </div>                                        
                                    
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <!--<textarea name="" class="form-control" id="" cols="30" rows="7" placeholder="Message"></textarea>-->
                                            <label for="password">Password*</label>
                                            <input type="text" class="form-control" name="password"  placeholder ="null" id = "password"  value = "<%=user.getPassword() %>" required> 
                                        </div>
                                    </div>
                                     <%--
                                    <div class="col-md-12">
                                        <div class="form-group">
                                                <!--<textarea name="" class="form-control" id="" cols="30" rows="7" placeholder="Message"></textarea>-->
                                                <input type="password" class="form-control" id = "re-password" placeholder="Re-enter Password*" onChange="validatePassword();" required>
                                        </div>
                                        <div class="registrationFormAlert" id="divCheckPasswordMatch">
                                            </div>
                                    </div>
                                     --%>
                                     
                                    <div class="col-md-6">
                                        <div class="form-group">
                                             <label for="p_email">Primary Email*</label>
                                            <input type="email" class="form-control" id = "p_email" name="p_email" placeholder ="null" value = "<%=user.getPriEmail()%>" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for="s_email">Secondary Email</label>
                                            <input type="email" class="form-control" id = "s_email" name="s_email" placeholder ="null"  value = "<%=user.getSecEmail()%>">
                                        </div>
                                    </div>                                            
                                    
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for ="postal_address">Postal Address</label>
                                            <input type="text" class="form-control" id="postal_address" name="postal_address" placeholder ="null"  value = "<%=user.getPosAddress()%>">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <label for ="per_address">Permanent Address</label>
                                            <input type="text" class="form-control" id ="per_address" name="per_address" placeholder ="null" value = "<%=user.getPerAddress()%>">
                                        </div>
                                    </div>                                    
                                    
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for ="w_phone">Work Phone</label>
                                            <input type="text" class="form-control" id= "w_phone" name="w_phone"   placeholder ="null" value = "<%=user.getWorkPhone()%>">
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for ="m_phone">Mobile Phone</label>
                                            <input type="text" class="form-control" id="m_phone" name="m_phone" placeholder ="null" value = "<%=user.getMobPhone()%>">
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <label for ="h_phone">Home Phone</label>
                                            <input type="text" class="form-control" id = "h_phone" name="h_phone"  placeholder ="null" value = "<%=user.getHomePhone()%>">
                                        </div>
                                    </div>
                                    <div class = "col-md-12">
                                        <label for = "desc">Comments</label>
                                        <textarea name="" class="form-control" id="desc" cols="30" rows="7" placeholder="null"></textarea>
                                    </div>
                                   
                                </div>
                                        
                                <div class="row" style="margin-top: 5px">
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="submit" value="Update" class="btn btn-primary">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="button" value="Cancel" class="btn btn-primary" onclick="window.location='view-users'" >
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


