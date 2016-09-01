<%-- 
    Document   : AddResource
    Created on : Aug 18, 2016, 9:38:58 AM
    Author     : Habiba Saim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <title>Add Resource</title>
    </head>
    
    <body>
         <%-- Menubar starts --%>
        <jsp:include page="includes/menubarUser.jsp" />
        <%-- Menubar Ends --%>

        <%-- Slider starts --%>
        <div id = simple>  
             <jsp:include page="includes/topSliderImages.jsp" />
        </div>

        <%-- Sidebar Menu Starts --%>
        <jsp:include page="includes/verticalUserMenu.jsp" />
        <%-- Sidebar Menu Ends --%>
        
        <%-- Add Resource Block Starts --%>   
        <div align = "center" id = "Login">
            <form method="post" action="AddResourceValidation.jsp">
                <table style="width:auto"align="center">
                    <tr>
                        <th colspan="2" align = "center">
                        Add Resource<br/>
                        </th>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td style="width:auto"align="right">
                            Name:
                        </td>
                        <td style="width:auto"align="left">
                            <input type = "text" name="add-name" id="add-name" placeholder="Name of resourse">
                        </td>
                    </tr>

                    <tr>
                        <td style="width:auto"align="right">
                            Description:
                        </td>
                        <td style="width:auto"align="left">
                            <textarea id="discrp" rows= "4" col="70" placeholder="Enter a brief description here."></textarea>
                        </td>
                    </tr>
                    
                    <tr>
                        <td style="width:auto"align="right">
                            Path / Link to Resource:
                        </td>
                        <td style="width:auto"align="left">
                            <input type = "text" name="add-name" id="add-name" placeholder="Address of Resource">
                            <input type = "file" name = "datafile">
                        </td>
                    </tr>
                    
                    <tr>
                        <td>
                            Tools to attach with:
                        </td>
                        <td>
                            <input type = "checkbox" name ="tools"value= "Visual Studio">Visual Studio<br>
                            <input type = "checkbox" name ="tools"value= "Eclipse">Eclipse<br>
                            <input type = "checkbox" name ="tools"value= "Net Beans">Net Beans<br>
                            <input type = "checkbox" name ="tools"value= "Andriod">Android
                        </td>
                    </tr>
                    
                    <tr>                       
                        <td colspan="2" align="center">
                            <input type="submit" value="Add Resource" />
                        </td>
                    </tr>

                </table>             

                    <!--<input type= "button" id = "btn" align="center" value="Add Resouce" onclick="addres()" />
                    -->
            </form>
        </div>
        <%-- Add Resource Block Ends --%> 
         
        <%-- adds Top Slider, login functions --%>
        <jsp:include page="includes/js.jsp" /> 
        
        <%-- Includes footer
        <jsp:include page="includes/footer.jsp" />
        
    </body>
</html>
 --%>