package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.AppointmentsRepository;
import ru.itis.springbootdemo.repositories.DoctorsRepository;
import ru.itis.springbootdemo.repositories.ServicesRepository;
import ru.itis.springbootdemo.repositories.UsersRepository;
import ru.itis.springbootdemo.security.details.UserDetailsImpl;
import ru.itis.springbootdemo.services.FileStorageService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    AppointmentsRepository appointmentsRepository;

    @Autowired
    DoctorsRepository doctorsRepository;

    @Autowired
    ServicesRepository servicesRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/profile")
    public String getProfile(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<User> optionalUser = usersRepository.findByEmail(userDetails.getUsername());
        User user = optionalUser.orElseGet(User::new);
        model.addAttribute("user", user);
        model.addAttribute("appointments", appointmentsRepository.findAll());
        model.addAttribute("doctors", doctorsRepository.findAll());
        model.addAttribute("services", servicesRepository.findAll());
        return "profile_page";
    }

}
