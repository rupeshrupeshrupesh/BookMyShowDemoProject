package springBoot.demo.ServiceLayer;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import springBoot.demo.Model.Admin;
import springBoot.demo.Model.LoginUserDTO;
import springBoot.demo.Model.Movies;
import springBoot.demo.Model.Role;
import springBoot.demo.Repository.AdminRepo;
import springBoot.demo.Repository.MoviesRepo;

import java.util.Optional;

@Service
public class AdminService {


    @Autowired
    AdminRepo adminRepo;


    @Autowired
    MoviesRepo moviesRepo;


    public String adminLogin(@RequestBody LoginUserDTO loginData) {

        boolean isPresent = adminRepo.existsByEmail(loginData.getEmail());
        Admin admin = adminRepo.findByEmail(loginData.getEmail());

        try {
            if (isPresent && loginData.getPwd().equals(admin.getPwd()) && admin.getRole() == Role.ADMIN) {
                return "success";

            } else {
                return "Failed";
            }
        } catch (Exception e) {
            return e.getMessage();

        }

    }


    public String adminRegister(@RequestBody @Valid Admin user) {

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

    public String DeleteMovies(int moviesID){

        Optional<Movies> movies=moviesRepo.findById(moviesID);

        if(!movies.isPresent()) throw new DataIntegrityViolationException("movie is not present");

         moviesRepo.delete(movies.get());
         return "ok";
    }

}
