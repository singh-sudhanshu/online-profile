package com.wipro.profile.risk.assessment.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.profile.risk.assessment.model.InvestmentRecommendation;

@Repository
public interface RecommendationRepository extends JpaRepository<InvestmentRecommendation, Integer> {

	@Transactional
	@Query(value = "select p.id, p.investment_strategy_description, p.dept_recommendation, p.equity_recommendations from investment_recommendations p where p.risk_profile_name = :profileName", nativeQuery = true)
	InvestmentRecommendation profileRecommendation(@Param("profileName") String profileName);
}
