<%-- 
    Document   : menubarUser
    Created on : Jul 26, 2016, 12:28:23 PM
    Author     : Habiba Saim
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    
     <body >
         
         <%
        String user = request.getParameter("hidden");
        %>
        <div id = "header">
            <ul>
                <li class = "home"><a  href="UserHome">Home</a></li>
                <li class = "news"><a href="UserNews">News</a></li>
                <li class = "contact"><a href="UserContactUs">Contact</a></li>
                <li class = "about"><a href="UserAboutUs">About</a></li>
               <!--- <li class = "map"><a href="UserMap.jsp">Site Map</a></li> -->   
               <li class = "map"><a href="UserMap">Site Map</a></li>
                <li><a href = "login.jsp">Logout</a></li>

                <%-- Code to display hello message with user name - Starts--%> 
                <lable id = "hellomsg" align="left"></lable>
                <script>
                   (function() {
                        var val1 = 'user';
                        var label = document.getElementById("hellomsg");
                        //var uname = document.getElementById("login-username").value;
                        //var val2 = request.getParameter("hidden");
                        //var val3 = session.getAttribute(userName);
                        label.innerHTML = "Welcome! " + val1;
                        label.style.display = "block";
                    })();
                  

               // var uname = document.getElementById("login-username").value;
                //var msg = document.getElementById("helomsg");
                //var msgvalue = document.getElementById("welcome");
               // document.getElementById('helomsg').innerHTML = msg;
               // msgvalue.innerHTML ="Hi"+" "+user; 
                //msg.style.display="block";
               </script>
               <%-- Code to display hello message with user name - Ends--%>     
            </ul>
        </div>
    </body>
</html>
