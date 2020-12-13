package ru.itis.services;

import ru.itis.models.Department;
import ru.itis.repositories.DepartmentRepository;
import ru.itis.repositories.DoctorsRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class DoctorsServiceImpl implements DoctorsService {

    private DoctorsRepository doctorsRepository;
    private  DepartmentRepository departmentRepository;

    public DoctorsServiceImpl(DoctorsRepository doctorsRepository, DepartmentRepository departmentRepository) {
        this.doctorsRepository = doctorsRepository;
        this.departmentRepository = departmentRepository;
    }

    //deprecated
    public void sendDocList(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        Long depId = 0L;
        for (Cookie cookie : cookies) {
            if (cookie.getName() == "depId") {
               depId =  Long.parseLong(cookie.getValue());
            }
        }

        Department department = departmentRepository.findById(depId).isPresent()?
                (Department) departmentRepository.findById(depId).get() :
                Department.builder().name("Доктора поликлиники").build();
        req.setAttribute("department", department);
        req.setAttribute("doctors", doctorsRepository.findByDepId(depId));
    }

    @Override
    public List getAllByDepId(Long depId) {
        return doctorsRepository.findByDepId(depId);
    }

}
