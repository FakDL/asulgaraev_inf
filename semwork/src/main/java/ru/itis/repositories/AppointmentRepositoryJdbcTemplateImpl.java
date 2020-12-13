package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Appointment;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class AppointmentRepositoryJdbcTemplateImpl implements AppointmentRepository {

    //language=SQL
    private static final String SQL_DELETE_BY_ID = "delete from appointments where id = ?";

    //language=SQL
    private static final String SQL_FIND_APPOINTMENTS_BY_DOCTOR = "select * from appointments where doctor_id = ?";

    //language=SQL
    private static final String SQL_FIND_APPOINTMENTS_BY_CUSTOMER = "select * from appointments where customer_id = ?";

    //language=SQL
    private static final String SQL_INSERT = "insert into appointments(customer_id, doctor_id, service_id, date) " +
            "values (?, ?, ?, ?)";

    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Appointment> appointmentRowMapper = (row, rowNumber) -> Appointment.builder()
            .id(row.getLong("id"))
            .customer_id(row.getLong("customer_id"))
            .doctor_id(row.getLong("doctor_id"))
            .service_id(row.getLong("service_id"))
            .date(row.getString("date")).build();

    public AppointmentRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Appointment entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getCustomer_id(),
                entity.getDoctor_id(),
                entity.getService_id(),
                entity.getDate());
    }

    @Override
    public void update(Appointment entity) {

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.query(SQL_DELETE_BY_ID, appointmentRowMapper, id);
    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByDoctor(Long docId) {
        return jdbcTemplate.query(SQL_FIND_APPOINTMENTS_BY_DOCTOR, appointmentRowMapper, docId);
    }

    @Override
    public List<Appointment> getAppointmentsByCustomer(Long customerId) {
        return jdbcTemplate.query(SQL_FIND_APPOINTMENTS_BY_CUSTOMER, appointmentRowMapper, customerId);
    }
}
