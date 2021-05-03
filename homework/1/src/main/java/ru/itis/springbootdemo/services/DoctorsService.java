package ru.itis.springbootdemo.services;

import ru.itis.springbootdemo.models.Department;
import ru.itis.springbootdemo.models.Doctor;

import java.util.List;

public interface DoctorsService {
    List<Doctor> findAll();
    List<Doctor> findByDep(Long departmentId);
}
