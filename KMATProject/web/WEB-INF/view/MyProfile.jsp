<%-- 
    Document   : MyProfile
    Created on : Jan 16, 2017, 1:02:03 PM
    Author     : Habiba Saim
--%>
<%@page import="model.UserBean"%>
<%@page import="model.LoginUserBean"%>
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
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="Logged in user profile">
        <meta name="keywords" content="name, email, address, phone">
        <meta name="author" content="KMAT Team">
        
         <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <title>My Profile</title>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
       
        <!-- Popover Script, will shift it in .js file later -->
        <script type="text/javascript">
            $(document).ready(function(){
                $('[data-toggle="popover"]').popover({
                    placement : 'bottom',

                    html : true,
                    
                        
                    content :   '<div class="media">\n\
                                     <div class="media-left">\n\
                                        <img src= "../../images/hero.jpg" class="pull-left" alt="Profile Img" style="width:60px">\n\
                                    </div>\n\
                                    <div class="media-body">\n\
                                        <h5 class="media-heading">Jhon Carter</h5>\n\
                                        <p><br/>Profile</p>\n\
                                    </div>\n\
                                </div>'
                });
            });
        </script>

        <style type="text/css">
                .bs-example{
                margin: 200px 150px 0;
            }
                .bs-example button{
                        margin: 10px;
        </style>

    </head>
    
    <body class = "edit-profile">
        <!-- START wrapper -->
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START header -->
                <%--
                <div id = "header">
                    <!-- START header-section -->
                    <header id="header-section">
                        <!-- START container -->
                        <div class="container">

                            <a href="#" class="js-nav-toggle nav-toggle"><i></i></a>
                            <div>
                                <h1 id="logo"><a href="home">KMAT</a></h1>
                            </div>
                            <%--
                            <div>
                                <h2 id="userName"><a href ="my-profile"><label id="uname"  data-toggle = "popover"></label></a></h2>
                            </div>
                            --%>
                            <%--
                            <div id="userName">    
                                <input id="unameB" type="button" class="btn btn-primary" data-toggle="popover" title="User Name" value ="button"></input>
                                <div class="head hide">user name</div>
                            </div>
                        </div>
                        <!-- END container -->
                          
                    </header>
                    <!-- END header-section -->
                </div>
  --%>
  <%--
                <!-- To display user name in label -->
                <script>
                    (function() {
                        <% LoginUserBean currentUser = ((LoginUserBean) (session.getAttribute("CurrentSessionUser"))); %>
                         var v1 = '<%= currentUser.getUsername() %>';                      
                         //var label = document.getElementById("uname");

                         //label.innerHTML = v1;
                         document.getElementById("unameB").value=v1;
                         document.getElementById("unameB").title=v1;
                         //label.style.display = "block";
                     })();
                </script>
          --%>  
                <jsp:include page="../../includes/user-profile-header.jsp" />       
                <!-- END header -->
                
                <!-- START: Page heading-->
                <aside class="heading-bg" > <!--style="background: url(images/heading-bg1.jpg) repeat;">-->
                    <div class="container">                        
                       My Account                            
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                <%
                    UserBean user = new UserBean();
                    //user = request.getParameter("user");
                    user = (UserBean) session.getAttribute("current-user");
                %>
                <div class="container">
                    <div class="row">
                        <!-- START: Page contents -->
                        <div class="col-md-10 col-md-push-2">
                            <%-- Logged in user detail in object 'user' 
                            <%
                                LoginUserBean user = new LoginUserBean();                           
                                user = ((LoginUserBean) (session.getAttribute("CurrentSessionUser")));
                            %>
                            --%>
                            <form name = "updateProfile" method="post" action="GetUsersController?action=update-profile&&userId=<%=user.getUserId()%>" onSubmit="return verifyEmail()">                               
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
                                            <input type="text" class="form-control" id="lastname" name="lastname" placeholder ="null" value = "<%=user.getLastName() %>"  required>                                          
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-12">
                                        <div class="form-group"> 
                                            <label for="user_name">User Name/User Id*</label>
                                            <input type="text" class="form-control" id = "user_name" name="user_name" placeholder ="null"  readonly value = "<%=user.getUserName() %>"  required>                                    
                                        </div>
                                    </div>
                                          <%--   
                                    <div class="col-md-12">
                                        Password*
                                    </div>
                                    <div class="col-md-12">
                                        <div class="form-group">
                                                
                                                <input type="text" class="form-control" name="password"  placeholder ="null" id = "password"  value = "<%=user.getPassword() %>" required> 
                                        </div>
                                    </div>
                                
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
                                        
                                    <div class="col-md-6">
                                        <div class="form-group">
                                            <input type="submit" value="Save" class="btn btn-primary">
                                            <span style="color: #4CAF50"> ${updateData}</span>
                                        </div>                                        
                                    </div>
                                    
                                </div>
                            </form>
                        </div>
                        <!-- END Page contents -->
                        
                        <!-- START: Left side bar -->
                        <div class="col-md-2 col-md-pull-10 back-color" >
                            <jsp:include page="../../includes/left-sidebar-profile.jsp" />                     
                        </div>         
                        <!-- END: Left side bar -->
                    </div>
                </div>
                          
                <!-- adds js -->                
                <jsp:include page="../../includes/js.jsp" /> 
                <%--<script type="text/javascript" src="../../js/current-user-name.js"></script>--%>
                <!-- footer.jspf integrates here -->


        
    
