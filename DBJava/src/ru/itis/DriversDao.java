package ru.itis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DriversDao {

    private Connection connection;

    public DriversDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<Driver> findById(Long id) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet drivers = statement.executeQuery(String.format("select * from driver where id = %d", id));
        ResultSet cars = statement.executeQuery(String.format("select * from car where driver_id = %d", id));
        List<Car> carList = new ArrayList<>();
        while(cars.next()) {
            carList.add(new Car(cars.getLong("id"), cars.getString("color"),
                    cars.getString("model")));
        }
        Driver driver = new Driver(
            drivers.getLong("id"), drivers.getString("firstName"),
                drivers.getString("lastName"), drivers.getInt("age"), carList);

        return Optional.of(driver);
    }
}

