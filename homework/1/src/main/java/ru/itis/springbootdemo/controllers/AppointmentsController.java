package ru.itis.springbootdemo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.*;
import ru.itis.springbootdemo.repositories.DoctorsRepository;
import ru.itis.springbootdemo.repositories.ServicesRepository;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.services.AppointmentsService;
import ru.itis.springbootdemo.services.DoctorsService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Controller
public class AppointmentsController {

    @Autowired
    AppointmentsService appointmentsService;
    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    ServicesRepository servicesRepository;

    @GetMapping("/appointments")
    public String getUsersPage(Model model) {
        model.addAttribute("doctors", appointmentsService.getAllDoctors());
        model.addAttribute("services", appointmentsService.getAllServices());
        return "appointments_page";
    }

    @PostMapping("/appointments/create")
    public ModelAndView createAppointment(
            @RequestParam(value = "docid") Long docId,
            @RequestParam(value ="service") Long serviceId,
            @RequestParam(value ="calendar") String calendar,
            @RequestParam(value ="hour") String hour,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        for (int i = 0; i < 100; i++){
            System.out.println("F");
        }
        Doctor doctor = doctorsRepository.findById( Objects.requireNonNull(docId))
                .get();
        Appointment appointment = Appointment.builder().
                customer(userDetails.getUser()).
                service(servicesRepository.findById(serviceId).get()).
                doctor(doctor).
                date(calendar + " " + hour).build();
        appointmentsService.createAppointment(appointment);
        return new ModelAndView("redirect:/appointments");
    }

    @PostMapping("/appointments/ajax")
    public ResponseEntity<?> getHoursResultViaAjax(
            @RequestBody HoursCriteria criteria) {

        System.out.println(criteria);
        List<Integer> blockedHours = appointmentsService.getBlockedHours(criteria.getDate(), criteria.getDocId());

        return ResponseEntity.ok(blockedHours);

    }
}
