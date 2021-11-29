package com.firststep.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firststep.dto.CourseDTO;
import com.firststep.dto.StudentDTO;
import com.firststep.service.CourseService;
import com.firststep.utill.CommonUtill;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * Controller class for handle Course related API's
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    final static Logger logger = Logger.getLogger(CourseService.class.getName());

    @PostMapping(value = "/create", produces="application/json")
    public ResponseEntity createCourse(@RequestBody CourseDTO course) {

        try {
            courseService.saveOrUpdate(course);
            return ResponseEntity.status(HttpStatus.CREATED).body(CommonUtill.Messages.SUCCESS);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @PostMapping(value = "/start", produces="application/json")
    public ResponseEntity startCourse(@RequestParam Long courseId) {

        try {
            courseService.startCourse(courseId);
            return ResponseEntity.status(HttpStatus.OK).body(CommonUtill.Messages.SUCCESS);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @PostMapping(value = "/cancel", produces="application/json")
    public ResponseEntity cancelCourse(@RequestParam Long courseId) {

        try {
            courseService.cancelCourse(courseId);
            return ResponseEntity.status(HttpStatus.OK).body(CommonUtill.Messages.SUCCESS);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @GetMapping(value = "/instructor", produces="application/json")
    public ResponseEntity getInstructorCreatedCourses(@RequestParam Long instructorId) {

        try {
            ArrayList<CourseDTO> courses = courseService.getCousebyInstructorId(instructorId);
            String json = MAPPER.writeValueAsString(courses);

            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @PostMapping(value = "/student/enroll", produces="application/json")
    public ResponseEntity enrollCourse(@RequestParam  Long [] courseIds, @RequestParam Long studentId) {
        try {
            courseService.enroll(courseIds,studentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(CommonUtill.Messages.SUCCESS);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @GetMapping(value = "/student", produces="application/json")
    public ResponseEntity getStudentEnrolledCourses(@RequestParam Long studentId) {
        try {
            StudentDTO dto = courseService.getEnrolledCoursedForStudent(studentId);
            String json = MAPPER.writeValueAsString(dto);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @GetMapping(value = "/all", produces="application/json")
    public ResponseEntity geAllCourses() {
        try {
            Set<CourseDTO> list = courseService.geAllCourses();
            String json = MAPPER.writeValueAsString(list);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @GetMapping(value = "/enroll/student", produces="application/json")
    public ResponseEntity geAStudentsAssignedForCourses(@RequestParam Long courseId) {
        try {
            Set<StudentDTO> list = courseService.geAStudentsForCourse(courseId);
            String json = MAPPER.writeValueAsString(list);
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getStackTrace());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

}
