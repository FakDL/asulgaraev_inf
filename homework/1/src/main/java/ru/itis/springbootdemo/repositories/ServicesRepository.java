package ru.itis.springbootdemo.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.itis.springbootdemo.models.Service;

public interface ServicesRepository extends CrudRepository<Service, Long> {
}
