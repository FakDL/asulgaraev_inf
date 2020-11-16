package ru.itis.services;

import ru.itis.repositories.DepartmentRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class DoctorsServiceJdbcTemplateImpl implements DoctorsService {

    public DoctorsServiceImpl(DoctorsRepository doctorsRepository) {
        this.doctorstRepository = departmentRepository;
    }

    public void sendDocList(HttpServletRequest req) {
        req.setAttribute("doctors", doctorsRepository.findByDepId(req.getParameter("dep")));
    }

}
