<%-- 
    Document   : menubar
    Created on : Jul 26, 2016, 12:28:23 PM
    Author     : Habiba Saim
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    String user = request.getParameter("userName");
%>

<div id = "header">
    <ul>
      <li><a class="active" href="https://www.facebook.com/">Home</a></li>
      <li><a href="http://tribune.com.pk/epaper/">News</a></li>
      <li><a href="https://www.lums.edu.pk/">Contact</a></li>
      <li><a href="https://www.google.com/">About</a></li>
      <li><a href="https://www.yahoo.com/">Site Map</a></li>
      <%-- <li id="logbtn" ><a onclick="login()"name="log" id="log" href="login.jsp">Login</a></li> --%>
      <li><a href = "login.jsp">Logout</a></li>
      
      <%-- Code to display hello message with user name - Starts--%> 
      <lable id = "hellomsg" align="left"><p id="welcome">Hi</p></lable>
      <script>
        var uname = document.getElementById("login-username").value;
        var msg = document.getElementById("helomsg");
        var msgvalue = document.getElementById("welcome");
       // document.getElementById('helomsg').innerHTML = msg;
        msgvalue.innerHTML ="Hi"+" "+user; 
	msg.style.display="block";
       </script>
       <%-- Code to display hello message with user name - Ends--%>
     
       <%--
      <li id ="outbtn"><a onclick="logout()"id="out">Logout</a></li>
      <lable id = "helomsg" align="left"><p id="welcome"></p></lable>
      --%>
    </ul>
</div>
