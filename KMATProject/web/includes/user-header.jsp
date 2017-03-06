<%-- 
    Document   : user-header
    Created on : Oct 31, 2016, 1:14:15 PM
    Author     : Habiba Saim
--%>

<%@page import="model.LoginUserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<div id = "header">
    <!-- START header-section -->
    <header id="header-section">
        <!-- START container -->
        <div class="container">
            <div class="nav-header">
                <a href="#" class="js-nav-toggle nav-toggle"><i></i></a>
                <div>
                <h1 id="logo"><a href="Home.jsp">KMAT</a></h1>
                </div>
                
                <div>
                    <h3 id="userName">
                        <a href = "GetUsersController?action=get-current-user">
                            <button type = "button " title = "Profile" id="uname"  ></button>
                        </a>
                        |
                        <a href = "LoginController">
                            Sign out
                        </a>
                    </h3>
                </div>
                <!-- START #menu-wrap -->
                <nav id="menu-wrap" role="navigation">
                    <ul class="sf-menu" id="primary-menu" >
                        <!--<label id="uname" align = "left"><a href =""></a></label>-->
                        <!--<h2 id="userName"><a href ="SignUp.jsp"><label id="uname" align = "left"></label></a></h2>-->
                        <!--<li id = "uname"><a>UserName</a></li>-->
                        <li class ="home"><a href="home">Home</a></li>
                        <li class = "components">
                            <a href="#" class="fh5co-sub-ddown">KMAT Components</a>
                            <ul class="sub-menu">
                                <li><a href="">Ladders</a>                                
                                    <ul class="sub-menu">
                                        <li><a href="CompositionLadder.jsp" onclick="open">Composition Ladder</a></li>
                                        <li><a href="ProcessLadder.jsp">Process Ladder</a></li>                                        
                                    </ul>
                                </li>
                                <li><a href = "ConceptMap.jsp">Concept Map</a></li>
                                <li><a href = ""></a></li>
                                <li><a href = ""></a></li>
                                <li><a href = ""></a></li>
                                <%--
                                <li>
                                    <a href="#" class="fh5co-sub-ddown">sub menu 2</a>
                                </li>  
                                --%>
                            </ul>
                        </li>
                        <li class  = "about"><a href="AboutKMAT.jsp">About KMAT</a></li>
                        <li class = "contact"><a href="Contact.jsp">Contact KMAT</a></li>
                        <!--<li class="cta"><a href="LoginController">Sign out</a></li>--> 

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
    (function() {
        <% LoginUserBean currentUser = ((LoginUserBean) (session.getAttribute("CurrentSessionUser"))); %>
         var v1 = '<%= currentUser.getUsername() %>';                      
         var label = document.getElementById("uname");

         label.innerHTML = v1;
         //label.style.display = "block";
     })();
</script>
