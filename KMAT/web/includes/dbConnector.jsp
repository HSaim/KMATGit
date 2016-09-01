<%-- 
    Document   : dbConnector
    Created on : Jul 29, 2016, 9:57:24 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import ="java.sql.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>

<%
  
    	String mySQLuser = "root";
    	String mySQLpwd = "century_77";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kmat",  mySQLuser, mySQLpwd) ;
        
    
  %>