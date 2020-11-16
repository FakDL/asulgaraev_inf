package models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Appointment {
    Integer id;
    Integer customer_id;
    Integer doctor_id;
    Integer service_id;
    String time;
}
