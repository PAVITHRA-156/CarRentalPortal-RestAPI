package carrental.demo.model;





import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

   
    private int id;
    String model;
    String regno;
    double ratePerDay;
    boolean available;
    
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

}
