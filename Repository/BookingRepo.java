package carrental.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import carrental.demo.model.Booking;
import jakarta.transaction.Transactional;
@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer>{

     @Query("SELECT b FROM Booking b")
    List<Booking> findAllBookings();

    @Query("SELECT b FROM Booking b WHERE b.id = :id")
    Optional<Booking> findBookingById(int id);

     @Modifying
    @Transactional
    @Query(value = "INSERT INTO booking (user, vehicle, start_date, end_date, insurance, total_amount) " +
   "VALUES (:user, :vehicle, :startDate, :endDate, :insurance, :totalAmount)", nativeQuery = true)
    void insertBookingNative(String user, String vehicle, String startDate, String endDate, boolean insurance, double totalAmount);

}
