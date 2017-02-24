/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Habiba Saim
 */

@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
                 maxFileSize=1024*1024*10,      // 10MB
                 maxRequestSize=1024*1024*50)   // 50MB

public class Upload extends HttpServlet {

     /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /**
     * handles file upload
     */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int done =0; //Uplaod sucessful or not
        String result = "";
        ServletContext servletContext = request.getServletContext();
        
        // Retrieves <input type="text" name="description">
        String description = request.getParameter("description"); 
        
        /** For one file upload **/
        //Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        //String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
                
        // gets absolute path of the web application
        String appPath = servletContext.getRealPath("/");
       
        // constructs path of the directory to save uploaded file        
        String savePath = appPath +  SAVE_DIR;
        
        /** Any of the following way can be used **/
        // File fileSaveDir = new File(appPath + "/uploadFiles");
         File fileSaveDir = new File(savePath);
         
        // creates the save directory if it does not exists                
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
            //fileSaveDir.mkdir();
        }
        
        // Retrieves <input type="file" name="file" multiple="true">
        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList()); 
        
        for (Part filePart : fileParts) {
            
            /** Any of the following way can be used **/
            // String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.            
            String fileName = extractFileName(filePart);
           
            // Refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            
           
            File file = new File(fileSaveDir, fileName);
            //File file1 = new File("d:\\", fileName);  //Local path
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath() , StandardCopyOption.REPLACE_EXISTING);
                done = 1;
                result = "Upload has been done successfully!";
            }
            catch (Exception e){
                //e.getLocalizedMessage();
                result = e.toString();
                done =0;
            }            
        }
        
        if (done == 1){
            request.setAttribute("message", result);            
        }
        else {
            request.setAttribute("message", result);
        }
        
        servletContext.getRequestDispatcher("/Message.jsp").forward(
                request, response);
        
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}
