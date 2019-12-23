package com.cognizant.userauthenticationservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cognizant.userauthenticationservice.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	@Query(value = "select * from  feedback ", nativeQuery=true)
	public Feedback getAllFeedback();

}
