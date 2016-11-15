<%-- 
    Document   : AddProcessLadder
    Created on : Nov 2, 2016, 9:11:48 AM
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
	<title>Add Process Ladder</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="Knowledge Management for All Tool (KMAT)" />
	<meta name="keywords" content="KMAT, process ladder, description, help" />
	<meta name="author" content="KMAT Team" />
        
        <jsp:include page="../../includes/link.jsp" /> 
        <jsp:include page="../../includes/process-ladder-link.jsp" />        
    </head>
    
    <body class = "add-p-ladder">
       
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
                        Add a new process ladder
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
                             
                            <div class="row">

                                <div class="col-md-6">
                                   
                                    <div class="field-name-wrapper">
                                    <!--<form accept-charset="UTF-8" action="/d3/d3/search" class="field-name-form" method="get">-->
                                        <span id="id-header-label" class="field-name-border">
                                            <label id="label-ladder-name" class="field-name-label">Ladder Name: </label>
                                            <input id="name-input" type="text" class="field-name-value" data-hotkey="s" name="q" placeholder="Enter name" tabindex="1" onclick="changeNameTextFieldColor()" onblur="nameTextFieldColorOriginal()">
                                            <label id="graph-name-error" style="display: none; position: absolute; padding-left: 10px; color: red">*</label>
                                        </span>
                                    <!--</form>-->
                                    </div>
                                    
                                </div>

                                <div class = "col-md-4">                                    
                                    <div id="graph-buttons">
                                        <input type="image" id="graph-help-icon" title="Help" src="images/help-icons/help-icon20.png"
                                               onclick="onClickHelpButton('Process', window.event.x, window.event.y)" 
                                               onmousedown="onMouseDownHelpButton('Process')" onmouseup="onMouseUpHelpButton('Process')" 
                                               onblur="onBlurHelpButton()" alt="Help">
                                        <input type="image" id="graph-settings-icon" title="Settings" src="images/icons/settings-icon.png" 
                                               onclick="onClickSettingsButton('Process' , window.event.x, window.event.y)" 
                                               onmousedown="onMouseDownSettingsButton('Process')" onmouseup="onMouseUpSettingsButton('Process')" alt="Settings">
                                    </div>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            <br/>
                            <br/>
                            
                           
                            <div id="toolbox">
                                <input type="image" id="center-input" title="Move to Root Node" src="images/icons/center-icon.ico" alt="Initial Position">
                                <input type="image" id="preview-input" title="Preview Ladder" src="images/icons/preview-icon.ico" alt="Preview Ladder">
                                <input type="image" id="save-input" title="Save Ladder" src="images/icons/backup-icon.ico" alt="Save Ladder">
                                <!--<input type="image" id="open-input" title="open graph" src="resources/open-icon.png" alt="open graph">-->
                                <input type="image" id="delete-graph" title="Delete Ladder" src="images/icons/delete-icon.png" alt="Delete Ladder">
                            </div>
                            
                            <input type="image" id="error-button" title="" src="images/icons/error-icon.ico" width="20" height="20" onclick="onClickErrorButton('Process')" onmousedown="onMouseDownErrorButton('Process')" onmouseup="onMouseUpErrorButton('Process')" onblur="onBlurErrorButton()" alt="Error">
                            
                            <!-- START: The Modal -->
                            <div id="node-modal" class="modal">
                                <!-- Modal content -->
                                <div class="modal-content">
                                    <div class="modal-header">
                                            <span class="close" onclick="closeModal()" style="font-size: 20px; padding-top: 7px">×</span>
                                            <div class="node-name-div">
                                                <p id="modal-node-name" contenteditable="true" onfocus="modalHeaderFull()" onblur="modalHeaderClip()">Modal Header<p>
                                            </div>
                                    </div>
                                    <div class="modal-body">
                                        <p style="text-align: left; font-family: Verdana, Geneva, sans-serif; font-size: 14px; color: rgb(77, 77, 77)">Description:&nbsp; 
                                        <textarea class="modal-description" id="modal-description-id" rows="15" cols="54"></textarea> </p>

                                        <button class="accordion" id="accordion1" style="border-top-left-radius: 10px; border-top-right-radius: 10px">Link Tools</button>
                                        <div class="panel" id="accordion1-panel">
                                            <div class="tools-last-accordion" id="tools-last-accordion-item">
                                                <!--return false after onclick function so that control does not go to href -->
                                                <a href="" id="tools-add-link" onclick="addToNode('accordion1-panel'); return false;"><img src="images/icons/add-icon.png" id="add-tool-icon" alt="">Add Tool</a>
                                                <a href="" id="tools-edit-button" onclick="editAccordionItems('accordion1-panel'); return false;">Edit</a>
                                            </div>
                                        </div>

                                        <button class="accordion" id="accordion2">Attach Resources</button>
                                        <div class="panel" id="accordion2-panel">
                                            <div class="tools-last-accordion" id="resources-last-accordion-item">
                                                <!--return false after onclick function so that control does not go to href -->
                                                <a href="" id="resources-add-link" onclick="addToNode('accordion2-panel'); return false;"><img src="images/icons/add-icon.png" id="add-resource-icon" alt="">Add Resource</a>
                                                <a href="" id="resources-edit-button" onclick="editAccordionItems('accordion2-panel'); return false;">Edit</a>
                                            </div>
                                        </div>

                                        <button class="accordion" id="accordion-share">Share with</button>
                                        <div class="panel" id="accordion3-panel">
                                            <div class="tools-last-accordion" id="share-last-accordion-item">
                                                <!--return false after onclick function so that control does not go to href -->
                                                <a href="" id="share-add-link" onclick="addToNode('accordion3-panel'); return false;"><img src="images/icons/add-icon.png" id="add-share-icon" alt="">Share</a>
                                                <a href="" id="share-edit-button" onclick="editAccordionItems('accordion3-panel'); return false;">Edit</a>
                                            </div>
                                        </div>
                                        <br>
                                        <input id="modal-checkbox1" type="checkbox" name="rating" value="allowRating"/> <label for="modal-checkbox1">Allow Rating</label> <br><br>
                                        <input id="modal-checkbox2" type="checkbox" name="comments" value="allowComments"> <label for="modal-checkbox2">Allow Comments</label> <br><br>
                                        <input id="modal-checkbox3" type="checkbox" name="list" value="addToList"> <label for="modal-checkbox2">Add to List</label>

                                        <button class="modal-save-button" onclick="saveNodeDetails()">Save</button>
                                    </div>

                                </div>
                            </div>
                            <!-- END: The Modal -->
                            
                            <!-- START: Inner Modal -->
                            <div id="inner-modal-id" class="inner-modal">
                                <!-- Modal content -->
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
                                <h3>Directions</h3>
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
                                        <h2>Settings</h2>
                                        <input id="settings-modal-checkbox1" type="checkbox" name="rating" value="allowRating"/> <label for="settings-modal-checkbox1">Enable Rating</label> <br><br>
                                        <input id="settings-modal-checkbox2" type="checkbox" name="comments" value="allowComments"> <label for="settings-modal-checkbox2">Enable Comments</label> <br><br>
                                        <input id="settings-modal-checkbox3" type="checkbox" name="rating" value="allowRating"/> <label for="settings-modal-checkbox3">Enable Rating in Nodes</label> <br><br>
                                        <input id="settings-modal-checkbox4" type="checkbox" name="comments" value="allowComments"> <label for="settings-modal-checkbox4">Enable Comments in Nodes</label> <br><br>
                                        <input id="settings-modal-checkbox5" type="checkbox" name="list" value="addToList"> <label for="settings-modal-checkbox5">Add to List</label>
                                        <br><br>
                                        <label style='font-family: Verdana, Geneva, sans-serif; font-size: 14.5px;'>Description:</label>
                                        <br>
                                        <textarea class="modal-description" style='margin: 10px 0px; width: 95%;' id="settings-modal-description-id" rows="15" cols="54"></textarea>               
                                    </div>
                                </div>
                            </div>

		
                            <div class = "row">
                                <div class="add-spaces"> <!-- Spaces are added here to keep prevent SVG from overlapping footer -->
                                    <p></p>
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
                <jsp:include page="../../includes/process-ladder-js.jsp" /> 
                
                <!-- footer.jspf integrates here -->