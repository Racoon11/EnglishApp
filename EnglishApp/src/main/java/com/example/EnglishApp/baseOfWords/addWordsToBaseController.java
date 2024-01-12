package com.example.EnglishApp.baseOfWords;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.RouteMatcher.Route;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.EnglishApp.words.UserLK;

import java.util.Scanner;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;






@Controller
@RequestMapping("/admin")
public class addWordsToBaseController {

    private baseOfWordsRepository lkRepository;
    private baseService wordsService;


    public addWordsToBaseController(baseOfWordsRepository lkRepository, baseService wordsService) {
        super();
        this.lkRepository = lkRepository;
        this.wordsService = wordsService;
    }

    @ModelAttribute("words")
	public baseDto BaseDto() {
		return new baseDto();
	}

    // @GetMapping
	// public ResponseEntity<Object> showSmth() {
    //     Scanner console = new Scanner(System.in);
    //     String line = console.next();
    //     String line2 = console.next();
    //     while(!((line.equals("0")) & (line2.equals("0")))){
    //         System.out.println(line);
    //         System.out.println(line2);
    //         baseOfWords BaseDto = new baseOfWords(line, line2);
    //         lkRepository.save(BaseDto);
    //         line = console.next();
    //         line2 = console.next();

    //     }
    //     console.close();
    //     return ResponseEntity.ok().build();
        
    //     //return String.format("Hello!");
	// }
    // @GetMapping("/file")
	// public String showSmth2() {

        
    //     Scanner console = new Scanner(System.in);
    //     String line = console.next();
    //     String line2 = console.next();
    //     while((line.equals("0")) & (line2.equals("0"))){
    //         System.out.println(line);
    //         System.out.println(line2);
    //         baseOfWords BaseDto = new baseOfWords(line, line2);
    //         lkRepository.save(BaseDto);
    //         line = console.next();
    //         line2 = console.next();

    //     }
    //     console.close();
        
    //     return String.format("Hello!");
	// }

    

    @GetMapping("/show")
	public ResponseEntity<Iterable<baseOfWords>> showEverything() {
        Iterable<baseOfWords> page = lkRepository.findAll();
        System.out.println(page);
        return ResponseEntity.ok(page);

	}
    @GetMapping("/getWords")
    public ResponseEntity<Iterable<baseOfWords>> getFiveWords() {
        Random rand = new Random();
        List<baseOfWords> listOfWords = new ArrayList<baseOfWords>(5);
        for (int i=0; i < 5; i++) {
            Long id = rand.nextLong(6, 8056);
            listOfWords.add(lkRepository.findById(id).get());
            
        }
        
        return ResponseEntity.ok(listOfWords);
    }


    @GetMapping("/getBy")
    public ResponseEntity<Iterable<baseOfWords>> getByString(@RequestParam String str) {
        Iterable<baseOfWords> page = lkRepository.findFirst10ByWordEngStartingWith(str);
        return ResponseEntity.ok(page);
    }
    
    
}
