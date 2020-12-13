package ru.itis.repositories;

import ru.itis.models.Appointment;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment> {
    List<Appointment> getAppointmentsByDoctor(Long docId);
    List<Appointment> getAppointmentsByCustomer(Long customerId);
}
