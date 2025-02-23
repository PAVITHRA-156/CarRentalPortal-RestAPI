package carrental.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import carrental.demo.Repository.InsuranceRepo;
import carrental.demo.model.Insurance;




@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepo ir;

    // public Insurance addInsurance(Insurance insurance) {
    //     return ir.save(insurance);
    // }
    public List<Insurance> createInsurance(List<Insurance>u) {
        return ir.saveAll(u);
    }

    public List<Insurance> getAllInsurance() {
        return ir.findAll();
    }

    public Insurance getInsuranceById(int id) {
        return ir.findById(id).orElse(null);
    }

    public Insurance updateInsurance(int id, Insurance insurance) {
        Insurance existing = ir.findById(id).orElse(null);
        if (existing != null) {
            existing.setProvider(insurance.getProvider());
            existing.setPolicyNumber(insurance.getPolicyNumber());
            existing.setCoverageAmount(insurance.getCoverageAmount());
            existing.setExpiryDate(insurance.getExpiryDate());
            return ir.save(existing);
        }
        return null;
    }

   public List<Insurance> deleteInsurances(List<Integer> Ids) {
        ir.deleteAllById(Ids);
        return ir.findAll() ;
    }
    
    public List<Insurance> page(int pageSize, int pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return ir.findAll(page).getContent();
    }

 
    public List<Insurance> sort(String field) {
        Sort sort = Sort.by(Sort.Direction.ASC, field);
        return ir.findAll(sort);
    }

   
    public List<Insurance> pageSort(int pageSize, int pageNumber, String field) {
        return ir.findAll(
                PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC, field))
        ).getContent();
    }
    public Insurance addInsurance(Insurance in)
    {
        return ir.save(in);
    }
}
