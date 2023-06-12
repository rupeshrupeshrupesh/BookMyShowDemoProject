package springBoot.demo.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springBoot.demo.DTO.EmailMailDTO;
import springBoot.demo.Model.LoginUserDTO;
import springBoot.demo.Model.Movies;
import springBoot.demo.Model.User;
import springBoot.demo.Repository.UserRepo;
import springBoot.demo.ServiceLayer.EmailService;
import springBoot.demo.ServiceLayer.UserService;

import java.util.List;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    UserService userService;

    @Autowired
    private EmailService emailService;


    @PostMapping("/usersLogin")
    public int userLogin(@RequestBody LoginUserDTO loginUser) {

        return userService.userLogin(loginUser);


    }

    @PostMapping("/userRegister")
    public String userRegister(@RequestBody @Valid User user) {


        return userService.userRegister(user);


    }



    @PostMapping("/sendotp")
    public String sendOtpEmail(@RequestBody EmailMailDTO recipientEmail) {
        return emailService.sendOtpEmail(recipientEmail.getEmail());
    }


    @PutMapping("/updatePassWord")
    public String updateandResetPassWord(@RequestBody User user)
    {
        return emailService.updateAndResetPassword(user);

    }


    @GetMapping("/booking")
    public String booking(@RequestParam int userid,@RequestParam int movieId){
        return userService.bookingByUser(userid,movieId);
    }


    @GetMapping("/getAllMovieByUser")

    public List<Movies> getAllMovieByUser(@RequestParam int userId){
        return userService.getAllMovieByUser(userId);
    }

}
