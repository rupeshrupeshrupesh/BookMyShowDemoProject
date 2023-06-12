package springBoot.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springBoot.demo.Model.Movies;
import springBoot.demo.Repository.MoviesRepo;
import springBoot.demo.ServiceLayer.MoviesService;

import java.util.List;

@RestController

public class MoviesController {

    @Autowired
    MoviesRepo moviesRepo;


    @Autowired
    MoviesService moviesService;


    @GetMapping("/allMovies")
    public List<Movies> getAllMovies(){



        return moviesService.getAllMovies();
    }


}
