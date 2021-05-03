package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.models.Department;
import ru.itis.springbootdemo.models.Doctor;
import ru.itis.springbootdemo.repositories.DoctorsRepository;

import java.util.List;

@Component
public class DoctorsServiceImpl implements DoctorsService {

    @Autowired
    DoctorsRepository doctorsRepository;

    @Override
    public List<Doctor> findAll() {
        return (List<Doctor>) doctorsRepository.findAll();
    }

    @Override
    public List<Doctor> findByDep(Long departmentId) {
        return doctorsRepository.findByDepartmentId(departmentId);
    }
}
