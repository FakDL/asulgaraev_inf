package ru.itis.repositories;

import java.util.List;

public interface DoctorsRepository extends CrudRepository {
    List findByDepId(Long depId);
}
