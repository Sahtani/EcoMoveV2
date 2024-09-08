package Models.Entities;

import java.util.UUID;

public class Client {

    private UUID id;
    private String firstName ;
    private String lastName ;
    private String email ;
    private int phoneNumber ;

    //constructor :
    public Client(UUID id, String firstName, String lastName, String email, int phoneNumber){
        this.id = id ;
        this.firstName = firstName ;
        this.lastName = lastName ;
        this.email = email ;
        this.phoneNumber = phoneNumber ;

    }

    // getters and setters :

    public UUID getId(){
        return id;
    }

    public void setId(UUID id){
        this.id = id ;

    }

    public String getFirstName(){
        return firstName ;
    }
    public void setFirstName(String firstName){this.firstName = firstName ;}

    public String getLastName(){
        return lastName ;
    }

    public String getEmail(){ return email ;}

    public void setEmail(String email)
    {
        this.email=email ;
    }
    public int getPhoneNumber(){
        return  phoneNumber ;
    }

    public void setPhoneNumber(int phoneNumber){this.phoneNumber = phoneNumber ;}
}
