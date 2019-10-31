<%-- 
    Document   : EditCompositionLadder
    Created on : Jan 27, 2017, 7:14:30 AM
    Author     : Maryam Khalid
--%>

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
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Edit Composition Ladder</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="Create a new composition ladder for Knowledge Management for All Tool - KMAT" />
		<meta name="keywords" content="KMAT, users, roles, knowledge engineers, concept map, list, tool, composition, composition ladder" />
		<meta name="author" content="KMAT Team" />
        
        <!-- css includes here -->
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
                       Edit Composition Ladder
                    </div>                        
                </aside>
                <!-- END: Page heading-->
                
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
							<br>
                            <div id = "ladder-header-row" class="row">

                                <div class="col-md-5 col-md-offset-3">
                                   
                                    <div class="field-name-wrapper">
                                    <!--<form accept-charset="UTF-8" action="/d3/d3/search" class="field-name-form" method="get">-->
                                        <span id="id-header-label" class="field-name-border">
                                            <label id="label-ladder-name" class="field-name-label">Ladder Name: </label>
                                            <input id="name-input" type="text" class="field-name-value" data-hotkey="s" name="q" placeholder="Enter name" tabindex="1" onclick="changeNameTextFieldColor()" onblur="nameTextFieldColorOriginal()">
                                            <!-- TODO: error on ladder name
											<label id="graph-name-error" style="display: none; position: absolute; padding-left: 10px; color: red">*</label>-->
                                        </span>
                                    <!--</form>-->
                                    </div>
                                    
                                </div>

                                <div class = "col-md-3 col-md-offset-1">
                                    <div id="graph-buttons" class="pull-right">
                                        <input type="image" id="graph-help-icon" title="Help" src="images/help-icons/help-icon19.png"
                                               onclick="onClickHelpButton('Composition', window.event.x, window.event.y)" 
                                               onmousedown="onMouseDownHelpButton('Composition')" onmouseup="onMouseUpHelpButton('Composition')" 
                                               onblur="onBlurHelpButton()" alt="Help">
                                        <input type="image" id="graph-settings-icon" title="Settings" src="images/icons/settings-icon.png" 
                                               onclick="onClickSettingsButton('Composition' , window.event.x, window.event.y)" 
                                               onmousedown="onMouseDownSettingsButton('Composition')" onmouseup="onMouseUpSettingsButton('Composition')" alt="Settings">
                                    </div>
                                </div>
								<br>
								<br>
                            </div>
                            
							<!--<div class="row" id="svg-row">
								
							</div>-->

							<div class="row" id="svg-row">
							</div>
							
							<div id="toolbox">
								<input type="image" id="center-input" title="Move to Root Node" src="images/icons/center-icon.ico" alt="Initial Position">
								<!--<form class="inline-toolbox-form" name = "add-edit-process" method="post" action="InsertLadderController" onSubmit="">
									<input id="newLadderId" type="hidden" name="newLadder" value="">-->
									<input type="image" id="save-input" title="Save Ladder" src="images/icons/backup-icon.ico" alt="Save Ladder">
								<!--</form>-->
								<input class="form-inline" type="image" id="delete-graph" title="Delete Ladder" src="images/icons/delete-icon.png" alt="Delete Ladder">
							</div>
							
							<input type="image" id="error-button" title="" src="images/icons/error-icon.ico" width="20" height="20" onclick="onClickErrorButton('Composition')" onmousedown="onMouseDownErrorButton('Composition')" onmouseup="onMouseUpErrorButton('Composition')" onblur="onBlurErrorButton()" alt="Error">
								
                            <!-- START: The Modal -->
                            <div id="node-modal" class="modal">
                                <!-- Modal content -->
                                <div class="modal-content">
                                    <div class="modal-header">
                                            <span class="close" onclick="closeCompositionModal()" style="font-size: 20px; padding-top: 7px">×</span>
                                            <div class="node-name-div">
                                                <p id="modal-node-name" contenteditable="true" onfocus="modalHeaderFull()" onblur="modalHeaderClip()">Modal Header<p>
                                            </div>
                                    </div>
                                    <div class="modal-body">
                                        <p style="text-align: left; font-family: Verdana, Geneva, sans-serif; font-size: 14px; color: rgb(77, 77, 77)">Description:&nbsp; 
                                        <textarea class="modal-description" id="modal-description-id" rows="15" cols="54"></textarea> </p>

										<!--
                                        <button class="accordion" id="accordion1" style="border-top-left-radius: 10px; border-top-right-radius: 10px">Link Tools</button>
                                        <div class="panel" id="accordion1-panel">
                                            <div class="tools-last-accordion" id="tools-last-accordion-item">
										-->
                                                <!--return false after onclick function so that control does not go to href -->
										<!--
                                                <a href="" id="tools-add-link" onclick="addToNode('accordion1-panel'); return false;"><img src="images/icons/add-icon.png" id="add-tool-icon" alt="">Add Tool</a>
                                                <a href="" id="tools-edit-button" onclick="editAccordionItems('accordion1-panel'); return false;">Edit</a>
                                            </div>
                                        </div>

                                        <button class="accordion" id="accordion2">Attach Resources</button>
                                        <div class="panel" id="accordion2-panel">
                                            <div class="tools-last-accordion" id="resources-last-accordion-item">
                                                <a href="" id="resources-add-link" onclick="addToNode('accordion2-panel'); return false;"><img src="images/icons/add-icon.png" id="add-resource-icon" alt="">Add Resource</a>
                                                <a href="" id="resources-edit-button" onclick="editAccordionItems('accordion2-panel'); return false;">Edit</a>
                                            </div>
                                        </div>

                                        <button class="accordion" id="accordion-share">Share with</button>
                                        <div class="panel" id="accordion3-panel">
                                            <div class="tools-last-accordion" id="share-last-accordion-item">
                                                <a href="" id="share-add-link" onclick="addToNode('accordion3-panel'); return false;"><img src="images/icons/add-icon.png" id="add-share-icon" alt="">Share</a>
                                                <a href="" id="share-edit-button" onclick="editAccordionItems('accordion3-panel'); return false;">Edit</a>
                                            </div>
                                        </div>
										-->
                                        <br>
                                        <!--<input id="modal-checkbox1" type="checkbox" name="rating" value="allowRating"/> <label for="modal-checkbox1">Allow Rating</label> <br><br>
                                        <input id="modal-checkbox2" type="checkbox" name="comments" value="allowComments"> <label for="modal-checkbox2">Allow Comments</label> <br><br>
                                        <input id="modal-checkbox3" type="checkbox" name="list" value="addToList"> <label for="modal-checkbox2">Add to List</label>-->

                                        <button id="save-button-modal" class="modal-save-button" onclick="saveNodeDetails()">Save</button>
                                    </div>

                                </div>
                            </div>
                            <!-- END: The Modal -->
                            
                            <!-- START: Inner Modal -->
							<!--
                            <div id="inner-modal-id" class="inner-modal">
							-->
                                <!-- Modal content -->
							<!--
                                <div class="inner-modal-content">
                                    <div class="inner-modal-header">
                                        <span id="search-header" class="field-search-border">
                                            <input id="search-input" type="text" class="field-name-value" data-hotkey="s" name="q" placeholder="Search" 
                                                   onclick="changeSearchTextFieldColor()" onblur="searchTextFieldColorOriginal()" onkeypress="searchEnterPressed(event)" 
                                                   onkeyup="searchKeyUp(this, event)" onmouseover="searchTextFieldMouseover()" onmouseout="searchTextFieldColorOriginal()">
                                            <img id="search-icon" src="images/icons/search-button-icon1.png" alt="">
                                        </span>
                                        <span id="inner-modal-close" class="close" onclick="closeInnerModal()" style="font-size: 20px;">×</span>
                                    </div>
                                    <div class="modal-body" id="search-modal-body">
                                        <label id="no-item-found-label">No Items Found</label>
                                    </div>
                                </div>
                            </div>
							-->
                            <!-- END: Inner Modal -->
                            
                            <!-- START: context menu for each node - to reassign root -->
                            <nav id="context-menu-node" class="context-menu">
                                <ul class="context-menu__items">
                                    <li class="context-menu__item">
                                            <a href="#" class="context-menu__link" data-action="View" 
                                               title="Set this node as root node of ladder" onclick="setRootNode()"> 
                                                <img id="root-icon" src="images/icons/root-node-button-icon1.png" alt=""> Make Root Node</a>
                                    </li>
                                </ul>
                            </nav>
                            <!-- END: context menu for each node - to reassign root --> 
                            
                            <!-- START: help tool tips-->
                            <div class="help-tooltiptext" id="help-tooltiptext">
                                <h3 style='padding:5px'>Directions</h3>
                                <ul>
                                    <li class="directions-list-item">Drag/scroll to translate/zoom the graph</li>
                                    <li class="directions-list-item">Shift-click on graph to create a node</li>
                                    <li class="directions-list-item">Shift-click on a node and then drag to another node to connect them with a directed edge</li>
                                    <li class="directions-list-item">Shift-click on a node to change its title</li>
                                    <li class="directions-list-item">Click on node or edge and press backspace/delete to delete</li>
                                </ul>
                            </div>
                            <!-- END: help tool tips-->
                            
                            <div class="error-modal" id="error-modal">
                                <ul id="error-modal-list">
                                </ul>
                            </div>
	   
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
                <jsp:include page="../../includes/composition-ladder-js.jsp" /> 
                
                <!-- footer.jsp integrates here -->
