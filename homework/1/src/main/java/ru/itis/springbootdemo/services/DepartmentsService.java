package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.models.Department;

import java.util.List;

public interface DepartmentsService {
    List<Department> getAllDepartments();
    Department getById(Long departmentId);
}
