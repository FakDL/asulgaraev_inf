package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.Department;
import ru.itis.springbootdemo.repositories.DepartmentsRepository;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.List;

@Component
public class DepartmentsServiceImpl implements DepartmentsService {

    @Autowired
    private DepartmentsRepository departmentsRepository;



    @Override
    public List<Department> getAllDepartments() {
        return departmentsRepository.findAll();
    }

    @Override
    public Department getById(Long departmentId) {
        return departmentsRepository.findById(departmentId).orElse(Department.builder().build());
    }
}
