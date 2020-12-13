package ru.itis.services;

import javax.servlet.http.HttpServletRequest;

public interface ProfileService {
    void setData(HttpServletRequest request);
    void deleteById(Long id);
}

