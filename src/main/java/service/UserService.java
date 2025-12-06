
package service;

import dao.UserDAO;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import model.User;

public class UserService {
    
 private UserDAO userDAO = new UserDAO();
 
 public boolean RegisterUser(String username, String password, String role){
     
     
     if(username == null || username.isEmpty()){
         
         
         System.err.println("User Name Cannot be empty");
         return false;         
     }
     
     if(password == null || password.isEmpty()){
         
         System.out.println("Password Cannot be empty");
         return false;
     }
     
     if(role == null || role.isEmpty()){
         
         role = "User";
        
     }
     
     if(userDAO.GET_USER_USERNAME(username) != null){
         
         System.out.println("Username Alrady exsits !!");
         
         return false;
     }
     
     String encryptedPass = HashPassword(password);
     
     User user = new User(0,username,encryptedPass,role);
     
     return userDAO.POST_USER(user);
     
 }//register user
 
 
 
 
 public User Login(String username , String password){
     
     if(username == null || password == null){
         return null;
         
     }
     
     User user = userDAO.GET_USER_USERNAME(username);
     
     if(user == null){
         
         System.out.println("User not found !!");
         return null;
         
     }
     
    String checkPass = HashPassword(password);
    
    if(!checkPass.equals(user.getPassword())){
        
        System.out.println("Invalied Password !!");
        return  null;
    }
    
    return user;
     
 }//login
 
 
 public boolean UpdateUser(int id, String username, String password, String role){
     
     
     User userExsist = userDAO.GET_USER_ID(id);
     
     if(userExsist == null){
         System.out.println("User not Found !!");
         return false;
     }
     
     userExsist.setUserName(username);
     
     if(password !=null || !password.isEmpty()){
         userExsist.setPassword(HashPassword(password));
         
     }
     
     userExsist.setRole(role);
     
    return userDAO.PUT_USER(userExsist);
     
 }
 
 public boolean DeleteUser(int id){
     return userDAO.DELETE_USER(id);
 }
 
 public User GetUser(int id){
     return userDAO.GET_USER_ID(id);
 }
 
 public List<User> GetAllUser(){
     return userDAO.GET_USERS();
 }
 

 private String HashPassword(String password){
    
     try {
         
         MessageDigest mg = MessageDigest.getInstance("SHA-256");
         byte[] diget = mg.digest(password.getBytes());
         BigInteger intg = new BigInteger(1,diget);
         
         
         return intg.toString(16);
         
     } catch (Exception e) {
         
         System.out.println("passwod encryption Error : " + e.getMessage());
         return null;
     }
    
}
    
}



