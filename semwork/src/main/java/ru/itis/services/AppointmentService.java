package ru.itis.services;

import ru.itis.models.Appointment;

import java.util.List;

public interface AppointmentService {
    void createAppointment(Appointment appointment);
    List<Integer> getBlockedHours(String date, Long docId);
}
