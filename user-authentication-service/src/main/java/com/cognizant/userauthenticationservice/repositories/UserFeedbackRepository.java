package com.cognizant.userauthenticationservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.userauthenticationservice.entities.User;
import com.cognizant.userauthenticationservice.entities.UserFeedback;



public interface UserFeedbackRepository extends JpaRepository<UserFeedback, Integer> {
	
	
	public List<UserFeedback> findByUser(User user); 
}
