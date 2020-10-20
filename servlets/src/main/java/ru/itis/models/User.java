package ru.itis.models;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Integer age;
    private String uuid;
}
