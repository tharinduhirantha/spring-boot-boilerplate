package com.firststep.model;

import javax.persistence.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for Student
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */
@Entity
@Table
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long joinedYear;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Student_Course",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private Set<Course> courses;

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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
