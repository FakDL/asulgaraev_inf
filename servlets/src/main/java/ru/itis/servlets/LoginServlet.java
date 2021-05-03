package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.UUID;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "20123124";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookieExample")) {
                    resp.sendRedirect("/users");
                }
            }
        } else req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        login(req, resp);
    }

    private boolean login(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(connection);
        User user = null;
        if (usersRepositoryJdbc.findByUsername(username) != null) {
            user = usersRepositoryJdbc.findByUsername(username).get(0);
            if (user.getPassword().equals(password)) {
                if(user.getUuid() == null) {
                    usersRepositoryJdbc.setUuidByUsername(UUID.randomUUID().toString(), username);
                }
                resp.addCookie(new Cookie("cookieExample", user.getUuid()));
                resp.sendRedirect("/users");
                return true;
            }
        }
         return false;
    }
}
