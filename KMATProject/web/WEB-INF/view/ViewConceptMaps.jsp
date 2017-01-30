<%-- 
    Document   : ViewConceptMaps
    Created on : Jan 24, 2017, 6:10:48 PM
    Author     : ricelab
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.LadderBean"%>

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
	<meta name="description" content="View a cocnept map for Knowledge Management for All Tool - KMAT" />
	<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
	<meta name="author" content="KMAT Team" />
		
		
        <!-- css includes here -->
        <jsp:include page="../../includes/link.jsp" />
        
        <title>Process Ladders</title>
    </head>

    <body class = "view-c-maps">
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
                       Process Ladders
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
			   
                <div class="container">
                    <div class="row">
						<!--<a href=""><h3>Something</h3></a>-->
                        <div id="inner-body" class="col-md-10 col-md-push-2">
							
							<hr style="height:1px;border:none;color:#333;background-color:#a9a9a9;" />
							<div class = "row">
                                <b>
                                <div class="col-md-6 center-block text-center talign-center">
                                    <b>Concept Map Name</b>                                    
                                </div>
                                <div class = "col-md-3 center-block text-center talign-center">
                                    Edit
                                </div>
                                <div class = "col-md-3 center-block text-center talign-center">
                                    Delete
                                </div>
                                </b>
                            </div>
                            <hr style="height:1px;border:none;color:#333;background-color:#a9a9a9;" />
							
							<!-- EXTRA
							<form name = "runtool" method="post" action="RunToolController" onSubmit="">
								<br>
								<div class="form-group">
									<input type="text" name="input" placeholder="Input Parameter" required>
								</div>
								<div class="form-group">
									<input type="submit" value="Run Process" class="btn btn-primary">
								</div>
								<div>
									<%--<p>Solution: <//%session.getAttribute("output");%></p>--%>
								</div>
							</form>
							<%--<a href="file:///Users/maryamkhalid/Desktop/gridWorld.pdf">Link 1</a>-
							<a href="file:///Users\maryamkhalid\Desktop\gridWorld.pdf">Link 1</a>--%>
							<p onclick="download()">Click Here</p>
							<a href="ViewAProcessLadder.jsp">View Process Ladder 1</a> -->
                        </div>
						
                        <div class="col-md-2 col-md-pull-10 back-color" >
                            <!-- Left side bar includes here -->
                            <jsp:include page="../../includes/left-sidebar.jsp" />
                        </div>                        
                    </div>
                </div>
				<script>
					var xhttp = new XMLHttpRequest();
					var allProcessLadders = [];
					xhttp.onreadystatechange = function() 
					{
						if (xhttp.readyState === 4 || xhttp.readyState === "complete")
						{   
							allProcessLadders = JSON.parse(xhttp.responseText);
							//alert(allProcessLadders.length);
							for(var i = 0; i < allProcessLadders.length; i++)
							{
								//console.log(allProcessLadders[i].name);

								var rowDiv = document.createElement('div');
								rowDiv.setAttribute("class", "row ladder-view-table-row");
								
								//** Ladder names **//
								var col1 = document.createElement('div');
								col1.setAttribute("class", "col-md-6 table-row-div-align");
								var col1href = document.createElement('a');
								col1href.setAttribute("class", "ladder-view-table-link w3-display-middle");
								col1href.innerHTML = allProcessLadders[i].name;
//TODO: check if window.sessionStorage would be a better option
								//set link to view ladder 
								var url1 = "window.location.href='view-a-process-ladder?ladder=" + JSON.stringify(allProcessLadders[i]) + "'";
								//col1href.setAttribute("onclick", "window.location.href='view-a-process-ladder'");
								col1href.setAttribute("onclick", url1);
								var col1h5 = document.createElement('h5');
								col1.style.left = "20px";
								col1h5.appendChild(col1href);
								col1.appendChild(col1h5);

								//** Edit Ladder **//
								var col2 = document.createElement('div');
								col2.setAttribute("class", "col-md-3 center-block text-center talign-center table-row-div-align");
								var col2href = document.createElement('a');
								//set link to edit process ladder - passing on the ladder
								var url2 = "window.location.href='edit-process-ladder?ladder=" + JSON.stringify(allProcessLadders[i]) + "'";
								col2href.setAttribute("onclick", url2);
								col2href.setAttribute("class", "ladder-view-table-link");
								var col2h3 = document.createElement('h5');
								var col2i = document.createElement('i');
								col2i.setAttribute("class", "icon-edit ladder-view-table-link ladder-view-table-icons");
								col2href.appendChild(col2i);
								col2h3.appendChild(col2href);
								col2.appendChild(col2h3);

								//** Delete Ladder **//
								var col3 = document.createElement('div');
								col3.setAttribute("class", "col-md-3 center-block text-center talign-center table-row-div-align");
								var col3href = document.createElement('a');
								//set link to get ladders controller with action: delete-ladder-from-view-ladders
								col3href.setAttribute("onclick", "deleteLadder("+ allProcessLadders[i].id + "); return false;");
								//var url3 = "view-process-ladders";
								//col3href.setAttribute("href", url3);
								col3href.setAttribute("class", "ladder-view-table-link");
								var col3h3 = document.createElement('h5');
								var col3i = document.createElement('i');
								col3i.setAttribute("class", "icon-trash ladder-view-table-link ladder-view-table-icons");
								col3href.appendChild(col3i);
								col3h3.appendChild(col3href);
								col3.appendChild(col3h3);

								rowDiv.appendChild(col1);
								rowDiv.appendChild(col2);
								rowDiv.appendChild(col3);

								document.getElementById("inner-body").appendChild(rowDiv);
							}
						}
						else
						{
							//alert('Request failed.  Returned status of ' + xhttp.status);
						}
					};

					function deleteLadder(ladderId)
					{
						var userResponse = window.confirm("Delete Ladder?");
						if(userResponse)
						{
							alert("TODO: delete ladder: " + ladderId);
							//TODO: delet ladder according to ladder id and refresh this page
						}
					}
					xhttp.open("POST", "GetLaddersController", true);
					xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					var action = "action=get-all-process-ladders";
					xhttp.send(action);
					
				</script>
                <!-- EXTRA
				<script>
					function download()
					{
						alert("In download!!");
						window.open("/Users/maryamkhalid/Desktop/gridWorld");
						//window.location.assign("Users/maryamkhalid/Desktop/gridWorld.pdf");
					}
				</script>-->
                <!-- adds js -->                
                <jsp:include page="../../includes/js.jsp" /> 
                
                <!-- footer.jspf integrates here -->


