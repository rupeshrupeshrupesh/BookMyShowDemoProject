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

@RestController
public class AdminController {


    @Autowired
    AdminRepo adminRepo;


    @Autowired
    MoviesRepo moviesRepo;


    @PostMapping("/adminLogin")
    public String adminLogin(@RequestBody LoginUserDTO loginData) {

        boolean isPresent = adminRepo.existsByEmail(loginData.getEmail());
        Admin admin = adminRepo.findByEmail(loginData.getEmail());

        try {


            if (isPresent && loginData.getPwd().equals(admin.getPwd()) && admin.getRole() == Role.ADMIN) {
                return "sucess";

            } else {
                return "Failed";
            }
        } catch (Exception e) {
            return e.getMessage();

        }


    }


    @PostMapping("/adminRegister")
    public String Admin(@RequestBody @Valid Admin user) {

        boolean isPresent = adminRepo.existsByEmail(user.getEmail());

        try {
            if (!isPresent) {
                return adminRepo.save(user).toString();
            } else {
                return "already exits";
            }


        } catch (Exception e) {
            System.out.println("Ex: " + e.getMessage());
            return "null";
        }


    }

    @PostMapping("/addMovies")
    public String addMovies(@RequestBody Movies movies){
      boolean ispresent= moviesRepo.existsByName(movies.getName());
      try {
          if(!ispresent)
          {
              moviesRepo.save(movies);
              return "ok";
          }
          else {
              return "already exits";

          }
      }
      catch (Exception e)
      {
          e.printStackTrace();
          return e.getMessage();
      }

    }

    @DeleteMapping("/delete{name}")
    public String DeleteMovies(@PathVariable String name){

        Movies movie=moviesRepo.findByName(name);
       boolean isPresent= moviesRepo.existsByName(name);
        try {
            if(isPresent)
            {
                moviesRepo.delete(movie);
                return "deleteSucceSSFully";
            }

            else {
                return "not Present";
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }



}
