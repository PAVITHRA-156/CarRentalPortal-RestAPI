package carrental.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Gps {
    @Id


    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
    
     @Version // Enables Hibernate optimistic locking
    private Integer version;

    private String deviceId;
    private String status;
    private String location;
    private double latitude;
    private double longitude;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "vehicle_id" ,referencedColumnName = "id")
    @JsonBackReference
    private Vehicle vehicle;
    public Vehicle getVehicle()
    {
        return vehicle;
    }
    public void setVehicle(Vehicle vehicle)
    {
        this.vehicle=vehicle;
    }
}
