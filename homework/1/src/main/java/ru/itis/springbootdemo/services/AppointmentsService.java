package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.models.Appointment;
import ru.itis.springbootdemo.models.Doctor;
import ru.itis.springbootdemo.models.Service;

import java.util.List;

public interface AppointmentsService {
    void createAppointment(Appointment appointment);
    List<Integer> getBlockedHours(String date, Long docId);
    List<Doctor> getAllDoctors();
    List<Service> getAllServices();
}
