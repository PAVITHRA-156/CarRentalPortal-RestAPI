package carrental.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="bookings")
@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    // String user;
    // String vehicle;
    LocalDate starDate;
    LocalDate endDate;
    boolean insurance;
    double totalAmount;
   
    @ManyToMany(mappedBy="bookings",cascade = {CascadeType.PERSIST,CascadeType.MERGE})

    private List<RentalCompany>rentalcompany=new ArrayList<>();
    public List<RentalCompany> getRentalCompany()
    {
        return rentalcompany;
    }
    public void setRentalCompany(List<RentalCompany>rentalcompany)
    {
    
        this.rentalcompany=rentalcompany;
    }
      @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicle_id",referencedColumnName = "id")
    @JsonBackReference
    private Vehicle vehicle;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @JsonBackReference(value = "user-booking")
    // @JsonIgnore 
    private User user;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
  
    public LocalDate getStarDate() {
        return starDate;
    }
    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public boolean isInsurance() {
        return insurance;
    }
    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public void setVehicle(Vehicle vehicle)
   {
    
    this.vehicle=vehicle;
   }
   public Vehicle getVehicle()
   {
    return vehicle;
   }
    public User getUser()
    {
        return user;
        
    }
    public void setUser(User user)
    {
        this.user=user;
        // if(user!=null)
        // {
        //     user.getBookings().add(this);
        // }
    }
   
   
        public Booking() {
        }
    
    
}
