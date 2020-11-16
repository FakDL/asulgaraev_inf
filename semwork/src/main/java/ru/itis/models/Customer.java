package models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Customer {
    Integer id;
    String email;
    String firstName;
    String lastName;
    String hashPassword;
    Integer age;
}
