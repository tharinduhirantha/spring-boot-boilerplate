package com.firststep.repository;

import com.firststep.model.Course;
import com.firststep.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
/**
 * Dao for Course
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */

public interface CourseDao extends CrudRepository<Course, Long>
    {
        @Query(value = "SELECT u FROM Course u WHERE u.instructor.id = ?1")
        List<Course> findAllCoursesForInstructor(Long id);
    }


