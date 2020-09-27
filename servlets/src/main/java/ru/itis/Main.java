package ru.itis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "20123124";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        String sqlInsertUser = "insert into users( email, username, password) " +
                "values ('qwewqe', 'asdasd@df.er', 'afadasdsda')";

        PreparedStatement preparedStatement = preparedStatement = connection.prepareStatement(sqlInsertUser);

        preparedStatement.executeUpdate();
    }
}
