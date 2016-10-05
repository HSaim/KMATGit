<%-- 
    Document   : AddUser
    Created on : Aug 2, 2016, 11:38:08 AM
    Author     : Habiba Saim

    Signup Form
--%>

<!-- Code to prevent user from accessing any user specific page after logout/session-end -->
<%
    response.setHeader("Pragma","no-cache"); 
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader("Expires",-1);
    if(session.getAttribute("CurrentSessionUser")==null){
    
        response.sendRedirect("login.jsp");
    }
%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <title>Add User</title>
        
         <!-- To validate email address-->
        <script>
            function verifyEmail(){
                var status = true;     
                var emailRegEx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
                    if (document.signup.p_email.value.search(emailRegEx) == -1) {
                         alert("Please enter a valid email address in Primary Email field");                      
                         status = false;
                    }

                    if (document.signup.s_email.value.search(emailRegEx) == -1 && document.signup.s_email.value!="") {
                         alert("Please enter a valid email address in Secondary Email field");                   
                         status = false;
                    }
                return status;
            }
        </script>
    </head>
    
    <body class="adduser" >
        <div class = "wrapper">
            <%-- Menubar starts --%>
            <jsp:include page="../../includes/menubarUser.jsp" />
            <%-- Menubar Ends --%>

            <%-- Slider starts --%>
            <div id = simple>  
                 <jsp:include page="../../includes/topSliderImages.jsp" />
            </div>

            <%-- Sidebar Menu Starts --%>
            <jsp:include page="../../includes/verticalUserMenu.jsp" />
            <%-- Sidebar Menu Ends --%>

            <%-- Add User Block Starts --%>      

            <div  id ="Login" align="center">
                <form name =  "signup" method="post" action="InsertUserController" onSubmit="return verifyEmail()">
                    <input type="hidden" name="hidden" value="AddUser"> <!-- For distinction of SignUp and AddUser pages-->
                    <table style="width:auto"align="center">
                        <tr>
                            <th colspan="2">
                                Add User
                            </th>
                        </tr>
                        <%-- Includes Sign Up Form --%>
                        <jsp:include page="../../includes/signupForm.jsp" />
                    </table>

                </form>
            </div>       
            <%-- Add User Block Ends --%>
            
            <%-- adds Top Slider, login functions --%>
            <jsp:include page="../../includes/js.jsp" /> 
            
            <%-- footer.jspf integrates here --%>