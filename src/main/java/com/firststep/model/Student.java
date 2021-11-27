package com.firststep.model;

import javax.persistence.*;
import java.util.Base64;

@Entity
@Table
public class Student extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long joinedYear;

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
}
