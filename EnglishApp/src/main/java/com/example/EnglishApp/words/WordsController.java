package com.example.EnglishApp.words;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.EnglishApp.User.User;
import com.example.EnglishApp.dto.UserRegistrationDto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;



@Controller
@RequestMapping("/words")
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

    @GetMapping("/train")
    public ResponseEntity<List<UserLK>> returnWordsToTrain(Principal principal){
        //this.email = principal.getName();
        List<UserLK> page = lkRepository.findByEmail(principal.getName());
        List<UserLK> ans = new ArrayList<UserLK>();

        long now = (new Date()).getTime();
        for (UserLK word : page){
            if (ans.size() == 5) break;
            if (word.getWhenToTrain() < now){
                ans.add(word);
            }
        }
        return ResponseEntity.ok(ans);

    }

    //needs testing
    @PutMapping("/")
    private ResponseEntity<Void> putCashCard(@RequestBody UserLK[] wordsToUpdate, Principal principal) {
        int[] times = new int[] {1*24*60*60, 3*24*60*60, 7*24*60*60, 30*24*60*60};


        for (UserLK word : wordsToUpdate){
            //UserLK word1 = lkRepository.findByIdAndEmail(word.getId(), principal.getName());
            UserLK updatedword = new UserLK(word.getId(), word.getEmail(), 
                word.getWordEng(), word.getWordRus(), word.getWhenToTrain() + times[word.getCounty()], word.getCounty() + 1, word.getMainID());
            lkRepository.save(updatedword);
        }
        
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteWord(@PathVariable Long id,  Principal principal) {
        if (!lkRepository.existsByIdAndEmail(id, principal.getName())) {
            return ResponseEntity.notFound().build();
        }
        lkRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
