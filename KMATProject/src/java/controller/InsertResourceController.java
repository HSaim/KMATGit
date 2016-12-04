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
import model.ResourceBean;
import model.ResourceDAO;

/**
 *
 * @author Fahad Akhtar
 */
public class InsertResourceController extends HttpServlet {
    protected void doGET(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        out.println("resource controller handling request from GET");
        }
    
    protected void doPOST(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        out.println("resource controller handling request from POST");
        
        File file;
        int maxFileSize = 5000*1024;
        int maxMemSize = 5000*1024;
        ServletContext context = request.getServletContext();
        String contentType = request.getContentType();
        String filePath = context.getInitParameter("file-upload");
        String ext = "";
        long sizeInBytes = 0;

        if ((contentType.indexOf("multipart/form-data") >= 0)){
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);
            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("c:\\temp"));
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax( maxFileSize );
            try{
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);
                // Process the uploaded file items
                Iterator i = fileItems.iterator();/*
                out.println("<html>");
                out.println("<head>");
                out.println("<title>JSP File upload</title>");
                out.println("</head>");
                out.println("<body>");*/
                while ( i.hasNext () ){
                    FileItem fi = (FileItem)i.next();
                    if ( !fi.isFormField () ){
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        boolean isInMemory = fi.isInMemory();
                        sizeInBytes = fi.getSize();
                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 ){
                            file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                            }
                        else{
                            file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                            }
                        fi.write( file ) ;
                        out.println("Uploaded Filename: " + filePath + fileName + "<br>");
                        ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                        out.println(ext);
                        out.println(sizeInBytes);
                        String fileType = typeID(ext);
                        out.println(fileType);
                        }
                    }
                out.println("</body>");
                out.println("</html>");
                }
            catch(Exception ex) {
                System.out.println(ex);
                }
            }
        else{
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>"); 
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>"); 
            out.println("</body>");
            out.println("</html>");
            }
        try{
            ResourceBean resource = new ResourceBean();
            //GET ADD RESOURCE ELEMENT
            resource.setResourceName(request.getParameter("add-name"));
            resource.setResourceDiscription(request.getParameter("add-description"));
            resource.setResourceLink(request.getParameter("add-link"));
            resource.setFileName(request.getParameter("datafile"));
            resource.setResourceFormat(ext);
            resource.setResourcePath(filePath);
            resource.setResourceSize(sizeInBytes);
            resource.setHidden(request.getParameter("hidden"));
            
            resource = ResourceDAO.insertResource(resource);
            
            //If insertion done successfully and done by existing user
            if (resource.isAdded() && resource.getHidden().equalsIgnoreCase("adduser")){
                //HttpSession session = request.getSession(true);
                //session.setAttribute("CurrentSessionUser", user);//.getUsername());
                //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/view/UserHome.jsp");
                //dispatcher.forward(request, response);
                response.setContentType("text/html;charset=UTF-8");
                try {
                    //PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('One record inserted successfully!');");
                    out.println("location='home';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
      
            }
            //If duplicate user name is given in the form from an existing user
            else if (!resource.isAdded() && resource.isDuplicateResource() && resource.getHidden().equalsIgnoreCase("addresource")){  
                response.setContentType("text/html;charset=UTF-8");
                try {
                    //PrintWriter out = response.getWriter();
                    String resourceName= resource.getResourceName();
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User name: " + resource.getResourceName() +" already exists, choose a new one!');");
                    out.println("location='AddUser';");
                    out.println("</script>");                    
                }
                catch (Exception e){
                    System.out.println("Alert could not be generated. Error: " + e);
                }
            }
            
            
      
            
            
            
            
            
            
        }
        catch(Throwable theException){
            theException.printStackTrace();
            
        }
        
        finally{
            
        }
        
        }
    private String typeID(String extention){
        String typeOfFile = null;
        if(extention.equals("jpg")||extention.equals("png")||extention.equals("bmp")||extention.equals("gif")
                ||extention.equals("jpeg")||extention.equals("svg")||extention.equals("tiff")){
            typeOfFile = "Image";
            }
        else if(extention.equals("avi")||extention.equals("mpeg")||extention.equals("qt")||extention.equals("mp4")
                ||extention.equals("webm")||extention.equals("flv")||extention.equals("mkv")||extention.equals("vob")
                ||extention.equals("wmv")){
            typeOfFile = "Video";
            }
        else if(extention.equals("css")||extention.equals("doc")||extention.equals("docx")||extention.equals("dotx")
                ||extention.equals("html")||extention.equals("pdf")||extention.equals("potx")||extention.equals("ppsx")
                ||extention.equals("ppt")||extention.equals("pptx")||extention.equals("txt")||extention.equals("sgml")
                ||extention.equals("tsv")||extention.equals("xlam")||extention.equals("xls") ||extention.equals("xlsb") 
                ||extention.equals("xlsx")||extention.equals("xltx")){
            typeOfFile = "Document";
            }
        return (typeOfFile);
        }
    /**
    * Returns a short description of the servlet.
    *
    * @return a String containing servlet description
    */

    @Override
    public String getServletInfo() {
        return "Controller for Adding resources";
    }// </editor-fold>



}

         
        
    
    

