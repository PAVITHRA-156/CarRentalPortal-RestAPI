package carrental.demo.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import carrental.demo.Service.InsuranceService;
import carrental.demo.model.Insurance;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService is;

   
      @PostMapping("/post")
public ResponseEntity<List<Insurance>> createInsurance(@RequestBody List<Insurance> ins) {
    return new ResponseEntity<>(is.createInsurance(ins), HttpStatus.CREATED);
}

    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurance() {
        return new ResponseEntity<>(is.getAllInsurance(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Insurance> getInsuranceById(@PathVariable int id) {
        Insurance insurance = is.getInsuranceById(id);
        return insurance != null ? new ResponseEntity<>(insurance, HttpStatus.OK)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable int id, @RequestBody Insurance insurance) {
        Insurance updatedInsurance = is.updateInsurance(id, insurance);
        return updatedInsurance != null ? new ResponseEntity<>(updatedInsurance, HttpStatus.OK)
           : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

   
        @DeleteMapping("/delete/{ids}")
public ResponseEntity<List<Insurance>> deleteInsurances(@PathVariable List<Integer> ids) {
    List<Insurance> remaining = is.deleteInsurances(ids);

    return remaining.isEmpty()
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
        : ResponseEntity.ok(remaining);
}
    
    @GetMapping("/{offset}/{pagesize}")
    public List<Insurance>page(@PathVariable int offset,@PathVariable int pagesize)
    {
        return is.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<Insurance>sort(@PathVariable String field)
   {
    return is.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<Insurance>pageSort(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return is.pageSort(pagesize,offset,field);
   }
   @PostMapping("/addInsurance")
   public ResponseEntity<Insurance>addInsurance(@RequestBody Insurance i)
   {
      Insurance in=is.addInsurance(i);
      return ResponseEntity.ok(in);
   }
}
