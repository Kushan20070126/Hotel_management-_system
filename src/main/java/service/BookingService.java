
package service;

import dao.BookingDAO;
import dao.interfaces.IBookingDAO;
import java.sql.Date;


public class BookingService {
    
    private IBookingDAO bookDAO = new BookingDAO();
    
    
    public boolean AddBooking(int gid , int rid, Date cID, Date cOD, String status, double tot){
        
        if(gid == -1){
            return false;
        }
        
    }
    
    
    
}
