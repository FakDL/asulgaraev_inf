package ru.itis.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Service {
    Long id;
    String name;
    Integer price;
    Integer department_id;
}