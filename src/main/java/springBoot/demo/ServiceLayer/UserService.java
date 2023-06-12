package springBoot.demo.ServiceLayer;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springBoot.demo.Model.LoginUserDTO;
import springBoot.demo.Model.Movies;
import springBoot.demo.Model.User;
import springBoot.demo.Repository.MoviesRepo;
import springBoot.demo.Repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MoviesRepo moviesRepo;

    public int userLogin(@RequestBody LoginUserDTO loginUser) {


        boolean isPresent = userRepo.existsByEmail(loginUser.getEmail());
        User user = userRepo.findByEmail(loginUser.getEmail());


        try {
            if (isPresent && user.getPwd().equals(loginUser.getPwd())) {
                return 1;
            } else {
                return 2;
            }

        } catch (
                Exception e) {

            e.printStackTrace();
            return 5;
        }

    }

    public String userRegister(@RequestBody @Valid User user) {

        boolean b = userRepo.existsByEmail(user.getEmail());

        try {
            if (!b) {
                return userRepo.save(user).toString();
            } else {
                return "already exits";
            }


        } catch (Exception e) {
            System.out.println("Ex: " + e.getMessage());
            return "null";
        }


    }

   public String bookingByUser(int userId,int moviesId){

        User user =userRepo.findById(userId).get();

        Optional<Movies> movies=moviesRepo.findById(moviesId);


        if(user.getMovies().contains(movies.get())) return  "movie alreay book";

        if(!movies.isPresent()) throw new DataIntegrityViolationException("Movie is not present with this Id ");

        Movies movies1 = movies.get();
        List<Movies> moviesList = user.getMovies();
        moviesList.add(movies1);
        user.setMovies(moviesList);
       return userRepo.save(user).toString();

   }
   public List<Movies> getAllMovieByUser(int userId)
   {
       Optional<User> user=userRepo.findById(userId);
       if(!user.isPresent()) throw new DataIntegrityViolationException("User is not found ");
       return user.get().getMovies();


   }


}
