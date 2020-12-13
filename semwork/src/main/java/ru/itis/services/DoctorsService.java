package ru.itis.services;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface DoctorsService {
    void sendDocList(HttpServletRequest req);
    List getAllByDepId(Long depId);
}
