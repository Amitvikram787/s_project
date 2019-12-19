package com.cognizant.userauthenticationservice.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_feedback")
public class UserFeedback {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="uf_id")
	private int userFeedbackId;
	@Column(name="uf_date")
	private Date date;
	@Column(name="uf_rating_1")
	private String answer1;
	@Column(name="uf_rating_2")
	private String answer2;
	@Column(name="uf_rating_3")
	private String answer3;
	@Column(name="uf_rating_4")
	private String answer4;
	@Column(name="uf_rating_5")
	private String answer5;
	@Column(name="uf_rating_6")
	private String answer6;
	@Column(name="uf_rating_7")
	private String answer7;
	@Column(name="uf_rating_8")
	private String answer8;
	@Column(name="uf_rating_9")
	private String answer9;
	@Column(name="uf_rating_10")
	private String answer10;
	@OneToOne
	@JoinColumn(name="uf_fe_id")
	private Feedback feedback;
	@ManyToOne
	@JoinColumn(name="uf_us_id")
	private User user;
	public UserFeedback() {
		super();
	}
	public UserFeedback(int userFeedbackId, Date date, String answer1, String answer2, String answer3, String answer4,
			String answer5, String answer6, String answer7, String answer8, String answer9, String answer10,
			Feedback feedback, User user) {
		super();
		this.userFeedbackId = userFeedbackId;
		this.date = date;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.answer5 = answer5;
		this.answer6 = answer6;
		this.answer7 = answer7;
		this.answer8 = answer8;
		this.answer9 = answer9;
		this.answer10 = answer10;
		this.feedback = feedback;
		this.user = user;
	}
	public int getUserFeedbackId() {
		return userFeedbackId;
	}
	public void setUserFeedbackId(int userFeedbackId) {
		this.userFeedbackId = userFeedbackId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getAnswer5() {
		return answer5;
	}
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	public String getAnswer6() {
		return answer6;
	}
	public void setAnswer6(String answer6) {
		this.answer6 = answer6;
	}
	public String getAnswer7() {
		return answer7;
	}
	public void setAnswer7(String answer7) {
		this.answer7 = answer7;
	}
	public String getAnswer8() {
		return answer8;
	}
	public void setAnswer8(String answer8) {
		this.answer8 = answer8;
	}
	public String getAnswer9() {
		return answer9;
	}
	public void setAnswer9(String answer9) {
		this.answer9 = answer9;
	}
	public String getAnswer10() {
		return answer10;
	}
	public void setAnswer10(String answer10) {
		this.answer10 = answer10;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
