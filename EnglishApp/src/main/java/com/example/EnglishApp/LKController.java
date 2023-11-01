package com.example.EnglishApp;

import java.net.URI;
import java.security.Principal;
import java.util.List;
//import java.sql.Date;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.example.EnglishApp.dto.UserRegistrationDto;
import com.example.EnglishApp.repository.UserRepository;
import com.example.EnglishApp.words.UserLK;
import com.example.EnglishApp.User.User;



@Controller
@RequestMapping("/LK")
public class LKController {

    private UserRepository lkRepository;

    public LKController(UserRepository lkRepository) {
        super();
        this.lkRepository = lkRepository;
    }

    @GetMapping
	public ResponseEntity<User> showRegistrationForm(Principal principal) {

		User user = lkRepository.findByEmail(principal.getName());

        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
	}

    @GetMapping("/{nick}")
    public ResponseEntity<User> findById(@PathVariable String nick, Principal principal){

        User user = lkRepository.findByEmail(nick);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();

    }



    // @GetMapping("/{nick}")
    // public ResponseEntity<UserLK> findById(@PathVariable Long nick, Principal principal) {

    //     UserLK cashCard = findCashCard(nick, principal);

    //     if (cashCard != null) {
    //         return ResponseEntity.ok(cashCard);
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }


    // @PostMapping
    // private ResponseEntity<Void> createCashCard(@RequestBody UserLK newCashCardRequest, UriComponentsBuilder ucb, Principal principal){
        
    //     UserLK cashCardWithOwner = new UserLK(null, newCashCardRequest.amount(), principal.getName());
    //     UserLK savedCashCard = lkRepository.save(cashCardWithOwner);
    //     //id is provided by a DateBase
    //     URI locationOfNewCashCard = ucb
    //             .path("LK/{id}")
    //             .buildAndExpand(savedCashCard.id())
    //             .toUri();



    //     return ResponseEntity.created(locationOfNewCashCard).build();

    // } 

    // @GetMapping
    // public ResponseEntity<List<UserLK>> findAll(Pageable pageable, Principal principal){
    //     Page<UserLK> page = lkRepository.findByOwner(principal.getName(),
    //         PageRequest.of(
    //             pageable.getPageNumber(),
    //             pageable.getPageSize(),
    //             pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
    //     ));

    //     return ResponseEntity.ok(page.getContent());
    // }

    // @PutMapping("/{requestedId}")
    // private ResponseEntity<Void> putCashCard(@PathVariable Long requestedId, @RequestBody UserLK cashCardUpdate, Principal principal){
    //     UserLK cashCard = findCashCard(requestedId, principal);
    //     if (cashCard != null){
    //         UserLK upCashCard = new UserLK(cashCard.id(), cashCardUpdate.amount(), principal.getName());

    //         lkRepository.save(upCashCard);

    //         return ResponseEntity.noContent().build();
    //     }
    //     return ResponseEntity.notFound().build();

    // }

    // private UserLK findCashCard(Long requestedId, Principal principal){

    //     return lkRepository.findByIdAndOwner(requestedId, principal.getName());

    // }

    // @DeleteMapping("/{id}")
    // private ResponseEntity<Void> deleteCashCard(@PathVariable Long id, Principal principal) {
    //     if (!lkRepository.existsByIdAndOwner(id, principal.getName())) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     lkRepository.deleteById(id);
        
    //     return ResponseEntity.noContent().build();
    // }

}