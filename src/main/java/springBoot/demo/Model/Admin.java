package springBoot.demo.Model;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Admins")
public class Admin {
    @Id
    @Column(name="admin_id")
    @GeneratedValue(strategy= GenerationType.AUTO)

    private int id;

    private String name;
    @Email(message = "Invalid email format")
    private String email;

    private String mobile;

    private String pwd;

    private Role role;


}
