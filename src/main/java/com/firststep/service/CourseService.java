package com.firststep.service;

import com.firststep.dto.CourseDTO;
import com.firststep.dto.StudentDTO;
import com.firststep.dto.Transformer;
import com.firststep.model.Course;
import com.firststep.model.Student;
import com.firststep.repository.CourseDao;
import com.firststep.repository.UserDao;
import com.firststep.utill.CommonUtill;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Service layer for Course.
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */

@Service
public class CourseService {

    final static Logger logger = Logger.getLogger(CourseService.class.getName());

    @Autowired
    CourseDao courseRepository;

    @Autowired
    UserDao userRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    Transformer transformer = new Transformer();

    // Save or update course
    public void saveOrUpdate(CourseDTO course) throws Exception{
        Course model = transformer.convertCourseDTOToModel(course);
        model.setStatus(CommonUtill.CouseStatus.CREATED);
        courseRepository.save(model);
        logger.info("Course saved");
    }

    // Return Courses assigned to instructor
  public ArrayList<CourseDTO> getCousebyInstructorId(Long id) {

      ArrayList<CourseDTO> returnList = new ArrayList<>();

      List<Course> courses = (List<Course>) courseRepository.findAllCoursesForInstructor(id);
      for (Course c: courses) {
          CourseDTO dto = new CourseDTO();
          dto.setCourseName(c.getCourseName());
          dto.setCourseDescription(c.getCourseDescription());
          dto.setStatus(c.getStatus());
          dto.setInstructorId(c.getInstructor().getId());
          dto.setId(c.getId());
          returnList.add(dto);
      }
        return returnList;
    }

    // Start course
    public void startCourse(Long courseId) throws Exception {
        Course course = courseRepository.findById(courseId).get();
        if(course != null){
            course.setStatus(CommonUtill.CouseStatus.START);
            courseRepository.save(course);
        } else {
          logger.error("Course not found");
          throw new Exception(CommonUtill.Errors.OBJECT_NOT_FOUND);
        }
    }

    // Return course
    public Course getCourse(Long courseId) throws Exception {
        Course course = courseRepository.findById(courseId).get();
        if(course != null){
           return course;
        } else {
            logger.error("Course not found");
            throw new Exception(CommonUtill.Errors.OBJECT_NOT_FOUND);
        }
    }

    // Cancel course
    public void cancelCourse(Long courseId) throws Exception {
        Course course = courseRepository.findById(courseId).get();
        if(course != null && !course.getStatus().equalsIgnoreCase(CommonUtill.CouseStatus.START)){
            course.setStatus(CommonUtill.CouseStatus.CANCEL);
            courseRepository.save(course);
        } else {
            logger.error("Course not found or course already started.");
            throw new Exception(CommonUtill.Errors.OBJECT_NOT_FOUND);
        }
    }

    // Student enroll to course
    public void enroll( Long [] courseIds, Long studentId) throws Exception {

        Student student = (Student) userRepository.getStudentByID(studentId);
        Set<Course> courses = new HashSet<>();
        for (Long courseId: courseIds) {
            Course course = getCourse(courseId);
            if(course.getStatus().equalsIgnoreCase(CommonUtill.CouseStatus.CREATED)){
                courses.add(course);
            }
        }
        student.setCourses(courses);
        userRepository.save(student);
        }

        //get Enrolled courses for a student
    public StudentDTO getEnrolledCoursedForStudent(Long studentId) throws Exception{
        Student studentModel = (Student) userRepository.getStudentByID(studentId);
        StudentDTO dto = transformer.convertStudentToDTO(studentModel);
        Set<CourseDTO> courseList = new HashSet<>();
        for(Course c : studentModel.getCourses()){
            CourseDTO cd = transformer.convertCourseToDTO(c);
            courseList.add(cd);
        }
        dto.setCourses(courseList);
        return dto;
    }

    // Return all courses
    public Set<CourseDTO> geAllCourses() throws Exception{
           Iterable<Course> courses = courseRepository.findAll();
           Set<CourseDTO> returnList = new HashSet<>();
        for(Course c: courses){
            returnList.add(transformer.convertCourseToDTO(c));
        }
        return returnList;
    }

    public Set<StudentDTO> geAStudentsForCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        Set<StudentDTO> returnList = new HashSet<>();

        for (Student student: course.getStudents()) {
            returnList.add(transformer.convertStudentToDTO(student));
        }

        return returnList;

    }
}