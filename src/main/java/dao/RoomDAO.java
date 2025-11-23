
package dao;

import java.sql.*;
import dao.interfaces.IRoomDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.Room;
import util.DBConnection;

public class RoomDAO implements IRoomDAO{
    
    private Connection con = DBConnection.getInstance().getConnection();

    @Override
    public boolean addRoom(Room room) {
        try{
            
            PreparedStatement st = con.prepareStatement("insert into rooms(number, type, price, status) values (?, ?, ?, ?)");
            st.setString(1, room.getNumber());
            st.setString(2, room.getType());
            st.setDouble(3, room.getPrice());
            st.setString(4, room.getStatus());
            
            
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
           
        
        }catch(Exception e){
            
            System.err.println("Somthing went Wrong Adding Room " + e.getMessage());    
            return false;
        }
        
        
    }

    @Override
    public boolean updateRoom(Room room) {
        try{
            
            PreparedStatement st = con.prepareStatement("Update rooms set number=?,type=? ,price=? , status=? where id=?");
            st.setString(1, room.getNumber());
            st.setString(2, room.getType());
            st.setDouble(3, room.getPrice());
            st.setString(4, room.getStatus());
            st.setInt(5, room.getID());
            
             if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
            System.err.println("Somthing went Wrong Updating Room " + e.getMessage());    
            return false;
        }
        
       

    }

    @Override
    public boolean deleteRoom(int id) {
        
        try{
            PreparedStatement st = con.prepareStatement("delete from rooms where id=?");
            st.setInt(1, id);
            
            if(st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(Exception e){
             System.err.println("Somthing went Wrong Deleting Room " + e.getMessage());    
            return false;
        }
      
    }

    @Override
    public Room getRoomByID(int id) {
        
        try{
            PreparedStatement st = con.prepareStatement("select * from rooms where id=? ");
            st.setInt(1, id);
            
            ResultSet rs = st.executeQuery();
            
            
            if(rs.next()){
                
               Room data = new Room(
                    rs.getInt("id"),
                    rs.getString("number"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getString("status")
                        
                );
               return  data;
                
            }

            
            
        }catch(Exception e){
              System.err.println("Somthing went Wrong fetching db Room Data " + e.getMessage());    
             return null;
        }
         return null;
         
    }

    @Override
    public List<Room> getAllRooms() {
        
        List<Room> data = new ArrayList<>();
        
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from rooms");
            
            while(rs.next()){
                
                Room rooms = new Room(
                    rs.getInt("id"),
                    rs.getString("number"),
                    rs.getString("type"),
                    rs.getDouble("price"),
                    rs.getString("status")
                        
                );
                
                data.add(rooms);    
            }
            
        }catch(Exception e){
           System.err.println("Somthing went Wrong fetching  All db Rooms Data " + e.getMessage());  
           return null;
        }
        
        return data;
    }
    
    
}
