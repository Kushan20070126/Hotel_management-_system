package model;

import java.util.Map;

public class ReportData {

    private double income;                     
    private int bookingCount;                  
    private int guestCount;                    
    private Map<String, Integer> roomTypeStats; 
    private double occupancyRate;              

    
    
    public ReportData() {}

    public ReportData(double income, int bookingCount, int guestCount,Map<String, Integer> roomTypeStats, double occupancyRate) {
        
        
        this.income = income;
        this.bookingCount = bookingCount;
        this.guestCount = guestCount;
        this.roomTypeStats = roomTypeStats;
        this.occupancyRate = occupancyRate;
    }

   

    public double getIncome() { 
        return income; 
    }
    public int getBookingCount() {
        return bookingCount; 
    }
    public int getGuestCount() {
        return guestCount; 
    }
    public Map<String, Integer> getRoomTypeStats() { 
        return roomTypeStats; 
    }
    public double getOccupancyRate() {
        return occupancyRate; 
    }

    

    public void setIncome(double income) {
        this.income = income; 
    }
    public void setBookingCount(int bookingCount) {
        this.bookingCount = bookingCount; 
    }
    public void setGuestCount(int guestCount) { 
        this.guestCount = guestCount; 
    }
    public void setRoomTypeStats(Map<String, Integer> roomTypeStats) { 
        this.roomTypeStats = roomTypeStats; 
    }
    public void setOccupancyRate(double occupancyRate) { 
        this.occupancyRate = occupancyRate;
    }

}
