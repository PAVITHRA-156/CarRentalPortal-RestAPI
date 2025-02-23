package carrental.demo.Service;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import carrental.demo.Repository.GpsRepo;
import carrental.demo.model.Gps;


@Service
public class GpsService {

    @Autowired
    private GpsRepo gpsRepo;

    public List<Gps> createGpsDevices(List<Gps> gpsDevices) {
        return gpsRepo.saveAll(gpsDevices);
    }

    public List<Gps> getAllGpsDevices() {
        return gpsRepo.findAll();
    }

    public Gps getGpsById(int id) {
        return gpsRepo.findById(id).orElse(null);
    }

   

    public List<Gps> updateGpsDevices(List<Gps> gpsDevices) {
        return gpsRepo.saveAll(gpsDevices);
    }

    public List<Gps> deleteGps(List<Integer> Ids) {
        gpsRepo.deleteAllById(Ids);
        return gpsRepo.findAll() ;
    }

    public List<Gps> paginateGps(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return gpsRepo.findAll(page).getContent();
    }

    public List<Gps> sortGps(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return gpsRepo.findAll(sort);
    }

    public List<Gps> paginateAndSortGps(int pageSize, int pageNumber, String field) {
        return gpsRepo.findAll(
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, field))
        ).getContent();
    }
    public Gps addGps(Gps g)
    {
        return gpsRepo.save(g);
    }
}
