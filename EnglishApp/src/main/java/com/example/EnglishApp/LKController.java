package com.example.EnglishApp;

//import java.sql.Date;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import com.example.EnglishApp.LKRepository;

@RestController
@RequestMapping("/LK")
public class LKController {

    private LKRepository lkRepository;
    public LKController(LKRepository lkRepository) {
        this.lkRepository = lkRepository;
    }


    @GetMapping("/{nick}")
    public ResponseEntity<UserLK> findById(@PathVariable Long nick) {

        Optional<UserLK> userOpt = lkRepository.findById(nick);

        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}