package com.example.EnglishApp.words;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/LK/words")
public class WordsController {


    private LKRepository lkRepository;

    public WordsController(LKRepository lkRepository) {
        super();
        this.lkRepository = lkRepository;
    }

    @GetMapping
	public ResponseEntity<UserLK> showRegistrationForm(Principal principal) {
        
		UserLK user = lkRepository.findByEmail(principal.getName());
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
	}

}
