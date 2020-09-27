package ru.itis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/reg")
public class RegistrationServlet extends HttpServlet {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "20123124";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        System.out.println("AAAAAAAAAAAAAAA");

        try {
            insertToDB(request);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void insertToDB(HttpServletRequest request) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String sqlInsertUser = "insert into users( email, username, password) " +
                "values ( ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2,username);
        preparedStatement.setString(3, password);

        preparedStatement.executeUpdate();
    }
}
