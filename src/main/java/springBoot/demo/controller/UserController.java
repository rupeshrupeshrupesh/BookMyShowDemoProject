package springBoot.demo.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springBoot.demo.Model.LoginUserDTO;
import springBoot.demo.Model.Role;
import springBoot.demo.Model.User;
import springBoot.demo.Repository.UserRepo;


import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/usersLogin")
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

    @PostMapping("/userRegister")
    public String Admin(@RequestBody @Valid User user) {

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

}
