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
    @Column(length = 20)
    private String password_hash;
    @Column(length = 20)
    private String created_at;
    @Column(length = 20)
    private String updated_at;
}
