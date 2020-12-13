package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import ru.itis.dto.UserDto;
import ru.itis.models.Appointment;
import ru.itis.repositories.DoctorsRepository;
import ru.itis.repositories.ServicesRepository;
import ru.itis.services.AppointmentService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet("/appointment")
public class AppointmentServlet extends HttpServlet {

    private AppointmentService appointmentService;
    private DoctorsRepository doctorsRepository;
    private ServicesRepository servicesRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.appointmentService = (AppointmentService) context.getAttribute("appointmentService");
        this.servicesRepository = (ServicesRepository) context.getAttribute("servicesRepository");
        this.doctorsRepository = (DoctorsRepository) context.getAttribute("doctorsRepository");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("form")) {
            String docId = req.getParameter("docid");
            HttpSession session = req.getSession();
            Appointment appointment = Appointment.builder().
                    customer_id(((UserDto) session.getAttribute("user")).getId()).
                    service_id(Long.parseLong(req.getParameter("service"))).
                    doctor_id(Long.parseLong(docId)).
                    date(req.getParameter("calendar") + " " + req.getParameter("hour")).build();
            appointmentService.createAppointment(appointment);
            resp.sendRedirect("/appointment");
        } else {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String blockedHours;

            JSONObject obj = new JSONObject(req.getReader().readLine());
            String date = obj.getString("date");
            Long docId = obj.getLong("docId");
            blockedHours = objectMapper.writeValueAsString(appointmentService.getBlockedHours(date, docId));
            resp.setContentType("application/json");
            resp.getWriter().println(blockedHours);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("doctors", doctorsRepository.findAll());
        req.setAttribute("services", servicesRepository.findAll());
        req.getRequestDispatcher("/WEB-INF/jsp/appointment.jsp").forward(req,resp);
    }
}
