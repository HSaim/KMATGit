<%-- 
    Document   : AddTool
    Created on : Nov 16, 2016, 10:29:01 AM
    Author     : Habiba Saim
--%>

<<<<<<< HEAD

=======
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
>>>>>>> 0377e9717aa445d3b04961bdd259cd08eb727c20

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Add a new tool in Knowledge Management for All Tool - KMAT" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
        
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <style>
.dropdown-submenu {
    position: relative;
}

.dropdown-submenu .dropdown-menu {
    top: 0;
    left: 100%;
    margin-top: -1px;
}
</style>
        <title>Add Tool</title>
    </head>
    
   <body class = "add-tool">
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
                        Add a New Tool
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
                        <%-- Add Tool Block Starts --%> 



<form method="post" action="AddResource" enctype="multipart/form-data">
    <div class="col-md-12">
        <div class="form-group">
        <input type = "text" class="form-control"placeholder="Name of Tool">
    
        </div>
    </div>
    <div class="col-md-12">
        <div class="form-group">
            <textarea class="form-control"placeholder="Description of Tool"rows="10"></textarea>
    
        </div>
    </div>
    
    <div class="col-md-11">
        <div class="form-group">
            <input type = "file" class="form-control-file">   <!--multiple files upload will go here--> 
        </div>
    </div>
    <br/>
   
    <div class="col-md-3">
        <div class="form-group">
            <div class="dropdown">
                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Select
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                    <li class="dropdown-submenu">
                        <a class="test" tabindex="-1" href="#">Matlab 
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">Main</a></li>
                            <li><a tabindex="-1" href="#">Others</a></li>
                        </ul>
                    </li>
                    <li class="dropdown-submenu">
                        <a class="test" tabindex="-1" href="#">JAVA 
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">JAR</a></li>
                            <li><a tabindex="-1" href="#">Script</a></li>
                        </ul>
                    </li>
                    <li class="dropdown-submenu">
                        <a class="test" href="#">C++ 
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">Exe</a></li>
                            <li><a href="#">Script</a></li>
                        </ul>
                    </li>
                  
                </ul>
            </div>
        </div>
        

    
        
    </div><br>
    <div class="form-group">
            <div class="col-md-5">
                <input type="checkbox" value="input">  Input<br>
                <p>Input Format:</p>
                <input type="checkbox" value="input-text">  Text<br>
                <input type="checkbox" value="input-images">  Images<br>
            </div>
            <div class="col-md-5">
                <input type="checkbox" value="output">  Output<br>
                <p>Output Format:</p>
                <input type="checkbox" value="output-text">  Text<br>
                <input type="checkbox" value="output-images">  Images<br>
            </div>
    </div>
   
    
  
   <div class="col-md-12">
                <div class="form-group">
                    <input type="submit" value="Add Tool" class="btn btn-primary">
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
                <script>
            $(document).ready(function(){
                $('.dropdown-submenu a.test').on("click", function(e)
                {
                $(this).next('ul').toggle();
                e.stopPropagation();
                e.preventDefault();
                });
            });
        </script>
                <!-- footer.jspf integrates here -->


