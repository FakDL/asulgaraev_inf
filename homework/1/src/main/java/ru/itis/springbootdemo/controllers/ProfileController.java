package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.springbootdemo.models.Appointment;
import ru.itis.springbootdemo.models.HoursCriteria;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.AppointmentsRepository;
import ru.itis.springbootdemo.repositories.UsersRepository;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;

import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    AppointmentsRepository appointmentsRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<User> optionalUser = usersRepository.findByEmail(userDetails.getUsername());
        User user = optionalUser.orElseGet(User::new);
        model.addAttribute("user", user);
        model.addAttribute("appointments", appointmentsRepository.findAll());
        System.out.println(((List<Appointment>) appointmentsRepository.findAll()).size());
        return "profile_page";
    }

    @PostMapping("/profile/ajax")
    public Long deleteAppointment(Model model,
                                  @RequestBody Long appointmentId,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        for (int i = 0; i < 100; i++) {
            System.out.println(appointmentId);
        }
        Appointment appointment = appointmentsRepository.findById(appointmentId).get();
        appointmentsRepository.delete(appointment);
        model.addAttribute("appointments", appointmentsRepository.findAll());
        return userDetails.getUser().getId();
    }

}
