<%-- 
    Document   : left-sidebar
    Created on : Oct 31, 2016, 12:32:36 PM
    Author     : Habiba Saim
--%>

<%@page import="model.LoginUserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    

<div class="sidebox" id = "navbar">
    <!--<h3 class="sidebox-lead">KMAT Components</h3>	-->
    <ul class="sidebox-menu">
        <li>
            <!-- <a href="#">-->

            <div class="sidebox-menu-blurb">
                Users 
                <!--<span class="sidebox-menu-meta">Add User</span>-->
            </div>
            <!-- </a>-->
            <ul>
                
                <li class = "view-users"><a href="GetUsersController?action=get-all-users"> 
                <!--<li class = "view-users"><a href="view-users"> -->
                        <div class="sidebox-menu-blurb-sub">
                            View Users
                        </div>
                </a></li>
                <li class = "add-user"><a href="add-user">
                        <div class="sidebox-menu-blurb-sub">
                            Add User
                        </div>
                </a></li>
            </ul>
        </li>
        <li>
            <div class="sidebox-menu-blurb">
                Roles                                           
            </div>
            <ul>
                <li class = "view-roles"><a href="view-roles">
                        <div class="sidebox-menu-blurb-sub">
                            View Roles 
                        </div>
                </a></li>
                <li class = "add-role"><a href="add-role">
                        <div class="sidebox-menu-blurb-sub">
                            Add Role 
                        </div>
                </a></li>
            </ul>                                    
        </li>
        <li>
            <div class="sidebox-menu-blurb">
                Composition Ladders 
                <ul>
                    <li class = "view-c-ladders"><a href="view-composition-ladders">
                            <div class="sidebox-menu-blurb-sub">
                                View Composition Ladders
                            </div>
                    </a></li>
                    <li class = "add-c-ladder"><a href="add-composition-ladder">
                            <div class="sidebox-menu-blurb-sub">
                                Add Composition Ladder
                            </div>
                    </a></li>
                </ul>
            </div>
        </li>
        <li>
            <div class="sidebox-menu-blurb">
                Process Ladders 
                <ul>
                    <li class = "view-p-ladders"><a href="view-process-ladders">
					<!--<li class = "view-p-ladders"><a href="GetLaddersController?action=get-all-process-ladders">-->
                            <div class="sidebox-menu-blurb-sub">
                                View Process Ladders
                            </div>
                    </a></li>
                    <li class = "add-p-ladder"><a href="add-process-ladder">
                            <div class="sidebox-menu-blurb-sub">
                                Add Process Ladder
                            </div>
                    </a></li>
                </ul>
            </div>

        </li>
        <li>
            <div class="sidebox-menu-blurb">
                Tools 
                <ul>
                    <li class = "view-tools"><a href="view-tools">
                            <div class="sidebox-menu-blurb-sub">
                                View Tools
                            </div>
                    </a></li>
                    <li class = "add-tool"><a href="add-tool">
                            <div class="sidebox-menu-blurb-sub">
                                Add Tool
                            </div>
                    </a></li>
                </ul>
            </div>
        </li>
        <li>
            <div class="sidebox-menu-blurb">
                Resources 
                <ul>
                    <li class = "view-resources"><a href="GetResourceController?action=get-all-resources">
                            <div class="sidebox-menu-blurb-sub">
                                View Resources
                            </div>
                    </a></li>
                    <li class = "add-resource"><a href="add-resource">
                            <div class="sidebox-menu-blurb-sub">
                                Add Resource
                            </div>
                    </a></li>
                </ul>
            </div>
        </li>
        <li>
            <%--
            <div class="extend-sidebar"> <!-- Spaces are added here to stretch the sidebar -->
                <p></p>
            </div>
            --%>
        </li>
    </ul>

</div>

                     