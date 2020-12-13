package ru.itis.services;

import ru.itis.repositories.DepartmentRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    @Override
    public void sendDepList(HttpServletRequest req) {
        req.setAttribute("departments", departmentRepository.findAll());
    }

}
