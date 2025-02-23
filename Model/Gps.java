package carrental.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Gps {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

    private String deviceId;
    private String status;
    private String location;
    private double latitude;
    private double longitude;

}
