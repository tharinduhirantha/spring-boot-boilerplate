package com.firststep.service;

import com.firststep.dto.CourseDTO;
import com.firststep.dto.Transformer;
import com.firststep.model.Course;
import com.firststep.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

//@NamedQuery(name = "Course.findAllCoursesForInstructor",
//        query = "SELECT c FROM Course c WHERE c.instructor_id = :instructorId")
@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    Transformer transformer = new Transformer();

    public void saveOrUpdate(CourseDTO course) {
        Course model = transformer.convertCourseToDTO(course);
        courseRepository.save(model);
    }

  public List<Course> getCousebyInstructorId(Long id) {
        return (List<Course>) courseRepository.findAllCoursesForInstructor(id);
    }

    public void startCourse(Long courseId, String start) {
        Course course = courseRepository.findById(courseId).get();
        course.setStatus("START");
        courseRepository.save(course);
    }
}