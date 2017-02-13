/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



    (function() {
        importClass(model.LoginUserBean);
        <% LoginUserBean currentUser = ((LoginUserBean) (session.getAttribute("CurrentSessionUser"))); %>
         var v1 = '<%= currentUser.getUsername() %>';                      
         var label = document.getElementById("uname");

         label.innerHTML = v1;
         //label.style.display = "block";
     })();
