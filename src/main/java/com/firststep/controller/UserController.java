package com.firststep.controller;

import com.firststep.model.Instructor;
import com.firststep.model.Student;
import com.firststep.model.User;
import com.firststep.service.UserService;
import com.firststep.utill.CommonUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handle Student and Instructor related API's
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/student/signup", produces="application/json")
    public ResponseEntity signupStudent(@RequestBody Student student) {

        try {
            userService.saveOrUpdate(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(CommonUtill.Messages.SUCCESS);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }
    @PostMapping(value = "/instructor/signup", produces="application/json")
    public ResponseEntity signupInstructor(@RequestBody Instructor instructor) {

        try {
            userService.saveOrUpdate(instructor);
            return ResponseEntity.status(HttpStatus.CREATED).body(CommonUtill.Messages.SUCCESS);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }

    @PostMapping(value = "/student", produces="application/json")
    public ResponseEntity getStudent(@RequestParam Long id) {

        try {
            User u = userService.getUser(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(CommonUtill.Messages.SUCCESS);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }


    @PostMapping(value = "/login", produces="application/json")
    public ResponseEntity login(@RequestParam String email, @RequestParam String password,
                                @RequestParam String loginType) {

        try {
            int auth = userService.login(email,password,loginType);
            if(auth > 0){
                return ResponseEntity.status(HttpStatus.OK).body(CommonUtill.Messages.SUCCESS);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonUtill.Messages.ERROR);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
        }
    }
}
