
package dao.interfaces;

import java.util.List;
import model.Booking;

public interface IBookingDAO {
    
    boolean addBooking(Booking booking);
    boolean updateBooking(Booking booking);
    boolean deleteBooking(int id);
    Booking getBookingByID(int id);
    List<Booking> getAllBookings();
    List<Booking> getBookingByGuest(int guestId);
    List<Booking> getBookingByRoom(int roomId);
    
    
}
