package springBoot.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springBoot.demo.Model.Movies;
import springBoot.demo.Repository.MoviesRepo;

import java.util.List;

@RestController

public class MoviesController {

    @Autowired
    MoviesRepo moviesRepo;



    @GetMapping("/allMovies")
    public List<Movies> getAllMovies(){

        try {
            return moviesRepo.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


}
