<%-- 
    Document   : AddUser
    Created on : Aug 2, 2016, 11:38:08 AM
    Author     : Habiba Saim

    Signup Form
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <title>Add User</title>
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
                <form method="post" action="InsertUserController">
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