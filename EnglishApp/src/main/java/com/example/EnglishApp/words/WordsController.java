package com.example.EnglishApp.words;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;



@RestController
@RequestMapping("/LK/words")
public class WordsController {


    private LKRepository lkRepository;

    public WordsController(LKRepository lkRepository) {
        super();
        this.lkRepository = lkRepository;
    }

    @GetMapping
	public ResponseEntity<List<UserLK>> showRegistrationForm(Pageable pageable, Principal principal) {

        if (lkRepository.findByEmail(principal.getName()) != null){ 
            Page<UserLK> page = lkRepository.findAllByEmail(principal.getName(),
                PageRequest.of(
                    pageable.getPageNumber(),
                    pageable.getPageSize(),
                    pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
            ));
            System.out.println(page);
            return ResponseEntity.ok(page.getContent());
        }

        return ResponseEntity.notFound().build();




		// UserLK user = lkRepository.findByEmail(principal.getName());
        // if (user != null) {
        //     return ResponseEntity.ok(user);
        // }
        // return ResponseEntity.notFound().build();
	}

}
