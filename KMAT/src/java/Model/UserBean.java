
package Model;

/**
 * UserBean to set and get user details
 * @author Habiba Saim
 */


public class UserBean implements java.io.Serializable {
    private String username;
    private String password;
    
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
