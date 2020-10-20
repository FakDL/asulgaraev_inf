package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "select * from users";
    //language=SQL
    private static final String SQL_FIND_USER_BY_USERNAME = "select * from users where username = ?";
    //language=SQL
    private static final String SQL_FIND_USER_BY_UUID = "select * from users where uuid = ?";
    //language=SQL
    private static final String SQL_SET_UUID_BY_USERNAME = "UPDATE users SET uuid = ? WHERE username = ?";
    //language=SQL
    private static final String SQL_INSERT_USER = "insert into users( email, username, password) values ( ?, ?, ?)";
    //language=SQL
    private static final String SQL_FIND_ALL_USERS_BY_USERNAME = "select * from users where username like ?";


    private Connection connection;

    private SimpleJdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
        this.jdbcTemplate = new SimpleJdbcTemplate(connection);
    }

    private RowMapper<User> usersRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .username(row.getString("username"))
            .email(row.getString("email"))
            .password(row.getString("password")).
            uuid(row.getString("uuid"))
            .build();

    public List<User> findAllByUsernameStartingWith(String username) {
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS_BY_USERNAME, usersRowMapper, username + "%");
    }

    public void setUuidByUsername(String uuid, String username) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_SET_UUID_BY_USERNAME);
        statement.setString(1, uuid);
        statement.setString(2, username);
        statement.execute();
    }

    public void insertUserToDB(String username, String email, String password) throws SQLException {
        PreparedStatement statement =
                connection.prepareStatement(SQL_INSERT_USER);
        statement.setString(1, email);
        statement.setString(2, username);
        statement.setString(3, password);
        statement.executeQuery();
    }

    @Override
    public List<User> findByUsername(String username) {
        return jdbcTemplate.queryForList(SQL_FIND_USER_BY_USERNAME, usersRowMapper, username);
    }
    public List<User> findByUuid(String uuid) {
        return jdbcTemplate.queryForList(SQL_FIND_USER_BY_UUID, usersRowMapper, uuid);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.queryForList(SQL_FIND_ALL_USERS, usersRowMapper);
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }
}

