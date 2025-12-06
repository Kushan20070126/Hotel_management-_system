
package service;

import dao.RoomDAO;
import dao.interfaces.IRoomDAO;
import java.util.List;
import model.Room;

public class RoomService {
    
    private IRoomDAO roomDAO = new RoomDAO();
    
    public boolean AddRoom(String number, String type, double price, String status){
        if(number == null || number.isEmpty() || type == null || type.isEmpty() || price <= 0 || status == null || status.isEmpty()){
            status = "Available";
            return false;
        }
        
        Room room = new Room(0, number, type, price, status);
        
        return roomDAO.POST_ROOM(room);
        
    } 
    
    public boolean UpdateRoom(int id, String number, String type, double price, String status){
        
        Room room = roomDAO.GET_ROOM_ID(id);
        if(room == null){
            return false;
        }else{
            room.setNumber(number);
            room.setPrice(price);
            room.setStatus(status);
            room.setType(type);   
        
            return roomDAO.PUT_ROOM(room);
        }  
    }
    
    public boolean DeleteRoom(int id){
        
         return roomDAO.DELETE_ROOM(id);
    }
    
    public Room GetRoom(int id){
        return roomDAO.GET_ROOM_ID(id); 
    }
    
    public List<Room> GetAllRoom(){
        return roomDAO.GET_ROOMS();
    }
    
    public boolean isAvailable(int id){
        Room room = roomDAO.GET_ROOM_ID(id);
        
        if(room == null){
            return false;
        }
        
        return room.getStatus().equalsIgnoreCase("Available");
        
    }
    
   
    
}
