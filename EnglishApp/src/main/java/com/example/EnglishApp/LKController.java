package com.example.EnglishApp;

import java.sql.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/LK")
public class LKController {

    @GetMapping("/{nick}")
    public ResponseEntity<UserLK> findById(@PathVariable String nick) {
        if (nick.equals("Name")) {

            // long ms = System.currentTimeMillis();
            // Date date = new Date(ms);

            UserLK cashCard = new UserLK("Name", 1000, 3);
            return ResponseEntity.ok(cashCard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}