package carrental.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "vehicle")
@Entity

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
       
    private int id;
    String model;
    String regno;
    double ratePerDay;
    boolean available;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="gps_id",referencedColumnName = "id")
    @JsonManagedReference
    private Gps gps;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rentalcompany_id",referencedColumnName = "id")
    @JsonBackReference

    
    private RentalCompany rentalCompany;
    
    @OneToMany(mappedBy = "vehicle",cascade = CascadeType.ALL)
    @JsonManagedReference
   List<Booking>bookings=new ArrayList<>(); 
   public List<Booking>getBookings()
   {
       return bookings;
   }
//    public void setBooking(List<Booking>bookings)
//    {
//     this.bookings.clear();
//       for(Booking add:bookings)

//       {
//          add.setVehicle(this);
//       }
//      this.bookings.addAll(bookings);
//    }
public void setBookings(List<Booking> bookings) {
    this.bookings.clear();
    for (Booking booking : bookings) {
        booking.setVehicle(this);
    }
    this.bookings.addAll(bookings);
}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getRegno() {
        return regno;
    }
    public void setRegno(String regno) {
        this.regno = regno;
    }
    public double getRatePerDay() {
        return ratePerDay;
    }
    public void setRatePerDay(double ratePerDay) {
        this.ratePerDay = ratePerDay;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    public Gps getGps()
   {
     return gps;
   }   
//    public void  setGps(Gps gps)
//    {
//      if(gps!=null){
//     gps.setVehicle(this);
//      }
//     this.gps=gps;
//    }
public void setGps(Gps gps) {
    if (this.gps != null) {
        this.gps.setVehicle(null);
    }
    if (gps != null) {
        gps.setVehicle(this);
    }
    this.gps = gps;
}
   public void setRentalCompany(RentalCompany rentalCompany)
   {
    
    this.rentalCompany=rentalCompany;
   }
   public RentalCompany getRentalCompany()
   {
    return rentalCompany;
   }
  public Vehicle()
  {

  }
}
