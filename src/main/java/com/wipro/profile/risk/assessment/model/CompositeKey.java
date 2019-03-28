package com.wipro.profile.risk.assessment.model;

import java.io.Serializable;


public class CompositeKey implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	private String userName;	
	private String questionNumber;	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((questionNumber == null) ? 0 : questionNumber.hashCode());
		result = prime * result + ((selectedOption == null) ? 0 : selectedOption.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompositeKey other = (CompositeKey) obj;
		if (questionNumber == null) {
			if (other.questionNumber != null)
				return false;
		} else if (!questionNumber.equals(other.questionNumber))
			return false;
		if (selectedOption == null) {
			if (other.selectedOption != null)
				return false;
		} else if (!selectedOption.equals(other.selectedOption))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}
