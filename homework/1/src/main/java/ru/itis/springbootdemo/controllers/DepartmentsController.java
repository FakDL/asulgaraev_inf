package ru.itis.springbootdemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.springbootdemo.models.Department;
import ru.itis.springbootdemo.services.DepartmentsService;

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
    @ResponseBody
    public ResponseEntity<Department> getUser(@PathVariable("department-id") Long departmentId) {
        return ResponseEntity.ok(departmentsService.getById(departmentId));
    }


}

