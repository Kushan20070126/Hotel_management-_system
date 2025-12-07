
package controller;

import java.util.List;
import model.Guest;
import service.GuestService;


public class GuestController {
    
    private GuestService guestservice = new GuestService();
    
    public boolean addguest(String name, String phone, String email,String nic, String address){
        
        return guestservice.AddGuest(name, phone, email, nic, address);
        
    }
    
    public boolean  updateguest(int id, String name, String phone, String email, String nic ,String address){
        
        return guestservice.UpdateGuest(id, name, phone, email, nic, address);
    }
    
    public boolean deleteguest(int id){
        
        return guestservice.DeleteGuest(id);
    }
    
    public Guest getguest(int id){
        
        return guestservice.GetGuest(id);
    }
    
    public List<Guest> getallguest(){
        return guestservice.GetAllGuest();
    }
    
    
}
