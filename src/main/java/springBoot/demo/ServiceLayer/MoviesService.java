package springBoot.demo.ServiceLayer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import springBoot.demo.Model.Movies;
import springBoot.demo.Repository.MoviesRepo;

import java.util.List;


@Service
public class MoviesService {


    @Autowired
    MoviesRepo moviesRepo;

    public List<Movies> getAllMovies() {

        try {
            return moviesRepo.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
