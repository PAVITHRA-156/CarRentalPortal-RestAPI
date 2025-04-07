package carrental.demo.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import carrental.demo.Repository.VehicleRepo;
// import carrental.demo.model.RentalCompany;
import carrental.demo.model.Vehicle;

@Service
public class VehicleService {

    @Autowired
    VehicleRepo vr;
  
    
    public List<Vehicle> createVehicles(List<Vehicle> vehicles) {
        return vr.saveAll(vehicles); 
    }
  
    
    
    public List<Vehicle>getAllVehicles()
    {
        return vr.findAll();
    }
    
    
    public Vehicle getVehicleById(int id) {
        return vr.findById(id).orElse(null);  
    }
   public Vehicle updateVehicle(int id, String model, String regno,double ratePerDay,boolean available) {
        Vehicle existing = vr.findById(id).orElse(null);
        if (existing != null) {
            
            existing.setModel(model);
            existing.setRegno(regno);
            existing.setRatePerDay(ratePerDay);
            existing.setAvailable(available);
            return vr.save(existing);  
        } else {
            return null;  
        }
    }
    public Vehicle updateVehicleById(int id, Vehicle updatedVehicle) {
        Optional<Vehicle> existingVehicle = vr.findById(id);
        if (existingVehicle.isPresent()) {
            Vehicle vehicle = existingVehicle.get();
            vehicle.setModel(updatedVehicle.getModel());
            vehicle.setRegno(updatedVehicle.getRegno());
            vehicle.setRatePerDay(updatedVehicle.getRatePerDay());
            vehicle.setAvailable(updatedVehicle.isAvailable());
            return vr.save(vehicle);
        }
        return null;
    }
    
       public List<Vehicle> updateVehicles(List<Vehicle> vehicles) {
        List<Vehicle> updatedVehicles = vehicles.stream()
                .map(v -> updateVehicle(v.getId(), v.getModel(), v.getRegno(), v.getRatePerDay(), v.isAvailable()))
                .filter(v -> v != null)
                .collect(Collectors.toList());
        return updatedVehicles;
    }
   public List<Vehicle> deleteVehicles(List<Integer> userIds) {
        vr.deleteAllById(userIds);
        return vr.findAll(); 
    }
    public List<Vehicle> page(int pageSize,int pageNumber)
    {
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return vr.findAll(page).getContent();
    }
    public List<Vehicle>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return vr.findAll(sort);
    }
    public List<Vehicle>pagesort(int pageSize,int pageNumber,String field)
    {
        return vr.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
   
    public Vehicle addVehicle(Vehicle vehicle) {
        return vr.save(vehicle);
    }
    
}
