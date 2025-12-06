package controller;

import java.sql.Date;
import java.util.List;
import model.Booking;
import service.BookingService;



public class BookingController{
    
    private BookingService bookingservice = new BookingService();
    
    
    public boolean createbooking(int roomId, int guestId, Date checkIn, Date checkOut){
        
        return bookingservice.CreateBooking(guestId, roomId, checkIn, checkOut);
    }
    
    public boolean updatebooking(int id ,Date checkIn, Date checkOut){
        
        return bookingservice.UpadateBooking(id, checkIn, checkOut);
        
    }
    
    public boolean deletebooking(int id){
        return bookingservice.DeleteBooking(id);
    }
    
    public boolean checkin(int bookingid){
        return bookingservice.CheckIn(bookingid);
    }
    
    public boolean checkout(int bookingid){
        
        return bookingservice.CheckOut(bookingid);
    }
    
    public Booking getbooking(int id){
        
        return bookingservice.GetBooking(id);
    }
    
    public List<Booking> getallbooking(){
        return bookingservice.GetAllBookings();
    }
    
    
}
