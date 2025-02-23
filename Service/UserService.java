package carrental.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import carrental.demo.Repository.UserRepo;
import carrental.demo.model.User;



@Service
public class UserService {

    @Autowired
    UserRepo ur;

    public List<User> createUser(List<User>u) {
        return ur.saveAll(u);
    }

    public List<User> getAllUsers() {
        return ur.findAll();
    }

    public User getUserById(int id) {
        return ur.findById(id).orElse(null);
    }

    public List<User> updateUsers(List<User> updatedUsers) {
        List<User> result = new ArrayList<>();
        for (User user : updatedUsers) {
            User existingUser = ur.findById(user.getId()).orElse(null);
            if (existingUser != null) {
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                existingUser.setActive(user.isActive());
    
                ur.save(existingUser);
                result.add(existingUser);
            }
        }
        return result; 
    }
    
    public List<User> deleteUsers(List<Integer> userIds) {
        ur.deleteAllById(userIds);
        return ur.findAll(); // Return remaining users
    }
    
    public List<User> page(int pageSize,int pageNumber)
    {
        
        Pageable page=PageRequest.of(pageNumber, pageSize);
        return ur.findAll(page).getContent();
        
    }
    public List<User>sort(String field)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,field);
        return ur.findAll(sort);
    }
    public List<User>pagesort(int pageSize,int pageNumber,String field)
    {
        return ur.findAll(
            PageRequest.of(pageNumber, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))
        ).getContent();
    }
   
    public User addUser(User user) {
        return ur.save(user); 
    }
    public Optional<User> getUserByEmail(String email) {
        return ur.findUserByEmail(email);
    }
} 
