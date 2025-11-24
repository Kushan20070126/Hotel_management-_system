package dao.interfaces;

import java.util.List;
import model.Guest;

public interface IGuestDAO {
    boolean POST_GUEST(Guest guest);
    boolean PUT_GUEST(Guest guest);
    boolean DELETE_GUEST(int id);
    Guest GET_GUEST_ID(int id);
    List<Guest> GET_GUESTS();
    
    
}
