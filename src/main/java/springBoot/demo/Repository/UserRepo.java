package springBoot.demo.Repository;
//
//import com.eazybyte.springsecuritybasic.Model.User;
//import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBoot.demo.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

//    List user findByEmail(email);

//    User findByEmail(String email);

    boolean existsByEmail(String email);
    User findByEmail(String email);

}

