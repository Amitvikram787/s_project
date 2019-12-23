package org.cognizant.product.services;

import java.util.List;

import org.cognizant.product.entities.SearchText;
import org.cognizant.product.entities.User;
import org.cognizant.product.repositories.SearchTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchTextService {
	@Autowired
	SearchTextRepository searchRepository;
	
	public SearchText addSearchText(SearchText searchText) {
		return this.searchRepository.save(searchText);
	}
	public List<SearchText> getSearchByUser(User user) {
		System.out.println(user);
		return this.searchRepository.findByUser(user);
	}
	public void deleteUserSearch(User user) {
		this.searchRepository.deleteByUser(user);
	}
}
