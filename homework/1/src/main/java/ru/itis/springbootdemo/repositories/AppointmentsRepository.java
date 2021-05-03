package ru.itis.springbootdemo.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.springbootdemo.models.Appointment;
import ru.itis.springbootdemo.models.Doctor;
import ru.itis.springbootdemo.models.User;

import java.util.List;

public interface AppointmentsRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findByCustomer(User customer);
    List<Appointment> findByDoctorId(Long docId);
}
