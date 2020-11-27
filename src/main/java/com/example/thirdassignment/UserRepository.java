package com.example.thirdassignment;

import org.springframework.data.repository.CrudRepository;

import com.example.thirdassignment.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers to Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByName(String name);
}
