package com.firststep.dto;

import com.firststep.model.Course;
import com.firststep.model.Instructor;
import com.firststep.model.Student;
import com.firststep.model.User;

/**
 * Class for convert DTO to Model or Model to DTO
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */
public class Transformer {

    public Course convertCourseDTOToModel(CourseDTO dto){
        Course model = new Course ();
        model.setCourseName(dto.getCourseName());
        model.setCourseDescription(dto.getCourseDescription());
        model.setStatus(dto.getStatus());
        Instructor inModel = new Instructor ();
        inModel.setId(dto.getInstructorId());
        model.setInstructor(inModel);
        return model;
    }

    public CourseDTO convertCourseToDTO(Course model){
        CourseDTO dto = new CourseDTO ();
        User userDto = new User ();

        dto.setId(model.getId());
        dto.setCourseName(model.getCourseName());
        dto.setCourseDescription(model.getCourseDescription());
        dto.setStatus(model.getStatus());

        userDto.setFirstName(model.getInstructor().getFirstName());
        userDto.setLastName(model.getInstructor().getLastName());
        userDto.setId(model.getInstructor().getId());
        userDto.setEmail(model.getInstructor().getEmail());

        dto.setInstructor(userDto);
        return dto;
    }

    public StudentDTO convertStudentToDTO(Student model){
        StudentDTO dto = new StudentDTO ();
        dto.setJoinedYear(model.getJoinedYear());
        dto.setEmail(model.getEmail());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setId(model.getId());
        return dto;
    }
}
