<%-- 
    Document   : EditUser
    Created on : Nov 25, 2016, 11:03:10 AM
    Author     : Habiba Saim
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
	<meta name="description" content="Tools available in Knowledge Management for All Tool - KMAT" />
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
                           <form name = "editUser" method="post" action="GetUsersController?action=update-user&userId=<%=user.getUserId()%>" onSubmit="return verifyEmail()">
                                <input type="hidden" name="hidden" value="AddUser">
                                <%-- Includes Sign Up Form 
                                <jsp:include page="../../includes/signup-form.jsp" /> --%>
                                 <div class="row">
                                     <div class="col-md-6">
                                         First Name*
                                     </div>
                                     <div class="col-md-6">
                                         Last Name*
                                     </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="firstname" placeholder ="null" placeholder ="null" value = "<%=user.getFirstName() %>" required>
                                            
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="lastname" placeholder ="null" value = "<%=user.getLastName() %>"  required>
                                          
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        User Name/User Id*
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">                                            
                                            <input type="text" class="form-control" name="user_name" placeholder ="null"  readonly value = "<%=user.getUserName() %>"  required>                                    
                                        </div>
                                    </div>
                                        
                                    <div class="col-md-12">
                                        Password*
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                                <!--<textarea name="" class="form-control" id="" cols="30" rows="7" placeholder="Message"></textarea>-->
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
                                       Primary Email*
                                    </div>
                                    <div class="col-md-6">
                                       Secondary Email
                                    </div>
                                    <div class="col-md-6">
                                            <div class="form-group">
                                                <input type="email" class="form-control" name="p_email" placeholder ="null" value = "<%=user.getPriEmail()%>" required>
                                            </div>
                                    </div>
                                    <div class="col-md-6">
                                            <div class="form-group">
                                                <input type="email" class="form-control" name="s_email" placeholder ="null"  value = "<%=user.getSecEmail()%>">
                                            </div>
                                    </div>
                                            
                                    <div class="col-md-6">
                                       Postal Address
                                    </div>
                                    <div class="col-md-6">
                                       Permanent Address
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="postal_address" placeholder ="null"  value = "<%=user.getPosAddress()%>">
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="per_address" placeholder ="null" value = "<%=user.getPerAddress()%>">
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-4">
                                        Work Phone
                                    </div>
                                    <div class="col-md-4">
                                        Mobile Phone
                                    </div>
                                    <div class="col-md-4">
                                        Home Phone
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="w_phone"   placeholder ="null" value = "<%=user.getWorkPhone()%>">
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="m_phone" placeholder ="null" value = "<%=user.getMobPhone()%>">
                                        </div>
                                    </div>
                                    <div class="col-md-4">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="h_phone"  placeholder ="null" value = "<%=user.getHomePhone()%>">
                                        </div>
                                    </div>
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


