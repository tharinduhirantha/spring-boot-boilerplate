package com.firststep.model;

import javax.persistence.*;
import java.util.Base64;
/**
 * Entity class for User. [Parent]
 *
 * @author  Tharindu Hirantha
 * @version 1.0
 * @since   2021/11/27
 */

@MappedSuperclass
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String password;
    @Column
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Base64.getEncoder().encodeToString(password.getBytes());;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
