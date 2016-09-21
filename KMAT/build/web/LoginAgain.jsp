<%-- 
    Document   : loginAgain
    If invalid username or password is entered then this page will execute to give error message and ask the user to login again
    Created on : Jul 28, 2016, 9:37:51 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <title>Login</title>
    </head>
    
    <body class = "login">
        <div class = "wrapper">
            <%-- Menubar starts --%>
            <jsp:include page="includes/menubar.jsp" />
            <%-- Menubar Ends --%>

            <%-- Slider starts --%>
            <div id = simple>  
                 <jsp:include page="includes/topSliderImages.jsp" />
            </div>
            <%-- Slider ends --%>

            <%-- Login section starts --%>
            <div  id ="Login" align="center">
                 <form method="post" action="LoginController" >    

                     <p class ="error-message">                   
                         Invalid user name or password                    
                     </p>

                     <table style="width:auto"align="center">
                         <tr>
                             <th colspan="2">
                                 Sign in
                             </th>
                         </tr>

                         <tr>
                             <td style="width:auto"align="right">
                                 Username:
                             </td>
                             <td style="width:auto"align="left">
                                 <input id="login-username" type="text" name="username" placeholder="Username or Email" required>
                             </td>
                         </tr>
                         <tr>
                             <td style="width:auto"align="right">
                                 Password:
                             </td>
                             <td style="width:auto"align="left">     
                                 <input id="login-password" type="password" name="password" placeholder="Password" required>
                             </td>
                         </tr>
                         <tr>
                             <td colspan="2" style="width:auto"align="right">
                                 <a href="#">Forgot Password?</a>
                             </td>
                         </tr>
                         <tr>
                             <td colspan="2" style="width:auto"align="left">
                                 <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                             </td>
                         </tr>
                         <tr>
                             <td colspan="2" style="width:auto"align="center">
                                 <!--<button name="loginform"   onclick="loggedin()">Login</button>-->
                                 <input type="submit" value="Login" />
                             </td>
                         </tr>
                         <tr>
                             <td colspan="2" style="width:auto"align="center">
                                 No account? <a href = "SignUp.jsp">Sign Up Here!</a>
                             </td>
                         </tr>
                     </table>
                 </form>
             </div>
             <%-- Login section ends --%>

             <%-- adds Top Slider, login functions --%>
             <jsp:include page="includes/js.jsp" />


             <%-- Includes footer 
             <jsp:include page="includes/footer.jsp" />

    </body>
</html>
--%>