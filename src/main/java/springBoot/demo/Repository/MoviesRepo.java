package springBoot.demo.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import springBoot.demo.Model.Movies;

public interface MoviesRepo extends JpaRepository<Movies, String> {

    Movies findByName(String name);
    boolean existsByName(String name);

}
