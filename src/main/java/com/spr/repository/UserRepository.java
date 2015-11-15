package com.spr.repository;

import com.spr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> getUserByName(String name); /*getting user by his name with the magic of spring data*/

}
