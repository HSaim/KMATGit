<%-- 
    Document   : Home
    Created on : Feb 24, 2017, 12:03:26 PM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Multiple Files </title>
    </head>
    <body>
        <form action="Upload" method="post" enctype="multipart/form-data">
            <input type="text" name="description" />
            <input type="file" name="file" multiple="true"/>
            <input type="submit" />
        </form>
    </body>
</html>
