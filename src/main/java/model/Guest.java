
package model;

public class Guest {
    
   private int Gid;
   private String name;
   private String phone;
   private String email;
   private String nicORPass;
   private String address;
    
    public Guest(){
        System.out.println("Default constructor !!");
    }
    
    public  Guest(int id, String name, String phon , String mail, String npid,String add){
        this.Gid = id;
        this.name = name;
        this.email = mail;
        this.phone = phon;
        this.nicORPass = npid;
        this.address = add;
    }
    
    public int getID(){
        return Gid;
    }
    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public String getEmail(){
        return email;
    }
    public String getNICORPassport(){
        return nicORPass;
    }
     public String getAddress(){
        return address;
    }
    
    
    
    public void setID(int id){
        this.Gid = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPhone(String pho){
        this.phone = pho;
    }
    public void setEmail(String mail){
        this.email = mail;
    }
    public void setNICORPassport(String npid){
        this.nicORPass = npid;
    }
    public void setAddress(String add){
        this.address = add;
    }
}
