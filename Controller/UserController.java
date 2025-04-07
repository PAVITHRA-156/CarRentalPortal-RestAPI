package carrental.demo.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import carrental.demo.Repository.UserRepo;
import carrental.demo.Service.UserService;
import carrental.demo.model.User;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService us;
    @Autowired
    UserRepo ur;

    @PostMapping("/post")
public ResponseEntity<List<User>> createUser(@RequestBody List<User> users) {
    return new ResponseEntity<>(us.createUser(users), HttpStatus.CREATED);
}


    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/update")
public ResponseEntity<List<User>> updateUsers(@RequestBody List<User> updatedUsers) {
    List<User> updatedUserList = us.updateUsers(updatedUsers);
    if (updatedUserList != null && !updatedUserList.isEmpty()) {
        return new ResponseEntity<>(updatedUserList, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

@DeleteMapping("/delete/{ids}")
public ResponseEntity<List<User>> deleteUsers(@PathVariable List<Integer> ids) {
    List<User> remainingUsers = us.deleteUsers(ids);

    return remainingUsers.isEmpty()
        ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList())
        : ResponseEntity.ok(remainingUsers);
}


    
  @GetMapping("/{offset}/{pagesize}")
    public List<User>getUsers(@PathVariable int offset,@PathVariable int pagesize)
    {
        return us.page(pagesize,offset);
    }
    @GetMapping("/sortBy/{field}")
   public List<User>sortUsers(@PathVariable String field)
   {
    return us.sort(field);
   }
   @GetMapping("/{offset}/{pagesize}/{field}")
   public List<User>getUsersSorted(@PathVariable int offset,@PathVariable int pagesize,@PathVariable String field)
   {
    return us.pagesort(pagesize,offset,field);
   }
   @GetMapping("/findByEmail")
   public Optional<User> getUserByEmail(@RequestParam String email) {
       return us.getUserByEmail(email);
   }
   @PostMapping("/adduser")
public ResponseEntity<User> addUser(@RequestBody User u) {
    User savedUser = us.addUser(u); 
    return ResponseEntity.ok(savedUser); 
}

}
