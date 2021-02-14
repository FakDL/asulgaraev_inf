package ru.itis.springbootdemo.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.springbootdemo.dto.UserDto;
import ru.itis.springbootdemo.models.Department;

import java.util.List;

public interface DepartmentsRepository extends CrudRepository<Department, Long> {
    List<Department> findAll();
}
