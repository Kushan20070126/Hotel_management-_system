
package dao.interfaces;

import java.util.List;
import model.User;

public interface IUserDAO {
    
    boolean POST_USER(User user);
    boolean PUT_USER(User user);
    boolean DELETE_USER(int id);
    User GET_USER_ID(int id);
    User GET_LOGIN(String username , String Password);
    List<User> GET_USERS();
    
}
