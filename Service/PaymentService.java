package carrental.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import carrental.demo.Repository.PaymentRepo;
import carrental.demo.model.Payment;
// import jakarta.transaction.Transactional;



@Service
public class PaymentService {

    @Autowired
    private PaymentRepo pr;
//    @Transactional
    public List<Payment> createPayments(List<Payment> payments) {
        return pr.saveAll(payments); 
    }
    

    public List<Payment> getAllPayments() {
        return pr.findAll();
    }

    public Page<Payment> getPaymentsWithPaginationAndSorting(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return pr.findAll(pageable);
    }

    public Payment getPaymentById(int id) {
        return pr.findById(id).orElse(null);
    }

    public Payment updatePayment(int id, Payment payment) {
        Payment existing = pr.findById(id).orElse(null);
        if (existing != null) {
            existing.setAmount(payment.getAmount());
            existing.setPaymentDate(payment.getPaymentDate());
            existing.setPaymentMethod(payment.getPaymentMethod());
            existing.setStatus(payment.getStatus());
            return pr.save(existing);
        }
        return null;
    }

     public List<Payment> deletePayment(List<Integer> Ids) {
        pr.deleteAllById(Ids);
        return pr.findAll() ;
    }
     
    public List<Payment> page(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return pr.findAll(page).getContent();
    }

   
    public List<Payment> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return pr.findAll(sort);
    }

    
    public List<Payment> pageSort(int pageSize, int pageNumber, String field) {
        return pr.findAll(
                PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field))
        ).getContent();
    }
    public Payment addPayment(Payment p)
    {
        return pr.save(p);
    }
}
