<%-- 
    Document   : user-header
    Created on : Oct 31, 2016, 1:14:15 PM
    Author     : Habiba Saim
--%>

<%@page import="model.UserBean"%>
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
                        <!--<label id="uname" align = "left"><a href =""></a></label>-->
                        <li id = "uname"><a>UserName</a></li>
                        <li><a href="home">Home</a></li>
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

                </ul>
                </nav>
                <!-- END menu-wrap -->
            </div>
        </div>
        <!-- END container -->
    </header>
    <!-- END header-section -->
</div>

<%--
<script>
    (function() {
        <% UserBean currentUser = ((UserBean) (session.getAttribute("CurrentSessionUser"))); %>
         var v1 = '<%= currentUser.getUsername() %>';                      
         var label = document.getElementById("uname");

         label.innerHTML = v1;
         //label.style.display = "block";
     })();
</script>
        --%>