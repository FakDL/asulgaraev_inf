package ru.itis.springbootdemo.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer workExp;
    private String lastName;
    private String firstName;
    @OneToMany(mappedBy = "doctor")
    Set<Appointment> appointments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dep_id", referencedColumnName = "id")
    private Department department;
}
