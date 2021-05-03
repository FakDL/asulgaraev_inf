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
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dep_id", referencedColumnName = "id")
    private Department department;
    @OneToMany(mappedBy = "service")
    Set<Appointment> appointments;
}
