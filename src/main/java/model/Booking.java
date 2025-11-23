package model;
import java.sql.Date; 

public class Booking {
    
    private int Bid;
    private int guestID;
    private int roomID;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;
    private double totalAmmount;
    
    
    public Booking(){
        System.out.println("Default constructor !!");
    }
    
    public Booking(int id, int gid , int rid, Date cID, Date cOD, String status, double tot ){
        this.Bid = id;
        this.roomID = rid;
        this.guestID = gid;
        this.checkInDate = cID;
        this.checkOutDate = cOD;
        this.status = status;
        this.totalAmmount = tot;
    }
    
    public long getNight(){
        long returnMS = checkOutDate.getTime() - checkInDate.getTime();
        return (returnMS / (1000*60 * 60 * 24));
    }
    
    public boolean isCheckedIn(){
        if(status.equals("CheckedIn")){
            return true; 
        }else{
            return false;
        }
    }
    
    public void checkIn(){
        status = "CheckedIn";
    }
    
    public void CheckedOut(){
        status = "CheckedOut";
    }
    
    public int getID(){
        return Bid;
    }
    public int getGuestID(){
        return guestID;
    }
    public int getRoomID(){
        return roomID;
    }
    public Date getCheckOutDate(){
        return checkOutDate;
    }
    public Date getCheckInDate(){
        return  checkInDate;
    }
    public String getStatus(){
        return status;
    }
    public double getToatalAmmount(){
        return totalAmmount;
    }
    
    public void setID(int id){
        this.Bid = id;
    }
    public void setGuestID(int id){
        this.guestID = id;
    }
    public void setRoomID(int id){
        this.roomID = id;
    }
    public void setCheckOutDate(Date date){
        this.checkOutDate = date;
    }
    public void setCheckInDate(Date date){
        this.checkInDate = date;
    }
    public void setStatus(String sta){
        this.status = sta;
    }
    public void setToatalAmmount(double amm){
        this.totalAmmount = amm;
    }
    
    
}
