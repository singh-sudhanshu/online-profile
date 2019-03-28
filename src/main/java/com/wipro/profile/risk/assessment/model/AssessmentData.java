package com.wipro.profile.risk.assessment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "assessment_data")
@IdClass(CompositeKey.class)
public class AssessmentData {		
	
	@Id
	@Column(name = "user_name")
	private String userName;
	
	@Id
	@Column(name ="question_number")
	private String questionNumber;
	
	@Id
	@Column(name = "selected_option")
	private String selectedOption;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getQuestionNumber() {
		return questionNumber;
	}
	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	
	@Override
	public String toString() {
		return "AssessmentData [userName=" + userName + ", questionNumber=" + questionNumber + ", selectedOption="
				+ selectedOption + "]";
	}

}
