package carrental.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import carrental.demo.Repository.RentalCompanyRepo;

import carrental.demo.model.RentalCompany;
import carrental.demo.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentalCompanyService {

    @Autowired
    RentalCompanyRepo rentalCompanyRepo;
    

   
   public List<RentalCompany> createRentalCompany(List<RentalCompany>u) {
        return rentalCompanyRepo.saveAll(u);
    }
   
    public RentalCompany savRentalCompany(RentalCompany rentalCompany)
    { 
        if (rentalCompany.getVehicles() != null){
        for(Vehicle vehicle:rentalCompany.getVehicles())
        {
            vehicle.setRentalCompany(rentalCompany);
        }
    }
        return rentalCompanyRepo.save(rentalCompany);
    }
    
    public List<RentalCompany> getAllRentalCompanies() {
        return rentalCompanyRepo.findAll();
    }

  
    public RentalCompany getRentalCompanyById(int id) {
        return rentalCompanyRepo.findById(id).orElse(null);
    }

    
      public List<RentalCompany> updateRentalCompanies(List<RentalCompany> rentalCompanies) {
        List<RentalCompany> updatedCompanies = new ArrayList<>();

        for (RentalCompany rentalCompany : rentalCompanies) {
            rentalCompanyRepo.findById(rentalCompany.getId()).ifPresent(existingCompany -> {
                existingCompany.setName(rentalCompany.getName());
                existingCompany.setLocation(rentalCompany.getLocation());
                // existingCompany.setVehicle(rentalCompany.getVehicle());
                updatedCompanies.add(rentalCompanyRepo.save(existingCompany));
            });
        }
        return updatedCompanies;
    }

   
      public List<RentalCompany> deleteRentalCompanies(List<Integer> Ids) {
        rentalCompanyRepo.deleteAllById(Ids);
        return rentalCompanyRepo.findAll() ;
    }
    
    
    public List<RentalCompany> page(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return rentalCompanyRepo.findAll(page).getContent();
    }

   
    public List<RentalCompany> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return rentalCompanyRepo.findAll(sort);
    }

    public List<RentalCompany> pageSort(int pageSize, int pageNumber, String field) {
        return rentalCompanyRepo.findAll(
                PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field))
        ).getContent();
    }
    public RentalCompany addCompany(RentalCompany rc)
    {
        return rentalCompanyRepo.save(rc);
    }
     

}
