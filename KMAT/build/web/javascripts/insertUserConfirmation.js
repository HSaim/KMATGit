/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function validLogin(){
if (document.form.user_name.value === "" || document.form.user_name.value === null){
alert ( "Please enter Login Name." );
document.loginform.user_name.focus();
return false;
}

//if (document.form.password.value == ""){
//alert ( "Please enter password." );
//document.userform.password.focus();
//return false;
//}
alert ( "Welcome User" );
return true;
}
