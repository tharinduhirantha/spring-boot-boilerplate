package com.firststep.service;

import com.firststep.model.User;
import com.firststep.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    public int login(String email, String password, String userType) {
        boolean isValid = false;
        String queryTable = userType.equals("S") ? "STUDENT" : "INSTRUCTOR";
        password = Base64.getEncoder().encodeToString(password.getBytes());
        String sql = String.format("SELECT EMAIL FROM %s WHERE EMAIL = '%s' AND PASSWORD = '%s'", queryTable,email,password);
        System.out.println(sql);
        List<String> users = jdbcTemplate.queryForList(sql, String.class);
        return users.size();
    }
}