/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Habiba Saim
 */


public class UserBean {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    public boolean valid;
    
    public String getPassword() {
       return password;
    }

    public void setPassword(String newPassword) {
       password = newPassword;
    }


    public String getUsername() {
       return username;
    }

    public void setUserName(String newUsername) {
       username = newUsername;
    }

    public boolean isValid() {
       return valid;
    }

    public void setValid(boolean newValid) {
       valid = newValid;
    }	
    
}
