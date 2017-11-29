<%-- 
    Document   : EditTool
    Created on : Nov 24, 2017, 5:37:33 PM
    Author     : Fahad Akhtar
--%>

<%@page import="model.ToolBean"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@ page session="true" %>

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



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Update the profile of a Resource" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
        
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <title>Edit Tool</title>
    </head>
    <body class = "view-tools">
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
                        Edit Tool
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
                            <%
                                ToolBean tool = new ToolBean();
                                //user = request.getParameter("user");
                                tool = (ToolBean) session.getAttribute("ret-tool");
                            %>
                            
                            <br/>
                            <form method="post"name ="editTool" method="post" enctype="multipart/form-data" action=action="GetToolController?action=update-tool&&toolId=<%=tool.getToolID()%>" >
                                <input type="hidden" name="hidden" value="AddTool" >
                                <div class="col-md-12">
                                    <div class="form-group">
                                    <input type = "text" class="form-control"value="<%=tool.getToolName()%>" name="addTool" id="tool-name">

                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <textarea class="form-control"value="<%=tool.getToolDescription()%>"rows="10" name="addToolDescrp" id="tool-descrp"></textarea>

                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <p>Tool Type:</p>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                         <div class="dropdown">

                                             <div>
                                                 <select id="tool_opition"name ="toolType" id="tool-type" class="addTypesBtn">
                                                     <option value="C++">C++</option>
                                                     <option value="JAVA">JAVA</option>
                                                     <option value="MATLAB">MATLAB</option>
                                                     <option value="C#">C#</option>
                                                     <option value="Dot Net">Dot Net</option>
                                                     <option value="Blender">Blender</option>
                                                     <option value="Android">Android</option>
                                                     <option value="IOS">IOS</option>
                                                     <option value="Windows">Windows</option>
                                                     <option value="others">Others</option>
                                                 </select>
                                     <!--            
                                                 <script type="text/javascript">

                                 $(".addTypesBtn").click(function(event){
                                     //$("#environment").hide();
                                     $('#tool_opition').on('change', function() {
                                         var selectedSID = $(this).find(':selected').val();
                                         var selectedSoftware = $(this).find(':selected').text();
                                         if(selectedSID=="others"){
                                             $("#environment").show(); 
                                         }
                                         event.preventDefault();
                                         var typeDescription = $("#typeDescription").val();
                                         alert(typeDescription);
                                         $.ajax({
                                             url: '/types/type_count',
                                             method: "post",
                                             data:	{typeDesc:typeDescription},
                                             success:	function(data){
                                                 $(".badgetypes").html(data[0].typecount);

                                             }

                                         })
                                     })
                                 });
                                 </script>-->
                                             </div>
                                         </div>
                                     </div>
                                 </div><!--
                                <div class="col-md-12" id = "environment" name = "environment" style="display: none">
                                    <div class="form-group">
                                    <input type = "text" class="form-control" id="typeDescription" placeholder="Name of New Tool">
                                    <input type="button" class =" form-control addTypesBtn" value="ADD">

                                    </div>
                                </div>-->
    
                                <div class="col-md-12">
                                    <div class="col-md-6">
                                    <div class="form-group">
                                        <p>Main File:</p><input type = "file" name="main" class="form-control-file">   <!--multiple files upload will go here--> 
                                    </div>
                                    </div>
                                    <div class="col-md-6">
                                    <div class="form-group">
                                        <p>Other Files:</p><input type = "file" name="others" class="form-control-file" multiple="ture">   <!--multiple files upload will go here--> 
                                    </div>
                                    </div>
                                </div>
    
   
    
                                <!--  

                                   <script type = "text/javascript">
                                       function myfun(){
                                           var x = document.getElementById(environment);
                                           x.style.display = block;

                                       }

                                   </script>-->
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <div class="col-md-3">
                                                <input type="checkbox" value="input" name = "input">  Input<br>

                                            </div>


                                            <div class="col-md-3">
                                                <input type="checkbox" value="output" name = "output">  Output<br>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-top: 5px">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <input type="submit" value="Update" class="btn btn-primary">
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <input type="button" value="Cancel" class="btn btn-primary" onclick="window.location='view-tool'" >
                                            </div>
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
                <script src="js/signup-verifications.js"></script> 
                
                <!-- footer.jspf integrates here -->


        
            </div>
        </div>
        
    </body>
</html>
