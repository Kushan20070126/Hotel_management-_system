package controller;

import model.User;
import service.UserService;
import java.util.List;

public class UserController {

    private UserService userService = new UserService();

    
    public boolean registeruser(String username, String password, String role) {
        
        return userService.RegisterUser(username, password, role);
    }

    public boolean updateuser(int id, String username, String password, String role) {
        
        return userService.UpdateUser(id, username, password, role);
    }

    public boolean deleteuser(int id) {
        
        return userService.DeleteUser(id);
    }

    public User getuser(int id) {
        
        return userService.GetUser(id);
    }

    public List<User> getAllusers() {
        
        return userService.GetAllUser();
    }

    public User login(String username, String password) {
        
        
        return userService.Login(username, password);
    }
}
