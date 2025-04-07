package carrental.demo.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import carrental.demo.Service.BookingService;
import carrental.demo.model.Booking;


@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    BookingService bs;

    @PostMapping("/add")
    public ResponseEntity<List<Booking>> createBooking(@RequestBody List<Booking> booking) {
        return new ResponseEntity<>(bs.createBooking(booking), HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        Booking book = bs.getBookingById(id);
        return book != null ? new ResponseEntity<>(book, HttpStatus.OK) 
        
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return new ResponseEntity<>(bs.getAllBookings(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int id, @RequestBody Booking updatedBooking) {
        Booking booking = bs.updateBooking(id, updatedBooking);
        return booking != null ? new ResponseEntity<>(booking, HttpStatus.OK)
                               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<List<Booking>> updateBooking(@RequestBody List<Booking> book) {
        List<Booking> updated = bs.updateBooking(book);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

       @DeleteMapping("/delete/{ids}")
public ResponseEntity<List<Booking>> deleteBookings(@PathVariable List<Integer> ids) {
    List<Booking> remaining = bs.deleteBookings(ids);

    return remaining.isEmpty()
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
        : ResponseEntity.ok(remaining);
}

    @GetMapping("/page/{offset}/{pagesize}")
    public List<Booking> getBookings(@PathVariable int offset, @PathVariable int pagesize) {
        return bs.page(offset, pagesize);
    }

    @GetMapping("/sortBy/{field}")
    public List<Booking> sortBookList(@PathVariable String field) {
        return bs.sort(field);
    }

    @GetMapping("/page/{offset}/{pagesize}/sort/{field}")
    public List<Booking> getBookingSorted(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return bs.pagesort(offset, pagesize, field);
    }
    @PostMapping("/addbooking")
    public ResponseEntity<Booking>addBooking(@RequestBody Booking b)

    {
      Booking book=bs.addBooking(b);
      return ResponseEntity.ok(book);
    }
}
