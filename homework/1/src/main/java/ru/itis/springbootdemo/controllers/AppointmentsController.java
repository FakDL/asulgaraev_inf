package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.springbootdemo.services.AppointmentsService;

@Controller
public class AppointmentsController {

    @Autowired
    AppointmentsService appointmentsService;


    @GetMapping("/appointments")
    public String getUsersPage(Model model) {
        model.addAttribute("doctors", appointmentsService.getAllDoctors());
        model.addAttribute("services", appointmentsService.getAllServices());
        return "appointments_page";
    }
}
