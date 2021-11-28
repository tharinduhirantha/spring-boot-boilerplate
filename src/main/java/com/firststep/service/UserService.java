package com.firststep.service;

import com.firststep.model.User;
import com.firststep.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

/**
 * Service layer for User.
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */

@Service
public class UserService {
    @Autowired
    UserDao userRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    // Save or update student or instructor
    public void saveOrUpdate(User user) throws Exception {
        userRepository.save(user);
    }

    // Authentication
    public int login(String email, String password, String userType) throws Exception{
        String queryTable = userType.equals("S") ? "STUDENT" : "INSTRUCTOR";
        password = Base64.getEncoder().encodeToString(password.getBytes());
        String sql = String.format("SELECT EMAIL FROM %s WHERE EMAIL = '%s' AND PASSWORD = '%s'", queryTable,email,password);

        List<String> users = jdbcTemplate.queryForList(sql, String.class);
        return users.size();
    }

    // Return Student for given id
    public User getUser(Long id) throws Exception {
        User student = userRepository.getStudentByID(id);
        return student;
    }
}