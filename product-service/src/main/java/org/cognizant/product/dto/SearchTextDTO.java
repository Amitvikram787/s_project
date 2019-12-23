package org.cognizant.product.dto;

public class SearchTextDTO {
	private String username;
	private String searchText;
	public SearchTextDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchTextDTO(String username, String searchText) {
		super();
		this.username = username;
		this.searchText = searchText;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	@Override
	public String toString() {
		return "SearchTextDTO [username=" + username + ", searchText=" + searchText + "]";
	}
	
}
