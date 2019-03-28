package com.wipro.profile.risk.assessment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wipro.profile.risk.assessment.model.AssessmentData;
import com.wipro.profile.risk.assessment.model.AssessmentResult;
import com.wipro.profile.risk.assessment.model.InvestmentRecommendation;
import com.wipro.profile.risk.assessment.model.Role;
import com.wipro.profile.risk.assessment.model.Summary;
import com.wipro.profile.risk.assessment.model.User;
import com.wipro.profile.risk.assessment.model.UserRegistrationDto;
import com.wipro.profile.risk.assessment.repository.AssessmentRepository;
import com.wipro.profile.risk.assessment.repository.RecommendationRepository;
import com.wipro.profile.risk.assessment.repository.RoleRepository;
import com.wipro.profile.risk.assessment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final AssessmentRepository assessmentRepo;
	private final RecommendationRepository recommendationRepo;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			BCryptPasswordEncoder passwordEncoder, AssessmentRepository assessmentRepo,
			RecommendationRepository recommendationRepo) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.assessmentRepo = assessmentRepo;
		this.recommendationRepo = recommendationRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findUserByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public User save(UserRegistrationDto registration) {
		User user = new User();
		user.setFirstName(registration.getFirstName());
		user.setLastName(registration.getLastName());
		user.setMobile(registration.getMobile());
		user.setEmail(registration.getEmail());
		user.setHouseNo(registration.getHouseNo());
		user.setStreet(registration.getStreet());
		user.setCity(registration.getCity());
		user.setPincode(registration.getPincode());
		user.setState(registration.getState());
		user.setCountry(registration.getCountry());
		user.setActive(1);
		user.setPassword(passwordEncoder.encode(registration.getPassword()));		
		Role userRole = roleRepository.findByName("ROLE_USER");
		user.setRoles(Arrays.asList(userRole));
		return userRepository.save(user);
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<User> find() {
		return em.createQuery("select u from User u ", User.class).getResultList()
				.stream().filter(a -> isUser(a)).collect(Collectors.toList());
	}
	
	private boolean  isUser(User user) {
		Role userRole = roleRepository.findByName("ROLE_USER");
		if (user.getRoles().contains(userRole)) {
			return true;
		}		
		return false;		
	}
	
	public List<AssessmentData> findAssessmentData() {
		return em.createQuery("select u from AssessmentData u ", AssessmentData.class).getResultList();
	}

	@Override
	public User getInvestorDetails(String firstName, String lastName, String mobile, String email) {

		if ((firstName != "") && (lastName != "") && (mobile != "") && (email != "")) {
			return userRepository.findUserByFirstNameAndLastNameAndMobileAndEmail(firstName, lastName, mobile, email);
		} else if (firstName != "") {
			return userRepository.findUserByFirstName(firstName);
		} else if (lastName != "") {
			return userRepository.findUserByLastName(lastName);
		} else if (mobile != "") {
			return userRepository.findByMobile(mobile);
		} else {
			return userRepository.findUserByEmail(email);
		}

	}

	@Override
	public void save(User user) {
		User u = userRepository.findUserByEmail(user.getEmail());
		if (u != null) {
			String firstname = user.getFirstName();
			String lastname = user.getLastName();
			String mobile = user.getMobile();
			String email = user.getEmail();
			String houseNo = user.getHouseNo();
			String street = user.getStreet();
			String city = user.getCity();
			String pincode = user.getPincode();
			String state = user.getState();
			String country = user.getCountry();
			userRepository.updateUserSetForEmail(firstname, lastname, mobile, email, houseNo, street, city, pincode,
					state, country);
		} else {
			userRepository.save(user);
		}
	}

	@Override
	public AssessmentResult profileResult(Map<Double, AssessmentData> options) {

		AssessmentResult assessmentResult = new AssessmentResult();
		Integer profileScore = 0;

		Optional<String> userName = options.entrySet().stream().map(a -> a.getValue().getUserName()).findFirst();

		for (Entry<Double, AssessmentData> data : options.entrySet()) {
			AssessmentData res = data.getValue();

			if (findByKey(userName, res.getQuestionNumber()) != null) {
				updateUserResponse(userName, res.getQuestionNumber(), res.getSelectedOption());
			} else {
				saveUserResponse(userName, res.getQuestionNumber(), res.getSelectedOption());
			}
			profileScore += profileScore(res.getQuestionNumber(), res.getSelectedOption());
		}
		String profileName = profileName(profileScore);
		assessmentResult.setProfileScore(profileScore);
		assessmentResult.setProfileName(profileName);
		return assessmentResult;
	}

	@Override
	public String profileName(Integer profileScore) {

		if (16 <= profileScore && profileScore <= 22) {
			return "Conservative";
		}
		if (23 <= profileScore && profileScore <= 32) {
			return "Moderately Conservative";
		}
		if (33 <= profileScore && profileScore <= 42) {
			return "Moderate";
		}
		if (43 <= profileScore && profileScore <= 52) {
			return "Moderately Aggresive";
		}
		if (53 <= profileScore && profileScore <= 60) {
			return "Aggresive";
		}
		return "";
	}

	private void saveUserResponse(Optional<String> userName, String questionNumber, String selectedOption) {
		assessmentRepo.saveUserResponse(userName, questionNumber, selectedOption);
	}

	private void updateUserResponse(Optional<String> userName, String questionNumber, String selectedOption) {
		assessmentRepo.updateUserResponse(userName, questionNumber, selectedOption);
	}

	@Override
	public String findByKey(Optional<String> userName, String questionNumber) {
		return assessmentRepo.findByKey(userName, questionNumber);

	}

	@Override
	public Integer profileScore(String questionNumber, String selectedOption) {
		return assessmentRepo.profileScore(Integer.parseInt(questionNumber), selectedOption);
	}

	@Override
	public InvestmentRecommendation profileRecommendation(String profileName) {
		return recommendationRepo.profileRecommendation(profileName);
	}

	@Override
	public List<AssessmentData> assesmsentData(String email) {
		return assessmentRepo.getAssessmentDataByEmail(email);
	}

	@Override
	public List<AssessmentData> getAssessmentDataByEmail(String email) {
		return assessmentRepo.getAssessmentDataByEmail(email);
	}

	@Override
	public List<Summary> findRiskProfiles() {
		List<Summary> summaries = new ArrayList<Summary>();
		List<User> user = userRepository.findAll();
		for (User u : user) {
			Summary summary = new Summary();
			List<AssessmentData> assessmentData = getAssessmentDataByEmail(u.getEmail());			
			if (assessmentData != null && assessmentData.size() > 0) {
				Integer profileScore = 0;
				for (AssessmentData data : assessmentData) {
					profileScore += profileScore(data.getQuestionNumber(), data.getSelectedOption());
					summary.setUserName(data.getUserName());
					summary.setRiskScore(profileScore);
				}
				String profileName = profileName(profileScore);
				InvestmentRecommendation recommendation = profileRecommendation(profileName);
				summary.setDebtRecommendation(recommendation.getDebtRecommendation());
				summary.setEquityRecommendation(recommendation.getEquityRecommendation());
				summary.setProfileName(profileName);
				summary.setRecommendation(recommendation.getRecommendation());
				summaries.add(summary);
			}
		}
		return summaries;
	}

	@Override
	public Summary searchRiskProfiles(String firstName, String lastName, String mobile, String email) {

		Summary summary = new Summary();
		User userDetails = getInvestorDetails(firstName, lastName, mobile, email);
		if(userDetails != null){
		List<AssessmentData> assessmentData = getAssessmentDataByEmail(userDetails.getEmail());
		if (assessmentData != null && assessmentData.size() > 0) {
			Integer profileScore = 0;
			for (AssessmentData data : assessmentData) {
				profileScore += profileScore(data.getQuestionNumber(), data.getSelectedOption());
				summary.setUserName(data.getUserName());
				summary.setRiskScore(profileScore);
			}
			String profileName = profileName(profileScore);
			InvestmentRecommendation recommendation = profileRecommendation(profileName);
			summary.setDebtRecommendation(recommendation.getDebtRecommendation());
			summary.setEquityRecommendation(recommendation.getEquityRecommendation());
			summary.setProfileName(profileName);
			summary.setRecommendation(recommendation.getRecommendation());
		}
		}
		return summary;
	}
}