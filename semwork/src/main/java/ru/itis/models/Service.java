package models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Service {
    Integer id;
    String disease;
    Integer price;
    Integer department_id;
}