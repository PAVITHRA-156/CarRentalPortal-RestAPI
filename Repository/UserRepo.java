  package carrental.demo.Repository;

  import java.util.List;
  import java.util.Optional;

  import org.springframework.data.jpa.repository.JpaRepository;
 
  import org.springframework.data.jpa.repository.Query;
  import org.springframework.data.repository.query.Param;
  import org.springframework.stereotype.Repository;

  import carrental.demo.model.User;
  

  @Repository
  public interface UserRepo extends JpaRepository<User,Integer>{

      @Query("SELECT u FROM User u")
      public List<User> getAllUsers();

      @Query("SELECT u FROM User u WHERE u.id = :id")
      public User getUserByEmail(@Param("email") String email);
      
      @Query("SELECT u FROM User u WHERE u.email = :email")
      Optional<User> findUserByEmail(@Param("email") String email);

      
  }
