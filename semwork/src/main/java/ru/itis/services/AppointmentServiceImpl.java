package ru.itis.services;

import ru.itis.models.Appointment;
import ru.itis.repositories.AppointmentRepository;
import ru.itis.repositories.DoctorsRepository;
import ru.itis.repositories.ServicesRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
    private DoctorsRepository doctorsRepository;
    private ServicesRepository servicesRepository;
    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(
            DoctorsRepository doctorsRepository,
            ServicesRepository servicesRepository,
            AppointmentRepository appointmentRepository){

        this.appointmentRepository = appointmentRepository;
        this.doctorsRepository = doctorsRepository;
        this.servicesRepository = servicesRepository;

    }
    public void createAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    @Override
    public List<Integer> getBlockedHours(String date, Long docId) {
        List<Appointment> appointments = appointmentRepository.getAppointmentsByDoctor(docId);
        List<Integer> blockedHours = new ArrayList<>();
        for (Appointment appointment: appointments) {
            String docDate = appointment.getDate();
            if(docDate.contains(date)) {
                blockedHours.add(Integer.parseInt(docDate.charAt(docDate.length()-2)+""+docDate.charAt(docDate.length()-1)));
            }
            System.out.println(docDate.contains(date) + " docDate = "  + docDate + " date = " + date);
        }
        return blockedHours;
    }

}
