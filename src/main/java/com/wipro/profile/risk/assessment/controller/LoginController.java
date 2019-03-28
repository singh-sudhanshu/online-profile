package com.wipro.profile.risk.assessment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wipro.profile.risk.assessment.model.User;
import com.wipro.profile.risk.assessment.model.UserRegistrationDto;
import com.wipro.profile.risk.assessment.service.UserService;

@Controller
public class LoginController {
	
	private final UserService userService; 
	
	 @Autowired
	 public LoginController(UserService userService) {
		 this.userService = userService;
		 
	 }
	 
	 @ModelAttribute("user")
	 public UserRegistrationDto userRegistrationDto() {
	    return new UserRegistrationDto();
	  }
	
	@RequestMapping(value = {"/", "/investmentrecommendation/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView model = new ModelAndView();
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value = "/investmentrecommendation/CreateAccount", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView();
		
		UserRegistrationDto user = new UserRegistrationDto();
		model.addObject("user", user);
		model.setViewName("registration");
		return model;
	} 
	
	@RequestMapping(value = "/investmentrecommendation/CreateAccount", method = RequestMethod.POST)
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result){

        User existing = userService.findUserByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "registration";
        }

        userService.save(userDto);
        return "redirect:/investmentrecommendation/CreateAccount?success";
    }	
	
	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public ModelAndView accessDenied() {
		ModelAndView model = new ModelAndView();
		model.setViewName("access_denied");
		return model;
	}
}
