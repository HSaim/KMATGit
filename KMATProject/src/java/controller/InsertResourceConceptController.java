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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import model.ConnectionManager;
import model.LoginUserBean;
import model.ResourceBean;
import model.ResourceDAO;

@WebServlet(name = "InsertResourceConceptController", urlPatterns = {"/insert-resource-concept"})
public class InsertResourceConceptController extends HttpServlet {
    
    static Connection currentCon = null;
    static ResultSet rs = null;  
    static Statement stmt = null;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        PrintWriter out = response.getWriter();
        out.println("resource controller handling request from GET");
        }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException{
        DiskFileItemFactory factory1 = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory1.setRepository(repository);

        PrintWriter out = response.getWriter();
        //PrintWriter out = response.getWriter();
        //out.println("resource controller handling request from POST");
        File file;
        
        int maxFileSize = 5000*1024;
        int maxMemSize = 5000*1024;
    
        ServletContext context = request.getServletContext();
        ResourceBean resource = new ResourceBean();
        //String contentType = request.getContentType();
        String filePath = context.getInitParameter("file-upload");
        String contentType = request.getContentType();
        String ext = "";
        LoginUserBean currentUser = (LoginUserBean)request.getSession(false).getAttribute("CurrentSessionUser");
        String name = currentUser.getUsername();
        String searchUserID = "select user_id from user_tbl where username='"+ name+"'";
        int sizeInBytes = 0;
        String fileType="";
        int uid = 0;
        currentCon = ConnectionManager.getConnection();
        boolean more;
        try
        {
            stmt=currentCon.createStatement();
            rs = stmt.executeQuery(searchUserID);	        
            more = rs.next();
            if(more){
                uid = rs.getInt("user_id");
                resource.setUserID(uid);
            }
        }
        catch(SQLException ex){
        //todo: Handle Sql Exception
        }
        
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
                Iterator i = fileItems.iterator();
                
                while ( i.hasNext () ){
                    FileItem fi = (FileItem)i.next();
                    resource.setResourceName(fi.getName());
                    if ( !fi.isFormField () ){
                        String fileName = fi.getName();
                        sizeInBytes = (int)fi.getSize();
                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 ){
                            file = new File( filePath + fileName.substring( fileName.lastIndexOf("\\"))) ;
                            }
                        else{
                            file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                            }
                        fi.write( file ) ;
                        //out.println("Uploaded Filename: " + filePath + fileName);
                        out.println(filePath + fileName);
                        resource.setResourcePath(filePath);
                        resource.setFileName(fileName);
                        ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
                        resource.setResourceFormat(ext);
                        resource.setResourceSize(sizeInBytes);
                        //fileType = typeID(ext);
                        fileType = ext;
                        resource.setFileType(fileType);
                        }
                    }
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
            resource = ResourceDAO.insertResource(resource);
            
            //If insertion done successfully and done by existing user
            if (resource.isAdded() && resource.getHidden().equalsIgnoreCase("AddResource")){
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
                    out.println("alert('Resource name: " + resource.getResourceName() +" already exists, choose a new one!');");
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
            //request.getRequestDispatcher("/AddConceptMap.jsp").forward(request, response);
            String path = request.getContextPath() + "/add-concept-map";
            //response.sendRedirect(path);
            //request.forward();
        }
    }
    
    public String typeID(String extention){
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
        else{
            typeOfFile = "File Type not Identified.";
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

         
        
    
    
