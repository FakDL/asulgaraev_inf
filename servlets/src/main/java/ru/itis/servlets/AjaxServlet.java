package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {

    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "20123124";
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    private Connection connection;
    private UsersRepositoryJdbcImpl usersRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void init() {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            usersRepository = new UsersRepositoryJdbcImpl(connection);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = objectMapper.readValue(req.getReader(), User.class);
        String users = objectMapper.writeValueAsString(usersRepository.findAllByUsernameStartingWith(user.getUsername()));
        resp.setContentType("application/json");
        resp.getWriter().println(users);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/html/ajax.html").forward(req, resp);
    }
}
