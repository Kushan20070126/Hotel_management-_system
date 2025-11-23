package dao.interfaces;

import java.util.List;
import model.Guest;

public interface IGuestDAO {
    boolean addGuest(Guest guest);
    boolean updateGuest(Guest guest);
    boolean deleteGuest(int id);
    Guest getGuestByID(int id);
    List<Guest> getAllGuest();
    
    
}
