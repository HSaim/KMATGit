<%-- 
    Document   : sidebarUser
    Created on : Aug 2, 2016, 11:41:03 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- <link rel="stylesheet" type="text/css" href="css/sidebarMenu.css">-->
<%-- Sidebar Menu Starts 
<div id = "nav" >--%>
<div id = "vertical">
    <ul >
        <li class = "user">
            <a href = "AddUser">User</a>  
        </li>

        <li class =  "rolls">
            <a href="#">Roles</a>
            <!-- Provision to add sub menu   -->
            <ul>
               <li><a href="#">Sub-Item 1 a</a></li>
           </ul>
          
        </li>

        <li>
            <a href = "#addLadder">Composition Ladder</a>
        </li>

        <li>
            <a>Concept Map</a>
        </li>

        <li >
            <a>Tools</a>
        </li>

        <li class = "resource" >
            <a href = "AddResource">
                Resources
            </a>
        </li>      

        <li>
            <a>Lists</a>
        </li>
     </ul>
<!--
     <div id="addUser">Tab 1 Content</div> 
     <div id="addRole">Tab 2 Content</div>
     <div id="addLadder">Tab 3 Content</div>
-->
</div>
<!-- Script for submenu to be diplayed and hidden on mouse click -->
<script>
    $(document).ready(function () {
        $('#vertical ul > li > a').click(function(){
            if ($(this).attr('class') != 'active'){
                $('#vertical ul li ul').slideUp();
                $(this).next().slideToggle();
                $('#vertical ul li a').removeClass('active');
                $(this).addClass('active');
             }
        });
    });
</script>
  <%-- Sidebar Menu Ends 
  
 <script>
 $(document).ready(function(){
     $("a").click(function(){
         $("p").toggle(1000);
     });
 });
 </script> --%>

 <%--
<div class="nav-container">
  <ul class="nav">
    <li>
      <a href="#">
       <!-- To place an icon ->
       <!-- <span class="icon-home"></span> -->
        <span class="text">home</span>
      </a>
    </li>
    <br/>
    <li>
      <a href="#">
        <span class="icon-user"></span>
        <span class="text">about</span>
      </a>
      </li>
      <br/>
    <li>
      <a href="#">
        <span class="icon-headphones"></span>
        <span class="text">Audio</span>
      </a>
      </li>
      <br/>
    <li>
      <a href="#">
        <span class="icon-picture"></span>
        <span class="text">Portfolio</span>    
      </a>
    </li>
    <br/>
    <li>
      <a href="#">
        <span class="icon-facetime-video"></span><span class="text">video</span>
      </a>
    </li>
  </ul>
</div>
 --%>
 <!--
 <script>
     $('li').click(function(){
  
  $(this).addClass('active')
       .siblings()
       .removeClass('active');
    
});
</script>
 -->