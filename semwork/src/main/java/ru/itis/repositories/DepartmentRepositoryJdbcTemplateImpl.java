package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Department;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class DepartmentRepositoryJdbcTemplateImpl implements DepartmentRepository {
    private static final String SQL_FIND_BY_ID = "select * from department where id = ?";
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT_ALL = "select * from department";

    public DepartmentRepositoryJdbcTemplateImpl(DataSource dataSource)  {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Department> departmentRowMapper = (row, rowNumber) -> Department.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
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
        return Optional.of(jdbcTemplate.query(SQL_FIND_BY_ID, departmentRowMapper, id).get(0));
    }

    @Override
    public List findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, departmentRowMapper);
    }
}
