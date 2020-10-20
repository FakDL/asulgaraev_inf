package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

    private List<User> users;
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "20123124";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.users = new ArrayList<>();
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(connection);
        users = usersRepositoryJdbc.findAll();

        request.setAttribute("username", getUsernameByUuid(request, connection));
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("WEB-INF/jsp/users.jsp").forward(request, response);
    }


    private String getUsernameByUuid(HttpServletRequest request,
                                     Connection connection) throws SQLException {
        Cookie[] cookies = request.getCookies();
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(connection);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookieExample")) {
                    return usersRepositoryJdbc.findByUuid(cookie.getValue()).get(0).getUsername();
                }
            }
        }
        return "unknown";
    }
}
