package com.wipro.profile.risk.assessment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InvestmentRecommendation {	
	
	@Id
	@Column(name = "id", insertable = false, updatable = false)
	private Integer id;
	
	@Column(name = "investment_strategy_description", insertable = false, updatable = false)
	private String Recommendation;	
	
	@Column(name = "dept_recommendation", insertable = false, updatable = false)
	private Integer debtRecommendation;
	
	@Column(name = "equity_recommendations", insertable = false, updatable = false)
	private Integer equityRecommendation;
	
	public String getRecommendation() {
		return Recommendation;
	}
	public void setRecommendation(String recommendation) {
		Recommendation = recommendation;
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
}
