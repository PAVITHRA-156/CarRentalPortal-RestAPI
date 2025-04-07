package carrental.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import carrental.demo.Repository.BookingRepo;
import carrental.demo.model.Booking;




@Service
public class BookingService {

    @Autowired
    BookingRepo br;

    
    public List<Booking> createBooking(List<Booking> book) {
        return br.saveAll(book); 
    }

  
    public List<Booking> getAllBookings() {
        return br.findAll();
    }

    // Get booking by ID
    // public Optional<Booking> getBookingById(int id) {
    //     return br.findById(id);
    // }
    public Booking getBookingById(int id) {
        return br.findById(id).orElse(null);  
    }

   
    public Booking updateBooking(int id, Booking updatedBooking) {
        Optional<Booking> existingBooking = br.findById(id);
        if (existingBooking.isPresent()) {
            Booking bookingToUpdate = existingBooking.get();
            bookingToUpdate.setUser(updatedBooking.getUser());
            bookingToUpdate.setVehicle(updatedBooking.getVehicle());
            bookingToUpdate.setStarDate(updatedBooking.getStarDate());
            bookingToUpdate.setEndDate(updatedBooking.getEndDate());
            bookingToUpdate.setInsurance(updatedBooking.isInsurance());
            bookingToUpdate.setTotalAmount(updatedBooking.getTotalAmount());
            return br.save(bookingToUpdate);
        } else {
            
            return null;
        }
    }
       public List<Booking> updateBooking(List<Booking> updatedBooking) {
        List<Booking> result = new ArrayList<>();
        for (Booking b : updatedBooking) {
            Booking existing = br.findById(b.getId()).orElse(null);
            if (existing != null) {
                
                existing.setUser(b.getUser());
                existing.setVehicle(b.getVehicle());
                existing.setStarDate(b.getStarDate());
                existing.setEndDate(b.getEndDate());
                existing.setInsurance(b.isInsurance());
                existing.setTotalAmount(b.getTotalAmount());
    
                br.save(existing);
                result.add(existing);
            }
        }
        return result; 
    }
    public List<Booking> deleteBookings(List<Integer> Ids) {
        br.deleteAllById(Ids);
        return br.findAll() ;
    }
    public List<Booking> page(int pageSize,int pageNumber)
    {
        
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return br.findAll(page).getContent();
        
    }
    public List<Booking>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return br.findAll(sort);
    }

    public List<Booking> pagesort(int offset, int pagesize, String field) {
        Page<Booking> pagedResult = br.findAll(PageRequest.of(offset, pagesize, Sort.by(field)));
        return pagedResult.getContent();
    }
    public Booking addBooking(Booking book)
    {
        return br.save(book);
    }
}
