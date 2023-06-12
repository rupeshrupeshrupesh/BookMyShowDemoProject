package springBoot.demo.controller;

import com.sun.net.httpserver.Authenticator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springBoot.demo.Model.Admin;
import springBoot.demo.Model.LoginUserDTO;
import springBoot.demo.Model.Movies;
import springBoot.demo.Model.Role;
import springBoot.demo.Repository.AdminRepo;
import springBoot.demo.Repository.MoviesRepo;
import springBoot.demo.ServiceLayer.AdminService;
import springBoot.demo.ServiceLayer.MoviesService;

@RestController
public class AdminController {


    @Autowired
    AdminRepo adminRepo;


    @Autowired
    MoviesRepo moviesRepo;

    @Autowired
    AdminService adminService;

    @Autowired
    MoviesService moviesService;


    @PostMapping("/adminLogin")
    public String adminLogin(@RequestBody LoginUserDTO loginData) {



        return adminService.adminLogin(loginData);

    }


    @PostMapping("/adminRegister")
    public String AdminRegister(@RequestBody @Valid Admin user) {



        return adminService.adminRegister(user);


    }

    @PostMapping("/addMovies")
    public String addMovies(@RequestBody Movies movies){


        return adminService.addMovies(movies);

    }

    @DeleteMapping("/deleteMovie")
    public String DeleteMovies(@RequestParam int movisId){


        return adminService.DeleteMovies(movisId);
    }

}
