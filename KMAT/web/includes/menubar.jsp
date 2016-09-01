<%-- 
    Document   : menubar
    Created on : Jul 26, 2016, 12:28:23 PM
    Author     : Habiba Saim
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id = "header">
    <ul>
      <li class = "home"><a  href="index.jsp">Home</a></li>
      <li class = "news"><a href="News.jsp">News</a></li>
      <li class = "contact"><a href="ContactUs.jsp">Contact</a></li>
      <li class = "about"><a href="AboutUs.jsp">About</a></li>
      <li class = "map"><a href="Map.jsp">Site Map</a></li>
      <%-- <li id="logbtn" ><a onclick="login()"name="log" id="log" href="login.jsp">Login</a></li> --%>
      <li class = "login"><a href = "login.jsp">Login</a></li>
      <%-- 
        <li id ="outbtn"><a onclick="logout()"id="out">Logout</a></li> 
        <lable id = "helomsg" align="left"><p id="welcome"></p></lable>
      --%>
    </ul>
</div>
