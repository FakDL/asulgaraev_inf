package ru.itis;


import lombok.*;

@Setter
@Getter
@Builder
public class User {
    Integer id;
    String username;
    String email;
}
