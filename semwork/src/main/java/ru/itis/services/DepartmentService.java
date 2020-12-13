package ru.itis.services;



import javax.servlet.http.HttpServletRequest;

public interface DepartmentService {
    void sendDepList(HttpServletRequest req);
}
