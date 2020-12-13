package ru.itis.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Appointment {
    Long id;
    Long customer_id;
    Long doctor_id;
    Long service_id;
    String date;
}
