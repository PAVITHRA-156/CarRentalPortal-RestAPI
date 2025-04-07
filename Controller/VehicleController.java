package carrental.demo.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import carrental.demo.Repository.VehicleRepo;
import carrental.demo.Service.VehicleService;

// import carrental.demo.model.RentalCompany;
import carrental.demo.model.Vehicle;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    VehicleService vs;
    @Autowired
    VehicleRepo vr;

    
    @PostMapping("/add")
    public ResponseEntity<List<Vehicle>> createVehicle(@RequestBody List<Vehicle> vehicles) {
        return new ResponseEntity<>(vs.createVehicles(vehicles), HttpStatus.CREATED);
    }
    

    
    @GetMapping("/get/{id}")
public ResponseEntity<Vehicle> getVehicleById(@PathVariable int id) {
    Vehicle vehicle = vs.getVehicleById(id);
    return vehicle != null ? new ResponseEntity<>(vehicle, HttpStatus.OK) 
                           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
}


    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return new ResponseEntity<>(vs.getAllVehicles(), HttpStatus.OK);
    }
    


 @PutMapping("/update")
public ResponseEntity<List<Vehicle>> updateVehicles(@RequestBody List<Vehicle> vehicles) {
    List<Vehicle> updatedVehicleList = vs.updateVehicles(vehicles);
    if (updatedVehicleList != null && !updatedVehicleList.isEmpty()) {
        return new ResponseEntity<>(updatedVehicleList, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
@PutMapping("/update/{id}")
public ResponseEntity<Vehicle> updateVehicleById(@PathVariable int id, @RequestBody Vehicle updatedVehicle) {
    Vehicle vehicle = vs.updateVehicleById(id, updatedVehicle);
    return vehicle != null ? new ResponseEntity<>(vehicle, HttpStatus.OK)
                           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
}


@DeleteMapping("/delete/{ids}")
public ResponseEntity<List<Vehicle>> deleteVehicles(@PathVariable List<Integer> ids) {
    List<Vehicle> remaining = vs.deleteVehicles(ids);

    return remaining.isEmpty()
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
        : ResponseEntity.ok(remaining);
}

    @GetMapping("/{offset}/{pagesize}")
    public List<Vehicle>getVehicles(@PathVariable int offset,@PathVariable int pagesize)
    {
        return vs.page(pagesize,offset);
    }
   @GetMapping("/sortBy/{field}")
   public List<Vehicle>sortVehicles(@PathVariable String field)
   {
    return vs.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Vehicle>getVehiclesSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return vs.pagesort(pagesize,offset,field);
   }
   @GetMapping("/totalcount")
   public long getTotalVehicleCount() {
       return vr.countTotalVehicles();
   }
   @GetMapping("/findbyrate")
   public ResponseEntity<List<Vehicle>> findVehiclesSortedByRate() {
    List<Vehicle>sort=vr.findVehiclesSortedByRate();
    return new ResponseEntity<>(sort,HttpStatus.OK);
}


@PostMapping("/addveh")
public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {
    Vehicle savedVehicle = vs.addVehicle(vehicle); 
    return ResponseEntity.ok(savedVehicle); 
}


}
