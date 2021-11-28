package com.firststep.dto;

import com.firststep.model.User;

/**
 * DTO class for Course Model
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */
public class CourseDTO {

    private Long id;
    private String courseName;
    private String courseDescription;
    private String status;
    private Long instructorId;
    private User instructor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public User getInstructor() {
        return instructor;
    }

    public void setInstructor(User instructor) {
        this.instructor = instructor;
    }
}
