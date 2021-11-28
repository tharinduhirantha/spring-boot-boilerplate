package com.firststep.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Entity class for Instructor
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */
@Entity
@Table
public class Instructor extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String qualification;
    @OneToMany(mappedBy="instructor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Course> course;


//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(name = "instructor_course",
//            joinColumns = { @JoinColumn(name = "instructor_id") },
//            inverseJoinColumns = { @JoinColumn(name = "course_id") })
//    private Set<Course> course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }


}
