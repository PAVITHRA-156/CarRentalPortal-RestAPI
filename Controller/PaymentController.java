package carrental.demo.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import carrental.demo.Service.PaymentService;
import carrental.demo.model.Payment;






@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService ps;

    @PostMapping("/add")
    public ResponseEntity<List<Payment>> createPayments(@RequestBody List<Payment> payments) {
        return new ResponseEntity<>(ps.createPayments(payments), HttpStatus.CREATED);
    }
    
   
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return new ResponseEntity<>(ps.getAllPayments(), HttpStatus.OK);
    }
 
    @GetMapping("/page")
    public ResponseEntity<Page<Payment>> getPaymentsWithPaginationAndSorting(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy) {
        return new ResponseEntity<>(ps.getPaymentsWithPaginationAndSorting(page, size, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int id) {
        Payment payment = ps.getPaymentById(id);
        return payment != null ? new ResponseEntity<>(payment, HttpStatus.OK)
       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Payment> updatePayment(@PathVariable int id, @RequestBody Payment payment) {
        Payment updatedPayment = ps.updatePayment(id, payment);
        return updatedPayment != null ? new ResponseEntity<>(updatedPayment, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
        @DeleteMapping("/delete/{ids}")
public ResponseEntity<List<Payment>> deletePayment(@PathVariable List<Integer> ids) {
    List<Payment> remaining = ps.deletePayment(ids);

    return remaining.isEmpty()
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
        : ResponseEntity.ok(remaining);
}
    
    @GetMapping("/{offset}/{pagesize}")
    public List<Payment>page(@PathVariable int offset,@PathVariable int pagesize)
    {
        return ps.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Payment>sort(@PathVariable String field)
   {
    return ps.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Payment>pageSort(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return ps.pageSort(pagesize,offset,field);
   }
   @PostMapping("/addpayment")
   public ResponseEntity<Payment>addPayment(@RequestBody Payment p)
   {
    Payment pay=ps.addPayment(p);
    return ResponseEntity.ok(pay);
   }
   
}
