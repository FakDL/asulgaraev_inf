package ru.itis.listener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SkeletonListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setUsername("postgres");
        dataSource.setPassword("20123124");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        DoctorsRepository doctorsRepository = new DoctorsRepositoryJdbcTemplateImpl(dataSource);
        DepartmentRepository departmentRepository = new DepartmentRepositoryJdbcTemplateImpl(dataSource);
        ServicesRepository servicesRepository = new ServicesRepositoryJdbcTemplateImpl(dataSource);
        AppointmentRepository appointmentRepository = new AppointmentRepositoryJdbcTemplateImpl(dataSource);

        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder);
        SignInService signInService = new SignInServiceImpl(usersRepository, passwordEncoder);
        ProfileService profileService = new ProfileServiceImpl( appointmentRepository, doctorsRepository, servicesRepository);
        DoctorsService doctorsService = new DoctorsServiceImpl(doctorsRepository, departmentRepository);
        DepartmentService departmentService = new DepartmentServiceImpl(departmentRepository);
        ServicesService servicesService = new ServicesServiceImpl(servicesRepository);
        AppointmentService appointmentService =
                new AppointmentServiceImpl(doctorsRepository,servicesRepository,appointmentRepository);

        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("profileService", profileService);
        servletContext.setAttribute("departmentService", departmentService);
        servletContext.setAttribute("doctorsService", doctorsService);
        servletContext.setAttribute("servicesService", servicesService);
        servletContext.setAttribute("appointmentService", appointmentService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
