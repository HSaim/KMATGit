<%-- 
    Document   : RecoverAccount
    Created on : Jan 5, 2017, 12:29:04 PM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">        
	<meta http-equiv="X-UA-Compatible" content="IE=edge">	
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="KMAT Sign up page" />
	<meta name="keywords" content="KMAT, sign up, name, email, password, address, phone, account, recover, forgotten" />
	<meta name="author" content="KMAT Team" />
        
        
        <jsp:include page="includes/link.jsp" />    
        <link rel="stylesheet" href="css/banner.css">
        <title>Account Recovery</title>       
    </head>
    <body>
        <div id = "wrapper">
            <!-- START page-->
            <div id = "page">
                <!-- START: header -->                
                <jsp:include page="includes/header.jsp" />                      
                <!-- END: header -->
                
                <!-- START: Banner -->
                 <aside id="banner" style="background-image: url(images/banner1.jpg);">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-8 col-md-offset-2">
                                <div class="banner-wrap">
                                    <div class="banner-intro">
                                        <h2>Recover your KMAT account <span></span></h2>
                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
		</aside>
                <!-- END: Banner-->
                
                <!-- START: Recover Account Form -->
                <div id="contact-section">
                    <div class="container">
                        <div class="row text-center">                            
                            <h2 >Recover  account</h2> 
                            
                        </div>
                        <span style="color: #4CAF50"> ${data}</span>
                        <form name = "signup" method="post" action="LoginController?action=recover-account" onSubmit="return verifyEmail()">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="text" class="form-control" name="username" placeholder="User Name*" onkeyup="nospaces(this);" required>
                                    </div>
                                </div>
                                </div>
                             <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="email" class="form-control" name="pri-email" placeholder="Primary Email*" required>
                                    </div>
                                </div>
                                </div>
                             <div class="row">
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="submit" value="Recover Account" class="btn btn-primary">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- END: Recover Account Form -->
                
                 <%-- adds js --%>
                <jsp:include page="includes/js.jsp" /> 
                
                <script src="js/signup-verifications.js"></script>
                <%-- footer.jspf integrates here --%>
