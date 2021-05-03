package ru.itis.springbootdemo.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.springbootdemo.models.Department;
import ru.itis.springbootdemo.models.Doctor;

import java.util.List;

public interface DoctorsRepository extends CrudRepository<Doctor, Long> {
    List<Doctor> findByDepartmentId(Long department_id);
}
