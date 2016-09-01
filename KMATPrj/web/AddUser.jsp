<%-- 
    Document   : AddUser
    Created on : Aug 2, 2016, 11:38:08 AM
    Author     : Saim
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
    <body>
        <%-- Menubar starts --%>
        <jsp:include page="includes/menubarUser.jsp" />
        <%-- Menubar Ends --%>

        <%-- Slider starts --%>
        <div id = simple>  
             <jsp:include page="includes/topSliderImages.jsp" />
        </div>

        <%-- Sidebar Menu Starts --%>
        <jsp:include page="includes/sidebarUserMenu.jsp" />
        <%-- Sidebar Menu Ends --%>
        
        <%-- Add User Block Starts --%>
        
            <div id="section" >
             <%--<div id="add-user"  class="hidden" style="align:center"> --%>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <div class="panel-title" style="float:right; font-size: 130%;  position: relative; top:-10px">ADD USER</div>
                    </div> 
                    
                    <div class="panel-body" >
                        <form method="post" action="InsertUser.jsp">
                            <div class="form-group">
                                <label for="email" class="col-md-3 control-label">Email</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="email" placeholder="Email Address">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="firstname" class="col-md-3 control-label">First Name</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="firstname" placeholder="First Name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="lastname" class="col-md-3 control-label">Last Name</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="lastname" placeholder="Last Name">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="password" class="col-md-3 control-label">Password</label>
                                <div class="col-md-9">
                                    <input type="password" class="form-control" name="password" placeholder="Password">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="address" class="col-md-3 control-label">Address</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="address" placeholder="Address">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="contactNumber" class="col-md-3 control-label">Contact Number</label>
                                <div class="col-md-9">
                                    <input type="text" class="form-control" name="contactNumber" placeholder="Contact Number">
                                </div>
                            </div>

                            <div class="form-group">                                        
                                <div class="col-md-offset-3 col-md-9">
                                    <!--
                                    <button name="add-user-form" onclick="addeduser()"><i class="icon-hand-right"></i> Add User</button> 
                                    -->
                                    <input type="submit" value="Add User" />
                                </div>
                            </div>

                        </form>
                     </div>
                </div> 

     <%--</div> --%>
            </div>
       
         <%-- Add User Block Ends --%>
       <%-- adds Top Slider, login functions --%>
        <jsp:include page="includes/js.jsp" /> 
        
        <%-- Footer starts --%>
        <div id="footer">
             Copyright © Rice Lab, LUMS, Lahore, Pakistan.
        </div>
        <%-- Footer ends --%>
    </body>
</html>
