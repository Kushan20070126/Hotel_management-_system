package service;

import dao.BookingDAO;
import dao.GuestDAO;
import dao.PaymentDAO;
import dao.RoomDAO;
import model.ReportData;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReportService {

    private PaymentDAO paymentDAO = new PaymentDAO();
    private BookingDAO bookingDAO = new BookingDAO();
    private GuestDAO guestDAO = new GuestDAO();
    private RoomDAO roomDAO = new RoomDAO();

    public double getTotalIncome(Date start, Date end) {
        return paymentDAO.GET_TOTAL_INCOME(start, end);
    }

    public int getBookingCount(Date start, Date end) {
        return bookingDAO.GET_BOOKING_COUNT(start, end);
    }

    public int getGuestCount(Date start, Date end) {
        return guestDAO.GET_GUEST_COUNT(start, end);
    }

    public Map<String, Integer> getRoomTypeReport() {
        return bookingDAO.GET_ROOM_TYPE_USAGE();
    }

    public double getOccupancyRate(Date start, Date end) {
        int totalRooms = bookingDAO.GET_TOTAL_ROOMS();
        int occupiedRooms = bookingDAO.GET_OCCUPIED_ROOMS(start, end);

        if(totalRooms == 0) return 0.0;
        return (occupiedRooms / (double) totalRooms) * 100.0;
    }

    public ReportData generateFullReport(Date start, Date end) {
        double income = getTotalIncome(start, end);
        int bookingCount = getBookingCount(start, end);
        int guestCount = getGuestCount(start, end);
        Map<String, Integer> roomTypeStats = getRoomTypeReport();
        double occupancyRate = getOccupancyRate(start, end);

        return new ReportData(income, bookingCount, guestCount, roomTypeStats, occupancyRate);
    }
    
    public Map<String, Integer> getDailyBookings() {
        return bookingDAO.GET_DAILY_BOOKING_VOLUME();
    }
    
    public Map<String, Double> getMonthlyIncomeTrend() {
    return bookingDAO.GET_MONTHLY_INCOME_TREND();
}

    public Map<String, Integer> getRoomOccupancyReport() {
    
    int total = roomDAO.GET_TOTAL_ROOMS();
    int occupied = roomDAO.GET_OCCUPIED_ROOMS();
    int available = total - occupied;

    Map<String,Integer> data = new LinkedHashMap<>();
    data.put("Occupied", occupied);
    data.put("Available", available);
    data.put("Total Rooms", total);  

    return data;
}

    
}
