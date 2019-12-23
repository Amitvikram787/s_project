package org.cognizant.product.dto;

import java.util.ArrayList;
import java.util.List;

public class UserSearchTextDTO {
	private String username;
	private List<SearchTextDTO> searchHistory;

	public UserSearchTextDTO() {
		super();
		searchHistory = new ArrayList<SearchTextDTO>();
	}

	public UserSearchTextDTO(String username, List<SearchTextDTO> searchHistory) {
		super();
		this.username = username;
		this.searchHistory = searchHistory;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<SearchTextDTO> getSearchHistory() {
		return searchHistory;
	}

	public void setSearchHistory(List<SearchTextDTO> searchHistory) {
		this.searchHistory = searchHistory;
	}

	@Override
	public String toString() {
		return "UserSearchTextDTO [username=" + username + ", searchHistory=" + searchHistory + "]";
	}

}
