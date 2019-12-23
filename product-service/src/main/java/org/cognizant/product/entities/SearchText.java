package org.cognizant.product.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="search_text")
public class SearchText {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="st_id")
	private int id;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="st_us_id")
	private User user;
	@Column(name="st_text")
	private String text;
	public SearchText() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchText(int id, User user, String text) {
		super();
		this.id = id;
		this.user = user;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "SearchText [id=" + id + ", user=" + user + ", text=" + text + "]";
	}

	
}
