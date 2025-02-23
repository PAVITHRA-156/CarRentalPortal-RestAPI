package carrental.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import carrental.demo.model.Gps;
import jakarta.transaction.Transactional;

public interface GpsRepo extends JpaRepository<Gps,Integer>{

     @Query("SELECT g FROM Gps g")
     public List<Gps> getAllGpsDevices();

    @Query("SELECT g FROM Gps g WHERE g.deviceId = :deviceId")
    public Gps getGpsById(@Param("deviceId") String deviceId);

    @Modifying
    @Transactional
    @Query("INSERT INTO Gps (deviceId, status, location, latitude, longitude) " +
           "VALUES (:deviceId, :status, :location, :latitude, :longitude)")
    void insertGps(
        @Param("deviceId") String deviceId, 
        @Param("status") String status, 
        @Param("location") String location, 
        @Param("latitude") double latitude, 
        @Param("longitude") double longitude
    );
}
