package com.firststep.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.firststep.dto.CourseDTO;
import com.firststep.model.Course;
import com.firststep.model.Instructor;
import com.firststep.model.Student;
import com.firststep.service.CourseService;
import com.firststep.service.UserService;
import com.google.gson.Gson;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/create")
    public ResponseEntity signupStudent(@RequestBody CourseDTO course) {
        courseService.saveOrUpdate(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course Successfully Saved");
    }

    @PostMapping("/start")
    public ResponseEntity startCourse(@RequestParam Long courseId, @RequestParam String start) {
        courseService.startCourse(courseId,start);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course Successfully Started");
    }

    @GetMapping("/instructor")
    public ResponseEntity getInstructorCreatedCourses(@RequestParam Long instructorId) {
        List<Course> courses = courseService.getCousebyInstructorId(instructorId);
        String json = null;
        Gson gson = new Gson();
        gson.toJson(courses);
//        try {
//             json = objectMapper.writeValueAsString(courses);
//            System.out.println(json);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        return ResponseEntity.status(HttpStatus.OK).body( gson.toJson(courses));
    }

}
