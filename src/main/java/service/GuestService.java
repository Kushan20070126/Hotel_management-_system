
package service;

import dao.GuestDAO;
import dao.interfaces.IGuestDAO;
import java.util.List;
import model.Guest;

public class GuestService {
    
    private IGuestDAO guestDAO = new GuestDAO();
    
    public boolean AddGuest(String name, String phon , String mail, String npid,String add){
        
        if(name == null || name.isEmpty()){
            System.out.println("name is cannot be empty");
            return false;
        }
        
        String phone_regex = "^(?:\\+94|0)(?:7[0-8]\\d{7}|(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)\\d{6})$";
        String email_regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        
        if (phon == null || !phon.matches(phone_regex) || phon.isEmpty()){
           
            System.out.println("Invalid phone number");
            return false;
        }
        
        if(mail == null || !mail.matches(email_regex)  || mail.isEmpty()){
            
            System.out.println("Invalid Email number");
            return false;
        }
        
        if(npid == null || npid.isEmpty()){
            System.out.println("NIC OR Pastport number is cannot be empty");
            return false;
            
        }
        if(add == null || add.isEmpty()){
            System.out.println("Address is cannot be empty");
            return false;
            
        }
        
        Guest guest = new Guest(0,name,phon,mail,npid,add);   
        
        return guestDAO.POST_GUEST(guest);
        
        
    }
    
    public boolean UpdateGuest(int id,String name, String phon , String mail, String npid,String add){
        
        Guest guest = guestDAO.GET_GUEST_ID(id);
        
        if(guest == null){
            return false;
        }else{
            if(name == null || name.isEmpty()){
            System.out.println("name is cannot be empty");
            return false;
        }
        
        String phone_regex = "^(?:\\+94|0)(?:7[0-8]\\d{7}|(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)\\d{6})$";
        String email_regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        
        if (phon == null || !phon.matches(phone_regex) || phon.isEmpty()){
           
            System.out.println("Invalid phone number");
            return false;
        }
        
        if(mail == null || !mail.matches(email_regex)  || mail.isEmpty()){
            
            System.out.println("Invalid Email number");
            return false;
        }
        
        if(npid == null || npid.isEmpty()){
            System.out.println("NIC OR Pastport number is cannot be empty");
            return false;
            
        }
        if(add == null || add.isEmpty()){
            System.out.println("Address is cannot be empty");
            return false;
            
        }
            guest.setName(name);
            guest.setPhone(phon);
            guest.setEmail(mail);
            guest.setNICORPassport(npid);
            guest.setAddress(add);
            
            return guestDAO.PUT_GUEST(guest);
        }
        
        
    }
    
    public boolean DeleteGuest(int id){
        
        return guestDAO.DELETE_GUEST(id);
        
    }
    
    public Guest GetGuest(int id){
        return guestDAO.GET_GUEST_ID(id);
        
    }
    
    public List<Guest> GetAllGuest(){
        
        return guestDAO.GET_GUESTS();
    }
    
    
    
    
    
}
