package carrental.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import carrental.demo.model.User;
import jakarta.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{

    @Query("SELECT u FROM User u")
    public List<User> getAllUsers();

    @Query("SELECT u FROM User u WHERE u.id = :id")
    public User getUserByEmail(@Param("email") String email);
    
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (name, email, password, active) VALUES (:name, :email, :password, :active)", nativeQuery = true)
    void insertUserNative(@Param("name")String name,
      @Param("email")String email,
      @Param("password")String password,
      @Param("active") boolean active);

    
}
