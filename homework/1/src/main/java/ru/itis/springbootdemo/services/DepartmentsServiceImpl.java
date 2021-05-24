package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.DepartmentDto;
import ru.itis.springbootdemo.dto.DepartmentsPage;
import ru.itis.springbootdemo.models.Department;
import ru.itis.springbootdemo.repositories.DepartmentsRepository;

import java.util.List;

@Component
public class DepartmentsServiceImpl implements DepartmentsService {

    @Autowired
    private DepartmentsRepository departmentsRepository;

    @Override
    public List<Department> getAllDepartments() {
        return (List<Department>) departmentsRepository.findAll();
    }

    @Override
    public Department getById(Long departmentId) {
        return departmentsRepository.findById(departmentId).orElse(Department.builder().build());
    }

    @Override
    public DepartmentsPage search(Integer size, Integer page, String query, String sortParameter, String directionParameter) {
        Sort.Direction direction = Sort.Direction.ASC;
        Sort sort = Sort.by(direction, "id");

        if (directionParameter != null) {
            direction = Sort.Direction.fromString(directionParameter);
        }

        if (sortParameter != null) {
            sort = Sort.by(direction, sortParameter);
        }

        if (query == null) {
            query = "empty";
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Department> departmentPage = departmentsRepository.search(query, pageRequest);

        return DepartmentsPage.builder()
                .pagesCount(departmentPage.getTotalPages())
                .departments(DepartmentDto.from(departmentPage.getContent()))
                .build();
    }

    @Override
    public List<Department> getAll() {
        return (List<Department>) departmentsRepository.findAll();
    }

}
