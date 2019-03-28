package com.wipro.profile.risk.assessment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.wipro.profile.risk.assessment.model.AssessmentData;
import com.wipro.profile.risk.assessment.model.AssessmentResult;
import com.wipro.profile.risk.assessment.model.InvestmentRecommendation;
import com.wipro.profile.risk.assessment.model.Summary;
import com.wipro.profile.risk.assessment.model.User;
import com.wipro.profile.risk.assessment.model.UserRegistrationDto;

public interface UserService extends UserDetailsService {
	
	User findUserByEmail(String email);
	User save(UserRegistrationDto registration);
	void save(User user);
	List<User> find();
	User getInvestorDetails(String firstName, String lastName, String mobile, String email);	
	AssessmentResult profileResult(Map<Double, AssessmentData> options);
	String findByKey(Optional<String> userName, String questionNumber);
	Integer profileScore(String questionNumber, String selectedOption);
	InvestmentRecommendation profileRecommendation(String profileName);
	List<AssessmentData> assesmsentData(String userName);
	String profileName(Integer profileScore);
	List<AssessmentData> getAssessmentDataByEmail(String email);
	List<com.wipro.profile.risk.assessment.model.Summary> findRiskProfiles();
	Summary searchRiskProfiles(String firstName, String lastName, String mobile, String email);	
}
