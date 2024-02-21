package com.example.useractivity.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.useractivity.dto.UserDTO;
import com.example.useractivity.entity.User;
import com.example.useractivity.repository.UserTrackRepository;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api")
public class UserController { 
    
	@Autowired private UserTrackRepository userTrackRepository;
	
	@GetMapping("/track-record")
    public ResponseEntity<?> findAllStudents(HttpServletRequest httpServletRequest) {
        System.out.println("after process interceptor ");
        // will run actual logic
        return   ResponseEntity.ok("after process interceptor ");
    }
    
    @PostMapping("/add-user")
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO){
    	User user = new User();
    	BeanUtils.copyProperties(userDTO, user);
    	LocalDateTime localDateTime = LocalDateTime.now();
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	 String formattedDateTime = localDateTime.format(formatter);
    	user.setRequestDate(formattedDateTime); 
    	user.setRequestLimit(200);
    	user.setTotalRequest(0);
    	user.setRequestTimeDuration("3600");
    	userTrackRepository.save(user);
		return ResponseEntity.ok("user successfully added ....?");
    	
    }
}
