package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itis.springbootdemo.dto.DepartmentsPage;
import ru.itis.springbootdemo.models.Department;
import ru.itis.springbootdemo.services.DepartmentsService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentsController {

    @Autowired
    DepartmentsService departmentsService;

    @GetMapping("/departments")
    public String getDepartmentsPage(Model model) {
        model.addAttribute("departmentList", departmentsService.getAllDepartments());
        return "departments_page";
    }

    @GetMapping("/departments/{department-id}")
    public String getUser(@PathVariable("department-id") Long departmentId, Model model) {
        List<Department> list = new ArrayList<>();
        list.add(departmentsService.getById(departmentId));
        model.addAttribute("departmentList", list);
        return "departments_page";
    }

    @GetMapping("/departments/search")
    public String search(@RequestParam("size") Integer size,
                         @RequestParam("page") Integer page,
                         @RequestParam(value = "q", required = false) String query,
                         @RequestParam(value = "sort", required = false) String sort,
                         @RequestParam(value = "direction", required = false) String direction,
                         Model model) {

        model.addAttribute("departmentList", departmentsService.search(size, page, query, sort, direction)
                .getDepartments());
        return "departments_page";
    }

    @CrossOrigin("http://localhost:8070")
    @GetMapping("/departments-js")
    public ResponseEntity<List<Department>> getDepartments() {
        return ResponseEntity.ok(departmentsService.getAll());
    }


}

