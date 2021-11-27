package com.firststep.repository;

import com.firststep.model.Course;
import com.firststep.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course, Long>
    {
        @Query(value = "SELECT u FROM Course u WHERE u.instructor.id = ?1")
        List<Course> findAllCoursesForInstructor(Long id);
    }


