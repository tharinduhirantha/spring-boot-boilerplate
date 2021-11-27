package com.firststep.dto;

import com.firststep.model.Course;
import com.firststep.model.Instructor;

public class Transformer {

    public Course convertCourseToDTO(CourseDTO dto){

        Course model = new Course ();
        model.setCourseName(dto.getCourseName());
        model.setCourseDescription(dto.getCourseDescription());
        model.setStatus(dto.getStatus());
        Instructor inModel = new Instructor ();
        inModel.setId(dto.getInstructorId());
        model.setInstructor(inModel);
        return model;
    }
}
