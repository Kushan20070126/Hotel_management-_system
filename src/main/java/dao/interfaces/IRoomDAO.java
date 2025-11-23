
package dao.interfaces;


import java.util.List;
import model.Room;


public interface IRoomDAO {
    
    boolean addRoom(Room room);
    boolean updateRoom(Room room);
    boolean deleteRoom(int id);
    Room getRoomByID(int id);
    List<Room> getAllRooms();
   
}
