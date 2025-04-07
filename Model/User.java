package carrental.demo.model;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class User {

     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int id;
    String name;
    String email;
    @Transient
    String password;

   
    boolean active;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "user-booking")
    List<Booking>bookings=new ArrayList<>();

    public List<Booking>getBookings()
   {
       return bookings;
   }
   public void setBookings(List<Booking>bookings)
   {
    this.bookings.clear();
      for(Booking add:bookings)

      {
         add.setUser(this);
      }
     this.bookings.addAll(bookings);
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
