package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import ru.itis.models.Department;
import ru.itis.services.ProfileService;
import ru.itis.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private ProfileService profileService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.profileService = (ProfileService) context.getAttribute("profileService");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        JSONObject obj = new JSONObject(req.getReader().readLine());
        Long id = obj.getLong("appId");
        profileService.deleteById(id);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        profileService.setData(req);
        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }
}
