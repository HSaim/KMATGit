/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var name = "fahad";
var paas = 1234;
var sidebar = document.getElementById("nav");
var log = document.getElementById("logbtn");
var out = document.getElementById("outbtn");
var msg = document.getElementById("helomsg");
var msgvalue = document.getElementById("welcome");
	
function adduser(){
    document.getElementById("add-user").style.display = "block";
    document.getElementById("Login").style.display = "none";
    document.getElementById("tool-add").style.display="none";
    document.getElementById("tool-added").style.display="none";
    document.getElementById("delmsg").style.display="none";
    document.getElementById("added").style.display="none";
    document.getElementById("to-add").style.display="none";
    //document.getElementById("add-user").style.display="none";
    document.getElementById("Login").style.display="none";	
}

function login(){
    alert("helo");
    document.getElementById("Login").style.display = "block";
    document.getElementById("add-user").style.display = "none";
    document.getElementById("tool-add").style.display="none";
    document.getElementById("tool-added").style.display="none";
    document.getElementById("delmsg").style.display="none";
    document.getElementById("added").style.display="none";
    document.getElementById("to-add").style.display="none";
    //document.getElementById("add-user").style.display="none";
    //document.getElementById("Login").style.display="none";
}
	
function loggedin(){	
    var name = "fahad";
    var paas = 1234;
    var uname = document.getElementById("login-username").value;
    var password = document.getElementById("login-password").value;
    
    if(uname === name && password === paas){
        log.style.display="none";
        out.style.display="block";
        msgvalue.innerHTML ="Hi"+" "+name; 
        msg.style.display="block";
        document.getElementById("Login").style.display = "none";
        sidebar.style.display = "block";
        //document.getElementById("tool-add").style.display="block";
        document.getElementById("tool-added").style.display="none";
        document.getElementById("delmsg").style.display="none";
        document.getElementById("added").style.display="none";
        document.getElementById("to-add").style.display="none";
        document.getElementById("add-user").style.display="none";
        document.getElementById("Login").style.display="none";

        alert("you are logged In");
    }
    else{
        alert("login failed");
    }
}
function logout(){

    out.style.display="none";
    msg.style.display="none";
    sidebar.style.display="none";
    log.style.display="block";
    document.getElementById("tool-add").style.display="none";
    document.getElementById("tool-added").style.display="none";
    document.getElementById("delmsg").style.display="none";
    document.getElementById("added").style.display="none";
    document.getElementById("to-add").style.display="none";
    document.getElementById("add-user").style.display="none";
    document.getElementById("Login").style.display="none";
}

function addres(){
    //alert("Resource");
    document.getElementById("to-add").style.display="none";
    document.getElementById("added").style.display="block";
    document.getElementById("res-name").innerHTML = document.getElementById("add-name").value;
    document.getElementById("disc").innerHTML = document.getElementById("discrp").value;
    document.getElementById("tool-add").style.display="none";
    document.getElementById("tool-added").style.display="none";
    document.getElementById("delmsg").style.display="none";
    document.getElementById("added").style.display="none";
    //document.getElementById("to-add").style.display="none";
    document.getElementById("add-user").style.display="none";
    document.getElementById("Login").style.display="none";
}
function del(){
    document.getElementById("delmsg").style.display="block";
    added.style.display= "none";
    document.getElementById("tool-add").style.display="none";
    document.getElementById("tool-added").style.display="none";
    //document.getElementById("delmsg").style.display="none";
    //document.getElementById("added").style.display="none";
    document.getElementById("to-add").style.display="none";
    document.getElementById("add-user").style.display="none";
    document.getElementById("Login").style.display="none";	
}

function addresource(){
    document.getElementById("to-add").style.display="block";
    document.getElementById("tool-add").style.display="none";
    document.getElementById("tool-added").style.display="none";
    document.getElementById("delmsg").style.display="none";
    document.getElementById("added").style.display="none";
    //document.getElementById("to-add").style.display="none";
    document.getElementById("add-user").style.display="none";
    document.getElementById("Login").style.display="none";
}

function myfunction2(){
    //window.alert(name);
    alert("helo");
    document.getElementById("tool-add").style.display="none";
    document.getElementById("tool-added").style.display="block";
    document.getElementById("tool-name").innerHTML = document.getElementById("add-tool-name").value;
    //document.getElementById("tool-add").style.display="block";
    //document.getElementById("tool-added").style.display="none";
    document.getElementById("delmsg").style.display="none";
    document.getElementById("added").style.display="none";
    document.getElementById("to-add").style.display="none";
    document.getElementById("add-user").style.display="none";
    document.getElementById("Login").style.display="none";
} 

function addtool(){
    document.getElementById("tool-add").style.display="block";
    document.getElementById("tool-added").style.display="none";
    document.getElementById("delmsg").style.display="none";
    document.getElementById("added").style.display="none";
    document.getElementById("to-add").style.display="none";
    document.getElementById("add-user").style.display="none";
    document.getElementById("Login").style.display="none";
	
}



