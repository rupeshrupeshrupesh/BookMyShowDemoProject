package springBoot.demo.ServiceLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import springBoot.demo.DTO.OtpDetails;
import springBoot.demo.Model.User;
import springBoot.demo.Repository.UserRepo;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    UserRepo userRepo;


    private OtpDetails generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);

        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(2); // Expiry time set to 5 minutes from now

        return new OtpDetails(String.valueOf(otp), expiryTime);
    }


    public String sendOtpEmail(String receipentEmail) {
        boolean b = userRepo.existsByEmail(receipentEmail);
//b=true;
        try {


            if (b) {


                OtpDetails otpDetails = generateOtp();
                String otp = otpDetails.getOtp();
                LocalDateTime expiryTime = otpDetails.getExpiryTime();

                // Construct the email message with OTP and expiry time
                String messageText = "Your One-Time Password (OTP) is: " + otp + "\n";
                messageText += "Please use this OTP within 2 minutes. After that, it will expire.";

                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(receipentEmail);
                message.setSubject("Your OTP");
                message.setText(messageText);

                javaMailSender.send(message);
                System.out.println("OTP email sent successfully to " + receipentEmail + "  " + otp);
                return "ok";

            } else {
                return "emial address wrong";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }

    public String updateAndResetPassword(User user) {

        User user1 = userRepo.findByEmail(user.getEmail());
        boolean b = userRepo.existsByEmail(user.getEmail());

        try {
            if (b) {
                user1.setPwd(user.getPwd());
                userRepo.save(user1);
                return "ok";
            } else {
                return "not present";
            }


        } catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    }


}
