package com.wipro.profile.risk.assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.profile.risk.assessment.model.ModifyUser;
import com.wipro.profile.risk.assessment.model.Summary;
import com.wipro.profile.risk.assessment.model.User;
import com.wipro.profile.risk.assessment.model.UserRegistrationDto;
import com.wipro.profile.risk.assessment.service.UserService;

@Controller
@RequestMapping("/investmentrecommendation/admin")
public class AdminController {

	private final UserService userService;
	private static final String riskProfileView = "/admin/investorRiskProfile";
	private static final String investorDetailsView = "/admin/investorDetails";
	private static final String userList = "users";
	private static final String NO_USER_FOUND = "no user found";

	ModifyUser userToModify = null;

	boolean flag = false;

	@Autowired
	public AdminController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/InvestorRiskProfile", method = RequestMethod.GET)
	public ModelAndView investorRiskDetails() {
		ModelAndView model = new ModelAndView();
		UserRegistrationDto user = new UserRegistrationDto();
		model.addObject("user", user);
		model.setViewName(riskProfileView);
		return model;
	}

	@RequestMapping(value = "/InvestorDetails", method = RequestMethod.GET)
	public ModelAndView investorDetails() {
		ModelAndView model = new ModelAndView();
		UserRegistrationDto user = new UserRegistrationDto();
		model.addObject("user", user);
		model.setViewName(investorDetailsView);
		return model;
	}

	@RequestMapping(value = "/modifyInvestorDetailsOne", method = RequestMethod.POST)
	public @ResponseBody HttpStatus modifyInvestorDetailsOne(@RequestBody ModifyUser selectedUser) {
		userToModify = selectedUser;
		return HttpStatus.OK;
	}

	@RequestMapping(value = "/modifyInvestorDetailsTwo", method = RequestMethod.GET)
	public ModelAndView modifyInvestordetailsDataTwo() {
		ModelAndView model = new ModelAndView();
		ModifyUser selectedUser = null;
		selectedUser = userToModify;
		User user = userService.findUserByEmail(selectedUser.getUserName());
		model.addObject("user", user);
		model.setViewName("/admin/modifyInvestorDetails");
		return model;
	}

	@RequestMapping(value = "/saveModifiedInvestorDetails", method = RequestMethod.POST)
	public ModelAndView saveModifiedInvestordetailsData(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView();
		userService.save(user);
		model.addObject("user", user);
		model.setViewName("/admin/investorDetailsModifiedSuccess");
		return model;
	}

	@RequestMapping(value = "/getAllInvestordetails", method = RequestMethod.GET)
	public ModelAndView getInvestorDetails() {
		List<User> users = userService.find();
		flag = true;
		ModelAndView model = new ModelAndView();
		UserRegistrationDto user = new UserRegistrationDto();
		model.addObject("user", user);
		model.addObject(userList, users);
		model.addObject("flag", flag);
		model.setViewName(investorDetailsView);
		return model;
	}

	@RequestMapping(value = "/getAllRiskInvestorProfiles", method = RequestMethod.GET)
	public ModelAndView getAllRiskInvestorProfiles() {
		ModelAndView model = new ModelAndView();
		User user = new User();
		model.addObject("user", user);
		List<Summary> summaries = userService.findRiskProfiles();
		if (summaries != null && !summaries.isEmpty() && summaries.size() > 0) {
			flag = true;
			model.addObject("users", summaries);
			model.addObject("flag", flag);
		} else {
			model.addObject("msg", NO_USER_FOUND);
		}
		model.setViewName(riskProfileView);
		return model;
	}

	@RequestMapping(value = "/investorRiskProfile/search", method = RequestMethod.POST)
	public ModelAndView searchInvestorRiskDetails(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String mobile = user.getMobile();
		String email = user.getEmail();
		if(firstName != "" || lastName != "" || mobile != "" || email != ""){
		Summary summary = userService.searchRiskProfiles(firstName, lastName, mobile, email);
		if (summary != null) {
			flag = true;
			model.addObject(userList, summary);
			model.addObject("flag", flag);
		} 
		}else
		{
			model.addObject("msg", NO_USER_FOUND);
		}
		model.setViewName(riskProfileView);
		return model;
	}

	@RequestMapping(value = "/investorDetails/search", method = RequestMethod.POST)
	public ModelAndView searchInvestorDetails(@ModelAttribute("user") User user) {
		ModelAndView model = new ModelAndView();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String mobile = user.getMobile();
		String email = user.getEmail();
		User details = userService.getInvestorDetails(firstName, lastName, mobile, email);
		UserRegistrationDto userDTO = new UserRegistrationDto();
		model.addObject("user", userDTO);
		if (details != null) {
			flag = true;
			model.addObject(userList, details);
			model.addObject("flag", flag);
		} else {
			model.addObject("msg", NO_USER_FOUND);
		}
		model.setViewName(investorDetailsView);
		return model;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView adminHome() {

		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		model.addObject("userName", user.getFirstName() + " " + user.getLastName());
		model.setViewName("/admin/home");
		return model;
	}

}
