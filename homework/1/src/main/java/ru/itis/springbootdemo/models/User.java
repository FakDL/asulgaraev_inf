package ru.itis.springbootdemo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String lastName;
    private String firstName;
    private String phone;

    @OneToMany(mappedBy = "customer")
    Set<Appointment> appointments;

    @Enumerated(value = EnumType.STRING)
    private State state;

    private String confirmCode;

    private Role role;
}
