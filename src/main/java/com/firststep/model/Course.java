package com.firststep.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Base64;
import java.util.Set;

@Entity
@Table

public class Course extends Audit{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ids")
    private Long id;
    @Column
    @JsonProperty("courseName")
    private String courseName;
    @Column
    @JsonProperty("courseDescription")
    private String courseDescription;
    @Column
    @JsonProperty("status")
    private String status;
    @ManyToOne
    @JoinColumn(name="instructor_id", referencedColumnName="id",nullable=false)
    private Instructor instructor;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            },
//            mappedBy = "course")
//    private Set<Instructor> instructors;


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

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
