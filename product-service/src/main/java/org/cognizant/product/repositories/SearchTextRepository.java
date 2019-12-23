package org.cognizant.product.repositories;

import java.util.List;

import org.cognizant.product.entities.SearchText;
import org.cognizant.product.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SearchTextRepository extends JpaRepository<SearchText, Integer> {
	List<SearchText> findByUser(User user);
	void deleteByUser(User user);
}
