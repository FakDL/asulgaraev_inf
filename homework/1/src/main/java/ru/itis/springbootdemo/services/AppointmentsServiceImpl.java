package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.models.Appointment;
import ru.itis.springbootdemo.models.Doctor;
import ru.itis.springbootdemo.models.Service;
import ru.itis.springbootdemo.repositories.AppointmentsRepository;
import ru.itis.springbootdemo.repositories.DoctorsRepository;
import ru.itis.springbootdemo.repositories.ServicesRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentsServiceImpl implements AppointmentsService{

    @Autowired
    AppointmentsRepository appointmentsRepository;
    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    ServicesRepository servicesRepository;


    @Override
    public void createAppointment(Appointment appointment) {
        appointmentsRepository.save(appointment);
    }

    @Override
    public List<Integer> getBlockedHours(String date, Long docId) {
        List<Appointment> appointments = appointmentsRepository.findByDoctorId(docId);
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

    @Override
    public List<Doctor> getAllDoctors() {
        return (List<Doctor>) doctorsRepository.findAll();
    }

    @Override
    public List<Service> getAllServices() {
        return (List<Service>) servicesRepository.findAll();
    }
}
