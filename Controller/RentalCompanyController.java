package carrental.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import carrental.demo.Service.RentalCompanyService;
import carrental.demo.model.RentalCompany;


import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/rentalCompanies")
public class RentalCompanyController {

    @Autowired
    RentalCompanyService rentalCompanyService;
   
  
      @PostMapping("/post")
public ResponseEntity<List<RentalCompany>> createRentalCompany(@RequestBody List<RentalCompany> rc) {
    return new ResponseEntity<>(rentalCompanyService.createRentalCompany(rc), HttpStatus.CREATED);
}
      @PostMapping("/onetomany")
      public ResponseEntity<RentalCompany> saveRentalCompany(@RequestBody RentalCompany rc) {
        return new ResponseEntity<>(rentalCompanyService.savRentalCompany(rc), HttpStatus.CREATED);
    }


    
  
    @GetMapping("/{id}")
    public ResponseEntity<RentalCompany> getRentalCompanyById(@PathVariable int id) {
        RentalCompany rentalCompany = rentalCompanyService.getRentalCompanyById(id);
        return rentalCompany != null ? new ResponseEntity<>(rentalCompany, HttpStatus.OK): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    
    @GetMapping
    public ResponseEntity<List<RentalCompany>> getAllRentalCompanies() {
        return new ResponseEntity<>(rentalCompanyService.getAllRentalCompanies(), HttpStatus.OK);
    }

    

    
    @PutMapping("/update")
public ResponseEntity<List<RentalCompany>> updateRentalCompanies(@RequestBody List<RentalCompany> rentalCompanies) {
    List<RentalCompany> updatedCompanies = rentalCompanyService.updateRentalCompanies(rentalCompanies);
    return updatedCompanies.isEmpty()
            ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(updatedCompanies, HttpStatus.OK);
}


    
 
    @DeleteMapping("/delete/{ids}")
public ResponseEntity<List<RentalCompany>> deleteRentalCompanies(@PathVariable List<Integer> ids) {
    List<RentalCompany> remaining = rentalCompanyService.deleteRentalCompanies(ids);

    return remaining.isEmpty()
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
        : ResponseEntity.ok(remaining);
}


    
    @GetMapping("/{offset}/{pageSize}")
    public List<RentalCompany> getRentalCompaniesPaged(@PathVariable int offset, @PathVariable int pageSize) {
        return rentalCompanyService.page(pageSize, offset);
    }

   
    @GetMapping("/sortBy/{field}")
    public List<RentalCompany> sortRentalCompanies(@PathVariable String field) {
        return rentalCompanyService.sort(field);
    }

    
    @GetMapping("/{offset}/{pageSize}/{field}")
    public List<RentalCompany> getRentalCompaniesPagedAndSorted(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        return rentalCompanyService.pageSort(pageSize, offset, field);
    }

  
    @GetMapping("/totalcount")
    public long getTotalRentalCompanyCount() {
        return rentalCompanyService.getAllRentalCompanies().size();
    }
     @PostMapping("/addcompany")
public ResponseEntity<RentalCompany> addCompany(@RequestBody RentalCompany u) {
    RentalCompany save = rentalCompanyService.addCompany(u); 
    return ResponseEntity.ok(save); 
}
}
