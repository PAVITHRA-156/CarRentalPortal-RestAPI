package carrental.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import carrental.demo.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {
    @Query("SELECT p FROM Payment p")
    List<Payment> getAllPayments();

     @Query("SELECT p FROM Payment p WHERE p.id = :id")
    Payment getPaymentByIdJPQL(@Param("id") int id);

        @Modifying
    @Query("INSERT INTO Payment (amount, paymentDate, paymentMethod, status) VALUES (:amount, :paymentDate, :paymentMethod, :status)")
    void  addPayment(@Param("amount") double amount, 
  @Param("paymentDate") String paymentDate, 
    @Param("paymentMethod") String paymentMethod, 
    @Param("status") String status);

}
