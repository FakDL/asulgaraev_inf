package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.Appointment;
import ru.itis.models.Doctor;
import ru.itis.models.Service;
import ru.itis.repositories.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ProfileServiceImpl implements ProfileService {

    private AppointmentRepository appointmentRepository;
    private DoctorsRepository doctorsRepository;
    private ServicesRepository servicesRepository;

    public ProfileServiceImpl( AppointmentRepository appointmentRepository, DoctorsRepository doctorsRepository,
                               ServicesRepository servicesRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorsRepository = doctorsRepository;
        this.servicesRepository = servicesRepository;
    }

    @Override
    public void setData(HttpServletRequest request) {
        UserDto user;
        HttpSession session = request.getSession();
        user = (UserDto) session.getAttribute("user");
        ArrayList<Appointment> appointments = (ArrayList<Appointment>)
                appointmentRepository.getAppointmentsByCustomer(user.getId());
        request.setAttribute("appointments", appointments);
//        for (Appointment appointment: appointments) {
//            Doctor doctor = (Doctor) doctorsRepository.findById(appointments.get(0).getDoctor_id()).get();
//            request.setAttribute("docName" + appointment.getDoctor_id(),
//                    doctor.getLastName() + ' ' + doctor.getFirstName());
//            Service service = (Service) servicesRepository.findById(appointment.getService_id()).get();
//            request.setAttribute("servName" + appointment.getDoctor_id(),
//                    service.getName() + " " + service.getPrice() + " рублей");
//
//        }
        request.setAttribute("services", servicesRepository.findAll());
        request.setAttribute("doctors", doctorsRepository.findAll());
        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("lastName", user.getLastName());
        request.setAttribute("email", user.getEmail());
    }

    public void deleteById(Long id) {
        appointmentRepository.delete(id);
    }
}
