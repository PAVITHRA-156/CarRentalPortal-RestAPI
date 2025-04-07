package carrental.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="rentalcompany")
public class RentalCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String name;
    String location;
    
    @ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(
        name="rentalcompany_booking",
        joinColumns = @JoinColumn(name="rentalcompany_id"),
        inverseJoinColumns =@JoinColumn(name="booking_id")
    )

    @JsonIgnore
    private List<Booking>bookings=new ArrayList<>();

    public List<Booking>getBooking()
    {
        return bookings;
    }
    public void setBooking(List<Booking>bookings)

    {
       
        this.bookings = bookings;
    }
    @OneToMany(mappedBy = "rentalCompany",cascade = CascadeType.ALL)
    List<Vehicle>vehicles=new ArrayList<>(); 
    @JsonManagedReference
    public List<Vehicle>getVehicles()
    {
        return vehicles;
    }
    public void setVehicles(List<Vehicle>ve)
    {
       for(Vehicle add:vehicles)

       {
          add.setRentalCompany(this);
       }
      this.vehicles.addAll(ve);
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
   
    public RentalCompany()
    {

    }
}
