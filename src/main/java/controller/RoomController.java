
package controller;

import java.util.List;
import model.Room;
import service.RoomService;


public class RoomController {
    
    private RoomService roomservice = new RoomService();
    
    
    public boolean addroom(String number, String type, double price, String status){
        
        return roomservice.AddRoom(number, type, price, status);
    }
    
    public boolean updateroom(int id, String number, String type, double price, String status){
        return roomservice.UpdateRoom(id, number, type, price, status);
    }
    
    public boolean deleteroom(int id){
        
        return roomservice.DeleteRoom(id);
    }
    
    public Room getroom(int id){
        
        return roomservice.GetRoom(id);
    }
    
    public List<Room> getallrooms(){
        return roomservice.GetAllRoom();
    }
    
    public boolean isroomAvlabale(int id){
        return roomservice.isAvailable(id);
    }
    
    
}
