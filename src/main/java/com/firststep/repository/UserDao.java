package com.firststep.repository;

import com.firststep.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Dao for User
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */
public interface UserDao extends CrudRepository<User, Integer>
    {
        @Query(value = "SELECT s FROM Student s WHERE s.id = ?1")
        User getStudentByID(Long id);
    }



