
package service;

import dao.BookingDAO;
import dao.GuestDAO;
import dao.RoomDAO;
import dao.interfaces.IBookingDAO;
import dao.interfaces.IGuestDAO;
import dao.interfaces.IRoomDAO;
import java.sql.Date;
import java.util.List;
import model.Booking;
import model.Guest;
import model.Room;


public class BookingService {
    
    private IBookingDAO bookDAO = new BookingDAO();
    private IRoomDAO roomDAO = new RoomDAO();
    private IGuestDAO guestDAO = new GuestDAO();
    
    
    public boolean CreateBooking(int gid , int rid, Date checkIn, Date checkout, String status, double total){
        
        if(checkIn == null || checkout == null){
            System.err.println("Inavalid Date value");
            return false;
        }
        
        if(checkout.before(checkIn)){
            System.out.println("check out cannot be earlier than checkIn");
            return false;
        }
        
        
        Room room = roomDAO.GET_ROOM_ID(rid);
        if(room == null){
            System.err.println("Room Not found !!!");
            return false;
        }
        
        Guest guest = guestDAO.GET_GUEST_ID(gid);
        if(guest == null){
            System.err.println("Guest Not found !!!");
            return false;
        }
        
        if(room.getStatus().equalsIgnoreCase("Available")){
            System.out.println("Room is Available !!! ");
        }else{
            System.err.println("Room is Not Available !!!");
            return false;
        }
        
         long returnMS = checkout.getTime() - checkIn.getTime();
        int  days = (int)(returnMS / (1000*60 * 60 * 24));
        
        if(days <= 0 ){
            System.out.println("You cannot stay this hotel");
            return false;
        }
        
        double tot = days * room.getPrice();
        
        Booking booking  = new Booking(0,rid,gid,checkIn,checkout,"Pending",tot);
        
        boolean isSave = bookDAO.POST_BOOKING(booking);
        if(!isSave){
            return  false;
        }
        
        room.setStatus("Booked");
        roomDAO.PUT_ROOM(room);
        return true;
         
    }// add 
    
    
    
    public boolean UpadateBooking(int id, Date checkIn, Date checkOut){
        Booking booking = bookDAO.GET_BOOKING_ID(id);
        
        if(booking == null){
            System.out.println("Thai Booking  not found!");
            return false;
        }
        
        if(checkOut.before(checkIn)){
            System.out.println("check out cannot be earlier than checkIn");
            return false;
        }
        
           long returnMS = checkOut.getTime() - checkIn.getTime();
        int  days = (int)(returnMS / (1000*60 * 60 * 24));
        
        if(days <= 0 ){
           
            return false;
        }
        
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        
        Room romm = roomDAO.GET_ROOM_ID(booking.getRoomID());
        if(romm == null){
            return false;
        }
        double updatedTotal = days * romm.getPrice();
        booking.setToatalAmmount(updatedTotal);
        
        return bookDAO.PUT_BOOKING(booking);
             
    }//update
    
   public  boolean DeleteBooking(int id){
       Booking booking = bookDAO.GET_BOOKING_ID(id);
       
       if(booking == null){
           return false;
       }
       
       Room room = roomDAO.GET_ROOM_ID(booking.getRoomID());
       room.setStatus("Available");
       roomDAO.PUT_ROOM(room);
       
       return bookDAO.DELETE_BOOKING(id);
       
   }//delete
   
   
   public boolean CheckIn(int bookingID){
       
       Booking booking = bookDAO.GET_BOOKING_ID(bookingID);
       if(booking == null){
           return false;
       }
       
       booking.setStatus("CheckedIn");
       return  bookDAO.PUT_BOOKING(booking);
   }//check in
   
   public boolean CheckOut(int bookingID){
       Booking booking = bookDAO.GET_BOOKING_ID(bookingID);
       if(booking == null){
           return false;
       }
       
       booking.setStatus("CheckedOut");
       bookDAO.PUT_BOOKING(booking);
       
       Room room = roomDAO.GET_ROOM_ID(booking.getRoomID());
       
       room.setStatus("Available");
       roomDAO.PUT_ROOM(room);
       
       return true;
       
   }//checkout
   
   public Booking GetBooking(int id){
       
       return bookDAO.GET_BOOKING_ID(id);
   }//get one
   
   public List<Booking> GetAllBookings(){
       
       return bookDAO.GET_BOOKINGES();
   }// get all
   
   
    
    
    
    
    
    
    
    
    
    
    
    
    
}
