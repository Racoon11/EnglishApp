package com.example.EnglishApp;

import java.net.URI;
import java.security.Principal;
import java.util.List;
//import java.sql.Date;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/LK")
public class LKController {

    //private static final String K = null;
    private LKRepository lkRepository;

    public LKController(LKRepository lkRepository) {
        this.lkRepository = lkRepository;
    }


    @GetMapping("/{nick}")
    public ResponseEntity<UserLK> findById(@PathVariable Long nick, Principal principal) {

        Optional<UserLK> userOpt = Optional.ofNullable(lkRepository.findByIdAndOwner(nick, principal.getName()));

        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity<Void> createCashCard(@RequestBody UserLK newCashCardRequest, UriComponentsBuilder ucb, Principal principal){
        
        UserLK cashCardWithOwner = new UserLK(null, newCashCardRequest.amount(), principal.getName());
        UserLK savedCashCard = lkRepository.save(cashCardWithOwner);
        //id is provided by a DateBase
        URI locationOfNewCashCard = ucb
                .path("LK/{id}")
                .buildAndExpand(savedCashCard.id())
                .toUri();



        return ResponseEntity.created(locationOfNewCashCard).build();

    }

    @GetMapping
    public ResponseEntity<List<UserLK>> findAll(Pageable pageable, Principal principal){
        Page<UserLK> page = lkRepository.findByOwner(principal.getName(),
            PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "amount"))
        ));

        return ResponseEntity.ok(page.getContent());
    }

}