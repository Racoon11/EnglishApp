package com.example.EnglishApp.words;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EnglishApp.User.User;
import com.example.EnglishApp.dto.UserRegistrationDto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;



@Controller
@RequestMapping("/LK/words")
public class WordsController {


    private LKRepository lkRepository;
    private WordsService wordsService;
    private String email;

    public WordsController(LKRepository lkRepository, WordsService wordsService) {
        super();
        this.lkRepository = lkRepository;
        this.wordsService = wordsService;
    }

    @ModelAttribute("words")
	public WordsRegistrationDto wordsRegistrationDto() {
		return new WordsRegistrationDto(email);
	}

    @GetMapping
	public ResponseEntity<List<UserLK>> showRegistrationForm(Principal principal) {
        this.email = principal.getName();
        List<UserLK> page = lkRepository.findByEmail(principal.getName());
        System.out.println(page);
        return ResponseEntity.ok(page);
        
        //this.email = principal.getName();
        // UserLK user = lkRepository.findByEmail(principal.getName());

        // if (user != null) {
        //     return ResponseEntity.ok(user);
        // }
        //return ResponseEntity.notFound().build();




		// UserLK user = lkRepository.findByEmail(principal.getName());
        // if (user != null) {
        //     return ResponseEntity.ok(user);
        // }
        // return ResponseEntity.notFound().build();
	}
    @GetMapping("/add")
	public String showRegistrationForm() {
		return "addWord";
	}
    @PostMapping("/add")
	public String registerUserAccount(@ModelAttribute("words") WordsRegistrationDto wordsRegistrationDto) {

		wordsService.save(wordsRegistrationDto);
		//wordsService.save(wordsRegistrationDto);
        return "redirect:/LK/words/add";
	}

}
