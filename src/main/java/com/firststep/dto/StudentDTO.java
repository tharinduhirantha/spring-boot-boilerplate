package com.firststep.dto;

import com.firststep.model.User;

/**
 * DTO class for Course Student and Instructor
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */
import java.util.Set;

public class StudentDTO extends User {

    private Long id;
    private Long joinedYear;
    private Set<CourseDTO> courses;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJoinedYear() {
        return joinedYear;
    }

    public void setJoinedYear(Long joinedYear) {
        this.joinedYear = joinedYear;
    }

    public Set<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseDTO> courses) {
        this.courses = courses;
    }
}
