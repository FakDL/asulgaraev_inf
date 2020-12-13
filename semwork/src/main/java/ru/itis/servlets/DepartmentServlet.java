package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.models.Department;
import ru.itis.services.DepartmentService;
import ru.itis.services.DoctorsService;
import ru.itis.services.ProfileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();
    private DepartmentService departmentService;
    private DoctorsService doctorsService;


    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.departmentService = (DepartmentService) context.getAttribute("departmentService");
        this.doctorsService = (DoctorsService) context.getAttribute("doctorsService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Department department = objectMapper.readValue(req.getReader(), Department.class);
        String doctorsAsJson = objectMapper
                .writeValueAsString(doctorsService.getAllByDepId(department.getId()));
        resp.setContentType("application/json");
        resp.getWriter().println(doctorsAsJson);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        departmentService.sendDepList(req);
        req.getRequestDispatcher("/WEB-INF/jsp/departments.jsp").forward(req, resp);
    }
}
