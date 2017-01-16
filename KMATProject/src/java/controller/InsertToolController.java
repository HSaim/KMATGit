/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import javax.servlet.*; 
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import model.ConnectionManager;
import model.LoginUserBean;
import model.ResourceBean;
import model.ResourceDAO;

/**
 *
 * @author Fahad Akhtar
 */
@WebServlet(name = "InsertToolController", urlPatterns = {"/insert-tool"})
public class InsertToolController extends HttpServlet{
    
    static Connection currentCon = null;
}
