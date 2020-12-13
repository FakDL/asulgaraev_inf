package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ServicesRepositoryJdbcTemplateImpl implements ServicesRepository {
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from services";
    private static final String SQL_SELECT_BY_ID = "select  * from services where id = ?";

    private JdbcTemplate jdbcTemplate;

    public ServicesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Service> servicesRowMapper = (row, rowNumber) -> Service.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .price(row.getInt("price"))
            .department_id(row.getInt("dep_id"))
            .build();


    @Override
    public void save(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, servicesRowMapper, id));
    }

    @Override
    public List findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, servicesRowMapper);
    }
}
