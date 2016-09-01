<%-- 
    Document   : login
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
    
    <body>
       <%-- Menubar starts --%>
       <jsp:include page="includes/menubar.jsp" />
       <%-- Menubar Ends --%>
       
       <%-- Slider starts --%>
       <div id = simple>  
            <jsp:include page="includes/topSliderImages.jsp" />
       </div>
       <%-- Slider ends --%>
       
       <%-- Login section starts --%>
       <form method="post" action="ValidateLogin.jsp">
       <div id="section" >
            <div  id ="login">           
                <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
                    <div class="panel panel-info" >
                        <div class="panel-heading">
                            <div class="panel-title">Sign In</div>
                            <div style="float:right; font-size: 95%; position: relative; top:-10px"><a href="#">Forgot Password?</a></div>
                        </div>     

                        <div style="padding-top:30px" class="panel-body" >
                            <div style="display:none" ></div>
                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input id="login-username" type="text" name="username" placeholder="Username or Email" required>                                        
                            </div>

                            <div style="margin-bottom: 25px" class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                                <input id="login-password" type="password" name="password" placeholder="Password" required>
                            </div>

                            <div class="input-group">
                                <div class="checkbox">
                                  <label>
                                    <input id="login-remember" type="checkbox" name="remember" value="1"> Remember me
                                  </label>
                                </div>
                            </div>

                            <!-- Button 
                            <button name="loginform"   onclick="loggedin()"><i class="icon-hand-right"></i> Login</button> -->
                            <input type="submit" value="Login" />
                            <div class="form-group">
                                <div class="col-md-12 control">
                                    <div style="border-top: 1px solid#888; padding-top:15px; font-size:95%" >
                                        Don't have an account? 
                                        <a onclick="adduser()">
                                            Sign Up Here!
                                        </a>
                                    </div>
                                </div>
                            </div>    
                         </div>                     
                    </div>  
                </div>
            </div>
        </div>
           </form>
        <%-- Login section ends --%>
        
        <%-- adds Top Slider, login functions --%>
        <jsp:include page="includes/js.jsp" />

 
        <%-- Footer starts --%>
        <div id="footer">
             Copyright © Rice Lab, LUMS, Lahore, Pakistan.
        </div>
         <%-- Footer ends --%>
         
    </body>
</html>
