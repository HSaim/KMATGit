<%-- 
    Document   : NewProcessLadder
    Created on : Oct 27, 2016, 9:58:18 AM
    Author     : Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Process Ladder </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="js/process-ladder/libs/d3/d3.js"></script>
        <script src="graph-creator.js"></script>
        <link rel="stylesheet" href="css/process-ladder/graph-creator.css">
        <link rel="stylesheet" href="css/process-ladder/graph-modal.css">
        <link rel="stylesheet" href="css/process-ladder/custom-contextmenu.css">
        <link rel="stylesheet" href="css/process-ladder/graph-settings-modal.css">

        <!--Main icon of the page (appears on the tab)-->
        <!--<link rel="shortcut icon" href="resources/process-icons/process-icon-pressed.png">-->
        <!--<link rel="shortcut icon" href="resources/fav-icon.png">-->
        
        <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
	<link rel="shortcut icon" href="favicon.ico">
        
       <!-- Animate.css -->
	<link rel="stylesheet" href="css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="css/icomoon.css">
	<!-- Simple Line Icons -->
	<link rel="stylesheet" href="css/simple-line-icons.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="css/bootstrap.css">
	<!-- Superfish -->
	<link rel="stylesheet" href="css/superfish.css">
	<!-- Flexslider  -->
	<link rel="stylesheet" href="css/flexslider.css">
        <!-- Homeslider  
	<link rel="stylesheet" href="css/homeslider.css">-->
	<link rel="stylesheet" href="css/style.css">
        
        <!-- Modernizr JS -->
	<script src="js/modernizr-2.6.2.min.js"></script>
    </head>
    <body>
          <!-- START wrapper -->
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START header -->
                <div id = "header">
                    <!-- START header-section -->
                    <header id="header-section">
                        <!-- START container -->
                        <div class="container">
                            <div class="nav-header">
                                <a href="#" class="js-nav-toggle nav-toggle"><i></i></a>
                                <h1 id="logo"><a href="home.jsp">KMAT</a></h1>
                                <!-- START #menu-wrap -->
                                <nav id="menu-wrap" role="navigation">
                                    <ul class="sf-menu" id="primary-menu" >
                                       
                                        <li><a href="home.jsp">Home</a></li>
                                        <li><a href="">News</a></li>
                                        <li>
                                            <a href="" class="sub-ddown">Site Map </a>
                                            <ul class="sub-menu">
                                                <li><a href="">sub menu 1</a></li>
                                                
                                                <li>
                                                    <a href="#" class="fh5co-sub-ddown">sub menu 2 </a>
                                                    <ul class="sub-menu">
                                                            <li><a href="" >Build</a></li>
                                                            <li><a href="">Work</a></li>
                                                            <li><a href="">Light</a></li>
                                                            <li><a href="">Relic</a></li>
                                                            <li><a href="">Display</a></li>
                                                            <li><a href="">Sprint</a></li>
                                                    </ul>
                                                </li>                                                
                                            </ul>
                                        </li>
                                    <li><a href="about.html">About</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                    <li class="cta"><a href="">Login</a></li>
                                </ul>
                                </nav>
                                <!-- END menu-wrap -->
                            </div>
                        </div>
                        <!-- END container -->
                    </header> 
                    <!-- END header-section -->
                </div>
                <!-- END header -->              
                
                
                <!-- START: Page Heading -->    
                <aside class="page-heading">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <h1 class="page-heading-lead">
                                   Process Ladder
                                    <span class="border"></span>
                                </h1>
                            </div>
                        </div>
                    </div>
                </aside>
                 <!-- END: Page Heading -->   
                
                
                <div class="container">
                    <div class="row">
                        <div class="col-md-10 col-md-push-2">
                            <!-- START: Page Header with Sub-title and name Label and Textfield> -->
                            <div class="row">

                                <div class="col-md-6">
                                    <div class="field-name-wrapper">
                            <!--<form accept-charset="UTF-8" action="/d3/d3/search" class="field-name-form" method="get">-->
                                        <span id="id-header-label" class="field-name-border">
                                                <label id="label-ladder-name" class="field-name-label">Ladde Name: </label>
                                                <input id="name-input" type="text" class="field-name-value" data-hotkey="s" name="q" placeholder="Enter name" tabindex="1" onclick="changeNameTextFieldColor()" onblur="nameTextFieldColorOriginal()">
                                                <label id="graph-name-error" style="display: none; position: absolute; padding-left: 10px; color: red">*</label>
                                        </span>
                            <!--</form>-->
                                    </div>
                                </div>

                                <div class = "col-md-4">

                                    <div id="graph-buttons">
                                            <input type="image" id="graph-help-icon" title="Help" src="resources/help-icons/help-icon20.png" onclick="onClickHelpButton('Process', window.event.x, window.event.y)" onmousedown="onMouseDownHelpButton('Process')" onmouseup="onMouseUpHelpButton('Process')" onblur="onBlurHelpButton()" alt="Help">
                                            <input type="image" id="graph-settings-icon" title="Settings" src="resources/icons/settings-icon.png" onclick="onClickSettingsButton('Process')" onmousedown="onMouseDownSettingsButton('Process')" onmouseup="onMouseUpSettingsButton('Process')" alt="Settings">
                                    </div>
                                </div>
                            </div>
                            <div class = "row">
                                <div class="add-spaces"> <!-- SPaces are added here to keep prevent SVG from overlapping footer -->
                                    <p></p>
                                </div>
                                
                            </div>
                             <!-- END Page Header with Sub-title and name Label and Textfield> -->  
                             
                            <!-- START: Toolbox 
                     <div id="toolbox">
			<input type="image" id="center-input" title="Move to Root Node" src="resources/center-icon.ico" alt="Initial Position">
			<input type="image" id="preview-input" title="Preview Ladder" src="resources/preview-icon.ico" alt="Preview Ladder">
			<input type="image" id="save-input" title="Save Ladder" src="resources/backup-icon.ico" alt="Save Ladder">
			<!--<input type="image" id="open-input" title="open graph" src="resources/open-icon.png" alt="open graph">
			<input type="image" id="delete-graph" title="Delete Ladder" src="resources/delete-icon.png" alt="Delete Ladder">
                    </div>                     
                     <!-- END: Toolbox -->
                     
                     <!-- Error 
                    <input type="image" id="error-button" title="" src="resources/error-icon.ico" width="20" height="20" onclick="onClickErrorButton('Process')" onmousedown="onMouseDownErrorButton('Process')" onmouseup="onMouseUpErrorButton('Process')" onblur="onBlurErrorButton()" alt="Error">
                        -->
                    </div>
                    
                    <div class="col-md-2 col-md-pull-10 back-color" >
                    <jsp:include page="includes/left-sidebar.jsp" />
                </div>
            </div>
            </div>
            
                 <!-- START: Process Lasser js -->
            <script src="js/process-ladder/process-ladder-creator.js"></script>
            <script src="js/process-ladder/graph-utility.js"></script>
            <script src="js/process-ladder/custom-contextmenu.js"></script>
            <script src="js/process-ladder/graphbuttons.js"></script>
            <!-- END: Process Lasser js -->  
            
            <script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Superfish -->
	<script src="js/hoverIntent.js"></script>
	<script src="js/superfish.js"></script>
	<!-- Flexslider -->
	<script src="js/jquery.flexslider-min.js"></script>
	<!-- Stellar -->
	<script src="js/jquery.stellar.min.js"></script>
	<!-- Counters -->
	<script src="js/jquery.countTo.js"></script>

	<!-- Main JS  -->
	<script src="js/main.js"></script>
                     
                