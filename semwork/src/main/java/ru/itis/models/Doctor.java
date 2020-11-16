package models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Doctor {
    Long id;
    Integer department_id;
    String firstName;
    String lastName;
    Integer workExp;
}
