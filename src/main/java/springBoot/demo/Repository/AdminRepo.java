package springBoot.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springBoot.demo.Model.Admin;
import springBoot.demo.Model.User;

public interface AdminRepo extends JpaRepository<Admin,Integer> {
    boolean existsByEmail(String email);
    Admin findByEmail(String email);


}
