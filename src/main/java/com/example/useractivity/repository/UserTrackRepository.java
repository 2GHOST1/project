package com.example.useractivity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.useractivity.entity.User;

 @Repository
public interface UserTrackRepository extends JpaRepository<User,Long> {
    User findByToken(String userToken);
}
