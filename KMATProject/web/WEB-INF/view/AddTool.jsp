<%-- 
    Document   : AddTool
    Created on : Nov 16, 2016, 10:29:01 AM
    Author     : Habiba Saim
--%>

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
	<meta name="description" content="Add a new tool in Knowledge Management for All Tool - KMAT" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
        
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
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
                        <%-- Add Resource Block Starts --%> 



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
    <div class="col-md-1">
        <div class = "form-group">
            <input type="radio" class = "form-check-input">
        </div>
    </div>
    <div class="col-md-11">
        <div class="form-group">
            
        <input type = "file" class="form-control-file">
    
        </div>
    </div>
    <br/>
    <div class="col-md-1">
        <div class = "form-group">
            <input type="radio" class = "form-check-input">
        </div>
    </div>
    <div class="col-md-8">
        <div class="form-group">
            
        <input type = "text" class="form-control"placeholder="Link of Tool">
    
        </div>
    </div>
    <div class="col-md-3">
        <div class="form-group">
            
        <input type = "button" class="form-control"value="Preview">
    
        </div>
    </div>
   <!-- <iframe src="http://www.wikipedia.org"></iframe>-->
    
   <row>
              <div class="col-md-12" height="100%">   
                <iframe src="https://google.com" style="width:100%; height:100%;"></iframe>
                     </div>
            
       
 </row>
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
                
                <!-- footer.jspf integrates here -->


