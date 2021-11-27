package com.firststep.repository;

import com.firststep.model.Course;
import com.firststep.model.User;
import org.springframework.data.repository.CrudRepository;


    public interface UserRepository extends CrudRepository<User, Integer>
    {
    }



