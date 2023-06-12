package springBoot.demo.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "movie_table")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movies_id")
    private  int id;

    private String name;

    private Date date;

    private String language;

    private int rating;

    private String city;

//    @ManyToMany(cascade = CascadeType.PERSIST)
//    @Column(name = "user")
//    private List<User> user;
}
