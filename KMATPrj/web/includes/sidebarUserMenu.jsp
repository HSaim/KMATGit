<%-- 
    Document   : sidebarUser
    Created on : Aug 2, 2016, 11:41:03 AM
    Author     : Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Sidebar Menu Starts --%>
<div id = "nav" class="side">
     <ul>
         <li>
             <a id ="addUser" href = "AddUser.jsp">User</a>
             <p>hi</p>
         </li><br>
         <li><a>Roles</a></li><br>
         <li><a>Process</a></li><br>
         <li><a>Composition</a></li><br>
         <li><a>Concept Map</a></li><br>
         <li><a onclick="addresource()">Resources</a></li><br>
         <li><a onclick = "addtool()">Tools</a></li><br>
         <li><a>Lists</a></li><br>

     </ul>
 </div>
  <%-- Sidebar Menu Ends --%>
 <script>
 $(document).ready(function(){
     $("a").click(function(){
         $("p").toggle(1000);
     });
 });
 </script>
