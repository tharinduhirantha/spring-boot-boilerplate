package com.firststep.controller;

import com.firststep.model.Instructor;
import com.firststep.model.Student;
import com.firststep.model.User;
import com.firststep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/student/signup")
    public ResponseEntity signupStudent(@RequestBody Student student) {
        userService.saveOrUpdate(student);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student Successfully Saved");
    }
    @PostMapping("/instructor/signup")
    public ResponseEntity signupInstructor(@RequestBody Instructor instructor) {
        userService.saveOrUpdate(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body("Student Successfully Saved");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String email, @RequestParam String password,
                                @RequestParam String loginType) {
        int auth = userService.login(email,password,loginType);
        if(auth > 0){
            return ResponseEntity.status(HttpStatus.OK).body("Successfully Logged in");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
    }
}
