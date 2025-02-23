package carrental.demo.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import carrental.demo.Service.GpsService;
import carrental.demo.model.Gps;


@RestController
@RequestMapping("/gps")
public class GpsController {

    @Autowired
    private GpsService gpsService;

    @PostMapping("/add")
    public ResponseEntity<List<Gps>> createGpsDevices(@RequestBody List<Gps> gpsDevices) {
        return new ResponseEntity<>(gpsService.createGpsDevices(gpsDevices), HttpStatus.CREATED);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Gps> getGpsById(@PathVariable int id) {
        Gps gps = gpsService.getGpsById(id);
        return gps != null ? new ResponseEntity<>(gps, HttpStatus.OK) 
         : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<List<Gps>> getAllGpsDevices() {
        return new ResponseEntity<>(gpsService.getAllGpsDevices(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<List<Gps>> updateGpsDevices(@RequestBody List<Gps> gpsDevices) {
        return new ResponseEntity<>(gpsService.updateGpsDevices(gpsDevices), HttpStatus.OK);
    }

       @DeleteMapping("/delete/{ids}")
public ResponseEntity<List<Gps>> deleteGps(@PathVariable List<Integer> ids) {
    List<Gps> remaining = gpsService.deleteGps(ids);

    return remaining.isEmpty()
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
        : ResponseEntity.ok(remaining);
}
    

    @GetMapping("/{offset}/{pagesize}")
    public List<Gps> getPaginatedGps(@PathVariable int offset, @PathVariable int pagesize) {
        return gpsService.paginateGps(pagesize, offset);
    }

    @GetMapping("/sortBy/{field}")
    public List<Gps> sortGps(@PathVariable String field) {
        return gpsService.sortGps(field);
    }

    @GetMapping("/{offset}/{pagesize}/{field}")
    public List<Gps> getPaginatedSortedGps(@PathVariable int offset, @PathVariable int pagesize, @PathVariable String field) {
        return gpsService.paginateAndSortGps(pagesize, offset, field);
    }
    @PostMapping("/addGps")
    public ResponseEntity<Gps>addGps(@RequestBody Gps gps)
    {
        Gps gp=gpsService.addGps(gps);
        return ResponseEntity.ok(gp);
    }
}
