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
	private WordsService wordsService;

	public RegistrationController(UserService userService, WordsService wordsService) {
		super();
		this.userService = userService;
		this.wordsService = wordsService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@ModelAttribute("words")
	public WordsRegistrationDto wordsRegistrationDto() {
		return new WordsRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		// UserRegistrationDto registrationDto = new UserRegistrationDto();
		// registrationDto.setEmail("s");
		// registrationDto.setFirstName("s");
		// registrationDto.setLastName("s");
		// registrationDto.setPassword("s");
		// userService.save(registrationDto);
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, @ModelAttribute("words") WordsRegistrationDto wordsRegistrationDto) {

		userService.save(registrationDto);
		wordsService.save(wordsRegistrationDto);
		return "redirect:/registration?success";
	}
	
}
