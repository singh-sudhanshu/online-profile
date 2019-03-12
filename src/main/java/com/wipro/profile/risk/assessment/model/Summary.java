package com.wipro.profile.risk.assessment.model;

public class Summary {

	private String userName;
	private Integer riskScore;
	private String recommendation;
	private Integer debtRecommendation;
	private Integer equityRecommendation;
	private String profileName;

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public Integer getDebtRecommendation() {
		return debtRecommendation;
	}

	public void setDebtRecommendation(Integer debtRecommendation) {
		this.debtRecommendation = debtRecommendation;
	}

	public Integer getEquityRecommendation() {
		return equityRecommendation;
	}

	public void setEquityRecommendation(Integer equityRecommendation) {
		this.equityRecommendation = equityRecommendation;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRiskScore() {
		return riskScore;
	}

	public void setRiskScore(Integer riskScore) {
		this.riskScore = riskScore;
	}

	@Override
	public String toString() {
		return "Summary [userName=" + userName + ", riskScore=" + riskScore + ", recommendation=" + recommendation
				+ ", debtRecommendation=" + debtRecommendation + ", equityRecommendation=" + equityRecommendation
				+ ", profileName=" + profileName + "]";
	}

}
