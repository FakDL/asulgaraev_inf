package ru.itis.services;

import ru.itis.repositories.ServicesRepository;
import ru.itis.repositories.ServicesRepositoryJdbcTemplateImpl;

import javax.servlet.http.HttpServletRequest;

public class ServicesServiceImpl implements ServicesService {

    private ServicesRepository servicesRepository;

    public ServicesServiceImpl(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }


    @Override
    public void sendServList(HttpServletRequest req) {
        req.setAttribute("services", servicesRepository.findAll());
    }
}
