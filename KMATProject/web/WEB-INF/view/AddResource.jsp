<%-- 
    Document   : AddResource
    Created on : Nov 16, 2016, 10:44:37 AM
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
	<meta name="description" content="Add a new resource in Knowledge Management for All Tool - KMAT" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
        
        
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <title>Add Resource</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>
            function preview()

{

document.getElementById("frame-1").src = document.getElementById("add-link").value;

}

function enableDisable(bEnable, otherID)

{



document.getElementById(otherID).disabled = !bEnable;

}

</script>


    </head>
    
   <body class = "add-resource">
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
                        Add A New Resource
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
                            <br/>
                           
                           
<%-- Add Resource Block Starts --%> 



<form method="post" action="AddResource" enctype="multipart/form-data">
    <row>
    <div class="col-md-12">
        <div class="form-group">
            <input type = "text" class="form-control"placeholder="Name of Resource" name="add-name" id="add-name">
    
        </div>
    </div>
        </row>
    
    <row>
    <div class="col-md-12">
        <div class="form-group">
            <textarea class="form-control"placeholder="Description of Resource"rows="10" id="discrp" name="add-description"></textarea>
    
        </div>
    </div>
    </row>
    <row>
    <div class="col-md-1">
        <div class = "form-group">
            <input type="radio" class = "form-check-input" name="chk" id="chk1" onclick="enableDisable(this.checked,'inputbtn')">
        </div>
    </div>
    <div class="col-md-11">
        <div class="form-group">
            
            <input type = "file" name="datafile" id="inputbtn" class="form-control-file" disabled>
    
        </div>
    </div>
        </row>
    
     <row>
    <div class="col-md-1">
        <div class = "form-group">
            <input type="radio" name="chk" id="chk1" onclick="enableDisable(this.checked,'view')" class = "form-check-input">
        </div>
    </div>
   
    <div class="col-md-8">
        <div class="form-group">
            
            <input type = "text" name="add-link" id="add-link" class="form-control"placeholder="Link of Resource">
    
        </div>
    </div>
    <div class="col-md-3">
        <div class="form-group">
            
            <input type = "button" id="view" onclick="preview()" disabled class="form-control"value="Preview">
    
        </div>
    </div>
          </row>
   
     <row>
    <div class="col-md-12" >   
       <iframe id="frame-1" name="f1" style="width:100%; height:100%;"></iframe>
    </div>
            
       
 </row>
    <div class="col-md-12">
                <div class="form-group">
                    <input type="submit" value="Add Resource" class="btn btn-primary">
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


