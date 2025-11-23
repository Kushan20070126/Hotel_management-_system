package model;

public class User {
    private int Uid;
    private String username;
    private String password; 
    private String role;
    
    public User(){
        System.out.println("Default constructor !!");
    }
    public User(int id, String u_Name,String pass, String role){
        this.Uid = id;
        this.password = pass;
        this.username= u_Name;
        this.role = role;
    }
    
    public int getID(){
        return Uid;           
    }
    public String getUserName(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getRole(){
        return role;
    }
    
    
    public void setID(int id){
        this.Uid = id;
    }
    public void setUserName(String u_name){
        this.username = u_name;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public void setRole(String r){
        this.role = r;
    }
    
    
}
