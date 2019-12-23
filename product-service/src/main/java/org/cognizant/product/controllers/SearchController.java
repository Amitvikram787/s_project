package org.cognizant.product.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.cognizant.product.dto.SearchTextDTO;
import org.cognizant.product.dto.UserSearchTextDTO;
import org.cognizant.product.entities.SearchText;
import org.cognizant.product.entities.User;
import org.cognizant.product.services.SearchTextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("search-history")
public class SearchController {
	@Autowired
	SearchTextService searchTextService;
	
	@Autowired
	RestTemplate restTemplate;
	
	private User getUser(String username) {
		User user = restTemplate.getForObject("http://user-authentication-service/users/" + username, User.class);
		return user;
	}
	@PostMapping
	public SearchTextDTO addSearchTextByUser(@RequestBody SearchTextDTO searchText) {
		User user= getUser(searchText.getUsername());
		List<SearchText> history = this.searchTextService.getSearchByUser(user);
		String present = history.stream().map(hist -> hist.getText()).filter(text -> text.equals(searchText.getSearchText())).findAny().orElse(null);
		if(present != null) {
			SearchText text = new SearchText();
			text.setUser(user);
			text.setText(searchText.getSearchText());
			this.searchTextService.addSearchText(text);			
		}
		return searchText;
	}
	@GetMapping("/{id}")
	public UserSearchTextDTO getSearchTextByUser(@PathVariable("id") String username) {
		User user = getUser(username);
		List<SearchText> searchHistory = this.searchTextService.getSearchByUser(user);
		System.out.println(searchHistory);
		UserSearchTextDTO searchTextDTO = new UserSearchTextDTO();
		searchTextDTO.setUsername(user.getUserId());
		List<SearchTextDTO> history = searchHistory.stream().map(search -> new SearchTextDTO(search.getUser().getUserId(), search.getText())).collect(Collectors.toList());
		searchTextDTO.setSearchHistory(history);
		return searchTextDTO;
	}
	@DeleteMapping("/{id}")
	public void deleteSearchHistory(@PathVariable("id") String username) {
		User user = getUser(username);
		this.searchTextService.deleteUserSearch(user);
	}
}
