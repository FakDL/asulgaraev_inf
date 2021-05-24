package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.dto.DepartmentsPage;
import ru.itis.springbootdemo.models.Department;

import java.util.List;

public interface DepartmentsService {
    List<Department> getAllDepartments();
    Department getById(Long departmentId);

    DepartmentsPage search(Integer size, Integer page, String query, String sort, String direction);

    List<Department> getAll();
}
