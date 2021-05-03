package ru.itis.springbootdemo.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.itis.springbootdemo.models.Department;

public interface DepartmentsRepository extends CrudRepository<Department, Long> {
    @Query("select department from Department department where " +
            "(:q = 'empty' or UPPER(department.name) like UPPER(concat('%', :q, '%'))) ")
    Page<Department> search(@Param("q") String q, Pageable pageable);
}
