package model;

public class Room {
    
    private int Rid;
    private String number;
    private String type;
    private double price;
    private String status;
    
    public Room(){
        System.out.println("Default constructor !!");
    }
    
    public Room(int id, String num , String ty , double price , String st){
        this.Rid = id;
        this.number = num;
        if(ty.equals("Single")){
           this.type = ty;
        }else if(ty.equals("Double")){
            this.type = ty;
        }else if(ty.equals("Suite")){
            this.type = ty;
        }else{
            System.out.println("This Type is Not found");
        }
        this.price = price;
        this.status = st;
    }
    
    //Business  logic
//    public boolean isRoomAvailable(){
//        if(status.equals("Available")){
//            return true;
//        }else{
//            return false;
//        }  
//    }
    public void markBooked(){
        status = "Booked";
    }
    public void markAvailable(){
        status = "Available";
    }
    
    
    public int getID(){
        return Rid;
    }
    public String getNumber(){
        return number;
    }
    
    public String getType(){
        return type;
    }
    public double getPrice(){
        return price;
    }
    public String getStatus(){
        return status;
    }
    
    
    public void setID(int id){
        this.Rid = id;
    }
    public void setNumber(String num ){
        this.number = num;
    }
    public void setType(String ty){
        this.type = ty;
    }
    public void setPrice(double p){
        this.price = p;
    }
    public void setStatus(String s){
        this.status = s;
    }
    
}


