package gijin.weather_app.domain;

import jakarta.persistence.*;
import lombok.*;
@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor

public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 20)
    private String username;

    @Column(length = 20)
    private String email;
    @Column(length = 255)
    private String password;
    @Column(length = 255)
    private String created;
    @Column(length = 255)
    private String updated;
}
