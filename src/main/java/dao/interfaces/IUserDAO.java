
package dao.interfaces;

import java.util.List;
import model.User;

public interface IUserDAO {
    
    boolean addUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    User getUserByID(int id);
    User Login(String username , String Password);
    List<User> getAllUser();
    
}
