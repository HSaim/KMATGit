<%-- 
    Document   : UserHome
    Created on : Jul 29, 2016, 11:52:55 AM
    Author     : Habiba Saim

    Logged in user's home page
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <title>KMAT User</title>
    </head>
    <body class = "home"> <!-- onload="loading();"> -->
       <%--
           String user = request.getParameter("hidden");
           out.println("user name: " + user);
       --%>
       <%-- Menubar starts --%>
       <jsp:include page="../../includes/menubarUser.jsp" />
       <%-- Menubar Ends --%>
       
       
       <%-- Menu added here to check if username displays at the top
       <div id = "header">
       <ul>
     <li class = "home"><a  href="UserHome.jsp">Home</a></li>
      <li class = "news"><a href="UserNews.jsp">News</a></li>
      <li class = "contact"><a href="UserContactUs.jsp">Contact</a></li>
      <li class = "about"><a href="UserAboutUs.jsp">About</a></li>
      <li class = "map"><a href="UserMap.jsp">Site Map</a></li>    
      <li><a href = "login.jsp">Logout</a></li>
      
      <%-- Code to display hello message with user name - Starts--%> 
      <%--
      <lable id = "helomsg" align="left"><p id="welcome"></p></lable>
      <script>
          var userN = request.getParameter("hidden");
           out.println("userN: " + userN);
          function loading(){
             // document.getElementById("hellomsg").value = "Hello " + user;
              document.getElementById("helomsg").innerHTML = "Hello " + userN;
          }
          
       // var uname = document.getElementById("login-username").value;
        //var msg = document.getElementById("helomsg");
        //var msgvalue = document.getElementById("welcome");
       // document.getElementById('helomsg').innerHTML = msg;
       // msgvalue.innerHTML ="Hi"+" "+user; 
	//msg.style.display="block";
       </script>
       <%-- Code to display hello message with user name - Ends     
    </ul>
</div>
       
       <%-- Slider starts --%>
       <div id = simple>  
            <jsp:include page="../../includes/topSliderImages.jsp" />
       </div>
       <%-- Slider ends --%>
     
       <%-- Sidebar Menu Starts --%>
       <jsp:include page="../../includes/verticalUserMenu.jsp" />       
       <%-- Sidebar Menu Ends --%>
        
        <%-- adds Top Slider, login functions --%>
        <jsp:include page="../../includes/js.jsp" /> 
        
        <%-- Includes footer
        <jsp:include page="includes/footer.jsp" />
        
    </body>
</html>
 --%>