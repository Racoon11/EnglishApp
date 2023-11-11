package com.example.EnglishApp;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.EnglishApp.dto.UserRegistrationDto;
import com.example.EnglishApp.service.UserService;
import com.example.EnglishApp.words.LKRepository;
import com.example.EnglishApp.words.UserLK;
import com.example.EnglishApp.words.WordsRegistrationDto;
import com.example.EnglishApp.words.WordsService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	private UserService userService;

	public RegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {

		userService.save(registrationDto);
		return "redirect:/registration?success";
	}
	
}
