package ru.itis.listener;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.services.SignUpService;
import ru.itis.services.SignUpServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 26.10.2020
 * 05. Skeleton
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebListener
public class SkeletonListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/11-901");
        dataSource.setUsername("postgres");
        dataSource.setPassword("qwerty007");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository, passwordEncoder);

        servletContext.setAttribute("signUpService", signUpService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
