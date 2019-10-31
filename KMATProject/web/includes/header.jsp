<%-- 
    Document   : header
    Created on : Oct 24, 2016, 12:58:47 PM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
  

<div id = "header">
    <!-- START header-section -->
    <header id="header-section">
        <!-- START container -->
        <div class="container">
            <div class="nav-header">
                <a href="#" class="js-nav-toggle nav-toggle"><i></i></a>
                <h1 id="logo"><a href="Home.jsp">KMAT</a></h1>
                <!-- START #menu-wrap -->
                <nav id="menu-wrap" role="navigation">
                    <ul class="sf-menu" id="primary-menu" >
                        <li class = "home"><a  href="Home.jsp">Home</a></li>                        
                        <li class = "news">
                            <a href="" class="fh5co-sub-ddown">News</a>
                            <ul class="sub-menu">

                                <li><a href="">sub menu 1</a></li>

                                <li><a href="">Ladders</a>                                
                                    <ul class="sub-menu">
                                        <li><a href="CompositionLadder.jsp" onclick="open">Composition Ladder</a></li>
                                        <li><a href="ProcessLadder.jsp">Process Ladder</a></li>                                        
                                    </ul>
                                </li>
                                <li><a href = "ConceptMap.jsp">Concept Map</a></li>
                                <li><a href = "Resources.jsp">Resources</a></li>
                                <li><a href = "Tools.jsp">Tools</a></li>
                                <li><a href = "Users.jsp">Users</a></li>
                                <%--

                                <li>
                                    <a href="#" class="fh5co-sub-ddown">sub menu 2</a>
                                    <ul class="sub-menu">
                                        <li><a href="" onclick="open">Build</a></li>
                                        <li><a href="">Work</a></li>
                                        <li><a href="">Light</a></li>
                                        <li><a href="">Relic</a></li>
                                        <li><a href="">Display</a></li>
                                        <li><a href="">Sprint</a></li>
                                    </ul>
                                </li>                                                
                            </ul>
                        </li>
                        <li class  = "about"><a href="AboutKMAT.jsp">About KMAT</a></li>
                        <li class = "contact"><a href="Contact.jsp">Contact</a></li>
                        <li class="cta home"><a href="Home.jsp#get-signin">Login</a></li>
                        <%--<li class="cta home"><a href="Home.jsp#name">Login</a></li>--%>
                    </ul>
                </nav>
                <!-- END menu-wrap -->
            </div>
        </div>
        <!-- END container -->
    </header> 
    <!-- END header-section -->
</div>

<script>
	function callMatlabScript()
	{
		alert("Opening MATLAB");
		
	}
</script>