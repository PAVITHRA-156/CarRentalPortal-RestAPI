package carrental.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import carrental.demo.model.Insurance;

public interface InsuranceRepo extends JpaRepository<Insurance, Integer> {

     @Query("SELECT i FROM Insurance i WHERE i.id = :id")
     public Insurance getInsuranceById(@Param("id") int id);
   
    @Query("SELECT i FROM Insurance i")
    List<Insurance> getALLInsurances();

     @Modifying
    @Query("INSERT INTO Insurance (provider, policyNumber, coverageAmount, expiryDate) VALUES (:provider, :policyNumber, :coverageAmount, :expiryDate)")
    void addInsurance(@Param("provider") String provider, 
       @Param("policyNumber") String policyNumber, 
      @Param("coverageAmount") double coverageAmount,
       @Param("expiryDate") String expiryDate);

}
