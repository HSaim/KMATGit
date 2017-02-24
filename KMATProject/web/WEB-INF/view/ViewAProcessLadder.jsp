<%-- 
    Document   : ViewAProcessLadder
    Created on : Dec 2, 2016, 10:10:41 PM
    Author     : Maryam Khalid
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
	<title>View Process Ladder</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Knowledge Management for All Tool (KMAT)" />
	<meta name="keywords" content="KMAT, process ladder, description, help" />
	<meta name="author" content="KMAT Team" />
        
        <jsp:include page="../../includes/link.jsp" /> 
        <jsp:include page="../../includes/process-ladder-link.jsp" />        
    </head>
    
    <body id = "add-process-ladder-body">
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
                        View Process Ladder
                            <!--<span class="border"></span>-->
                        <!--</h1>-->
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <!-- START: Page contents -->
                <div class="container">
                    <!-- START: Page contents main row -->
                    <div class="row">
                        <!-- START: Process ladder-svg and its menus area-->
                        <div class="col-md-10 col-md-push-2">
							<br>
                            <div id = "ladder-header-row" class="row">

                                <div class="col-md-5 col-md-offset-3">
                                   
									<div>
										<label id="view-ladder-name">Ladder Name: </label>
									</div>
                                    
                                </div>

                                <div class = "col-md-3 col-md-offset-1">
                                    <div id="graph-buttons" class="pull-right">
                                        <input type="image" id="graph-help-icon" title="Help" src="images/help-icons/help-icon20.png"
                                               onclick="onClickHelpButton('Process', window.event.x, window.event.y)" 
                                               onmousedown="onMouseDownHelpButton('Process')" onmouseup="onMouseUpHelpButton('Process')" 
                                               onblur="onBlurHelpButton()" alt="Help">
                                        <input type="image" id="graph-settings-icon" title="Settings" src="images/icons/settings-icon.png" 
                                               onclick="onClickSettingsButton('Process' , window.event.x, window.event.y)" 
                                               onmousedown="onMouseDownSettingsButton('Process')" onmouseup="onMouseUpSettingsButton('Process')" alt="Settings">
                                    </div>
                                </div>
								<br>
								<br>
                            </div>

							<div class="row" id="svg-row">
							</div>
								
                            <!-- START: The Modal -->
                            <div id="node-modal" class="modal">
                                <!-- Modal content -->
                                <div class="modal-content">
                                    <div class="modal-header">
                                            <span class="close" onclick="closeModal()" style="font-size: 20px; padding-top: 7px">×</span>
                                            <div class="node-name-div">
												<p id="modal-node-name" contenteditable="false">Modal Header<p>
                                            </div>
                                    </div>
                                    <div class="modal-body">
                                        <p style="text-align: left; font-family: Verdana, Geneva, sans-serif; font-size: 14px; color: rgb(77, 77, 77)">Description:&nbsp; 
											<textarea class="modal-description" id="modal-description-id" rows="15" cols="54" readonly="true"></textarea> </p>

                                        <button class="accordion" id="accordion1" style="border-top-left-radius: 10px; border-top-right-radius: 10px">Linked Tools</button>
                                        <div class="panel" id="accordion1-panel">
                                            <div class="tools-last-accordion" id="tools-last-accordion-item">
                                            </div>
                                        </div>

                                        <button class="accordion" id="accordion2">Attached Resources</button>
                                        <div class="panel" id="accordion2-panel">
                                            <div class="tools-last-accordion" id="resources-last-accordion-item">
                                            </div>
                                        </div>

                                        <button class="accordion" id="accordion-share">Shared with</button>
                                        <div class="panel" id="accordion3-panel">
                                            <div class="tools-last-accordion" id="share-last-accordion-item">
                                            </div>
                                        </div>
                                        <br>
<!--TODO: make a runNodeProcess() function-->
                                        <!--<button id="run-process-button" class="modal-save-button" onclick="saveNodeDetails()">Save</button>-->
                                    </div>

                                </div>
                            </div>
                            <!-- END: The Modal -->
                            
                            <!-- START: help tool tips-->
                            <div class="help-tooltiptext" id="help-tooltiptext">
                                <h3 style='padding:5px'>Directions</h3>
                                <ul>
                                    <li class="directions-list-item">Drag/scroll to translate/zoom the graph</li>
									<li class="directions-list-item">Double click on a node to view node details</li>
                                    <li class="directions-list-item">Double click on an edge to view edge details</li>
                                </ul>
                            </div>
                            <!-- END: help tool tips-->
	   
                            <!-- START: Graph Settings Modal-->		
                            <div id="graph-settings-modal" class="settings-modal-dialog">
                                <!--<div class="modal-content">-->
                                <div class="modal-settings-content">
                                    <span id="settings-modal-close" class="close-settings" onclick="settingsButtonOnClose()">X</span>
                                    <div class="modal-body" style='padding:0px'>
                                        <h2 style='padding:5px'>Settings</h2>
                                        <label style='font-family: Verdana, Geneva, sans-serif; font-size: 14.5px;'>Description:</label>
                                        <br>
                                        <textarea id="graph-settings-description" class="modal-description" style='margin: 10px 0px; width: 95%;' rows="15" cols="54"></textarea>               
                                    </div>
                                </div>
                            </div>
                               
                        </div>
                        <!-- END: Process ladder-svg and its menus area-->
                        <!-- START: Side bar-->
                        <div class="col-md-2 col-md-pull-10 back-color" >
                            <%-- <jsp:include page="includes/verticalUserMenu.jsp" />  --%>
                            <!-- Left side bar includes here -->
                            <jsp:include page="../../includes/left-sidebar.jsp" />
                        </div>
                        <!-- END: Side bar -->
                    </div>
                    <!-- START: Page contents main row -->
                </div>
                <!-- END: Page contents -->
               
                <!-- adds js -->                
                <jsp:include page="../../includes/js.jsp" /> 
				<script type="text/javascript" src="js/process-ladder/libs/d3/d3.js"></script>
				<script src="js/process-ladder/global-variables.js"></script>
				<script src="js/process-ladder/process-ladder-viewer.js"></script>
				<script src="js/process-ladder/graph-utility.js"></script>
				<script src="js/process-ladder/graphbuttons.js"></script>
                <%--<jsp:include page="../../includes/process-ladder-js.jsp" />--%>
                
                <!-- footer.jsp integrates here -->