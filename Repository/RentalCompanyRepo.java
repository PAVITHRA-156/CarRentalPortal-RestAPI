package carrental.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import carrental.demo.model.RentalCompany;
import jakarta.transaction.Transactional;

public interface RentalCompanyRepo extends JpaRepository<RentalCompany, Integer> {


    @Query("SELECT r FROM RentalCompany r WHERE r.id = :companyId")
    RentalCompany getRentalCompanyById(@Param("companyId") int companyId);


    @Query("SELECT r FROM RentalCompany r")
    List<RentalCompany> getAllRentalCompanies();


    @Modifying
    @Transactional
    @Query(value = "INSERT INTO rental_company (id, name, location, vehicle) VALUES (:id, :name, :location, :vehicle)", nativeQuery = true)
    void insertRentalCompanyNative(
        @Param("id") int id, 
        @Param("name") String name, 
        @Param("location") String location, 
        @Param("vehicle") String vehicle
    );
    
}
