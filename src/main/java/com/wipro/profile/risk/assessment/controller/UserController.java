package com.wipro.profile.risk.assessment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.profile.risk.assessment.model.AssessmentData;
import com.wipro.profile.risk.assessment.model.AssessmentResult;
import com.wipro.profile.risk.assessment.model.InvestmentRecommendation;
import com.wipro.profile.risk.assessment.model.Summary;
import com.wipro.profile.risk.assessment.model.User;
import com.wipro.profile.risk.assessment.service.UserService;

@Controller
@Scope("session")
@RequestMapping("/investmentrecommendation")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;		
	}

	Map<Double, AssessmentData> options = new HashMap<>();

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {

		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("user", user);
		model.setViewName("/user/home");
		return model;
	}

	@GetMapping("/investments")
	public ModelAndView investments() {
		ModelAndView model = new ModelAndView();
		Summary summary = investmentRecommendation();		
		if (summary != null && summary.getDebtRecommendation() != null) {	
			model.addObject("profileName", summary.getProfileName());
			model.addObject("debtRecommendation", summary.getDebtRecommendation());
			model.addObject("equityRecommendation", summary.getEquityRecommendation());
			model.addObject("recommendation", summary.getRecommendation());			
			model.setViewName("/user/summary");
		}

		else {
			model.setViewName("/user/notification");
		}
		return model;
	}

	@GetMapping("/questionnaire")
	public ModelAndView questionnaire() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getEmail());
		model.setViewName("/user/questionnaire");
		return model;
	}

	@GetMapping("/second")
	public ModelAndView second() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/user/second");
		return model;
	}

	@GetMapping("/third")
	public ModelAndView third() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/user/third");
		return model;
	}

	@GetMapping("/fourth")
	public ModelAndView fourth() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/user/fourth");
		return model;
	}

	@GetMapping("/fifth")
	public ModelAndView fifth() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/user/fifth");
		return model;
	}

	@GetMapping("/sixth")
	public ModelAndView sixth() {
		ModelAndView model = new ModelAndView();		
		model.setViewName("/user/sixth");
		return model;
	}

	@PostMapping("/store")
	public @ResponseBody HttpStatus store(HttpServletRequest request, @RequestBody AssessmentData data) {

		Double questionNumber = Double.parseDouble(data.getQuestionNumber());
		options.put(questionNumber, data);
		return HttpStatus.OK;
	}

	@GetMapping("/result")
	public ModelAndView result() {
		ModelAndView model = new ModelAndView();
		AssessmentResult result = userService.profileResult(options);
		model.addObject("result", result);
		model.setViewName("/user/result");
		return model;
	}

	@GetMapping("/summary")
	public ModelAndView summary() {
		ModelAndView model = new ModelAndView();		
		Summary summary = investmentRecommendation();		
		model.addObject("profileName", summary.getProfileName());
		model.addObject("debtRecommendation", summary.getDebtRecommendation());
		model.addObject("equityRecommendation", summary.getEquityRecommendation());
		model.addObject("recommendation", summary.getRecommendation());
		model.setViewName("/user/summary");
		return model;
	}
	
	public Summary investmentRecommendation() {
		Summary summary = new Summary();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		List<AssessmentData> assessmentData = userService.getAssessmentDataByEmail(user.getEmail());
		if (assessmentData != null && !assessmentData.isEmpty() && assessmentData.size() > 0) {
			Integer profileScore = 0;		
			for (AssessmentData data : assessmentData) {
				profileScore += userService.profileScore(data.getQuestionNumber(), data.getSelectedOption());
			}
			String profileName = userService.profileName(profileScore);
			InvestmentRecommendation recommendation = userService.profileRecommendation(profileName);
			summary.setDebtRecommendation(recommendation.getDebtRecommendation());
			summary.setEquityRecommendation(recommendation.getEquityRecommendation());
			summary.setProfileName(profileName);
			summary.setRecommendation(recommendation.getRecommendation());
		}		
		return summary;
	}

}
