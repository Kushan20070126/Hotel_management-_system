
package dao.interfaces;


import java.util.List;
import model.Room;


public interface IRoomDAO {
    
    boolean POST_ROOM(Room room);
    boolean PUT_ROOM(Room room);
    boolean DELETE_ROOM(int id);
    Room GET_ROOM_ID(int id);
    List<Room> GET_ROOMS();
    int GET_TOTAL_ROOMS();
    int GET_OCCUPIED_ROOMS() ;
    
   
}
