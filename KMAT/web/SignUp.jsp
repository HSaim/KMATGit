<%-- 
    Document   : SignUp
    Created on : Aug 10, 2016, 10:16:38 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <title>Sign Up</title>
        
        <script>
            function validLogin(){
                        <%--if (n == "111"){
                    alert ( "Please enter Login Name." );
                    //document.loginform.userName.focus();
                    return false;
                }
                        --%>
                 if (document.form.user_name.value === ""){
                     alert("enter user name");
                 }   
                alert ( "Welcome User" );
                return true;
           }
        </script>
       
    </head>
    
    <body>
        <%-- Menubar starts --%>
        <jsp:include page="includes/menubar.jsp" />
        <%-- Menubar Ends --%>

        <%-- Slider starts --%>
        <div id = simple>  
             <jsp:include page="includes/topSliderImages.jsp" />
        </div>

        <%-- Add User Block Starts --%>
        <div  id ="Login">
            <form method="post" action="InsertUser.jsp" > <!--onsubmit="return validLogin();"> -->
                <input type="hidden" name="hidden" value="SignUp"> <!-- For distinction of SignUp and AddUser pages-->
                <table style="width:auto"align="center">
                    <tr>
                        <th colspan="2">
                            Create your KMAT Account
                        </th>
                    </tr>
                    
                    <%-- Includes Sign Up Form --%>
                    <jsp:include page="includes/signupForm.jsp" />
                </table>  
            </form>
        </div>
       
        <%-- Add User Block Ends --%>
        
        <%-- adds Top Slider, login functions --%>
        <jsp:include page="includes/js.jsp" /> 
      
        <%-- Includes footer 
        <jsp:include page="includes/footer.jsp" />
        
    </body>
    
</html>
--%>