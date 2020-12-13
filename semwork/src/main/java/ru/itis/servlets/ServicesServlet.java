package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.services.DepartmentService;
import ru.itis.services.ServicesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/services")
public class ServicesServlet extends HttpServlet {

    private DepartmentService departmentService;
    private ServicesService servicesService;


    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.departmentService = (DepartmentService) context.getAttribute("departmentService");
        this.servicesService = (ServicesService) context.getAttribute("servicesService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        departmentService.sendDepList(req);
        servicesService.sendServList(req);
        req.getRequestDispatcher("/WEB-INF/jsp/services.jsp").forward(req, resp);
    }
}
