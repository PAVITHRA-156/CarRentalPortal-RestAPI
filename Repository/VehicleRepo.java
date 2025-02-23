package carrental.demo.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import carrental.demo.model.Vehicle;
import jakarta.transaction.Transactional;
@Repository
public interface VehicleRepo extends JpaRepository <Vehicle,Integer>{

    @Modifying
    
    @Query("SELECT v FROM Vehicle v") 
    public List<Vehicle>getAllVehicles();

    @Query("SELECT v FROM Vehicle v ORDER BY v.ratePerDay DESC")
List<Vehicle> findVehiclesSortedByRate();
    
@Query("SELECT COUNT(v) FROM Vehicle v")
long countTotalVehicles();
  
@Query("SELECT v From Vehicle v Where id=:id")
public Vehicle getVehicleById(int id);
  
                 


    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO vehicle (model, regno, rate_per_day, available) VALUES (:model, :regno, :ratePerDay, :available)", nativeQuery = true)
    void insertVehicle(@Param("model") String model, 
      @Param("regno") String regno, 
      @Param("ratePerDay") double ratePerDay, 
     @Param("available") boolean available);
}






