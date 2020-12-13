package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Doctor;

import javax.print.Doc;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class DoctorsRepositoryJdbcTemplateImpl implements DoctorsRepository {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from doctors";

    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from doctors where id = ?";

    //language=SQL
    private static final String SQL_FIND_BY_DEP_ID = "select * from doctors where dep_id = ?";

    public DoctorsRepositoryJdbcTemplateImpl(DataSource dataSource)  {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<Doctor> doctorRowMapper = (row, rowNumber) -> Doctor.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .workExp(row.getInt("work_exp"))
            .department_id(row.getInt("dep_id"))
            .build();

    @Override
    public List<Doctor> findByDepId(Long depId) {
        return  jdbcTemplate.query(SQL_FIND_BY_DEP_ID, doctorRowMapper, depId);
    }

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
    public Optional<Doctor> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, doctorRowMapper, id));
    }

    @Override
    public List findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, doctorRowMapper);
    }
}
