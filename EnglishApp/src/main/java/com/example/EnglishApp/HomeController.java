package com.example.EnglishApp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}

	// @GetMapping("/LK/{nick}")
    // public ResponseEntity<UserLK> findById(@PathVariable String nick, Principal principal, @AuthenticationPrincipal UserDetails userDetails){

	// 	System.out.println(nick);
	// 	System.out.println(principal.getName());
	// 	System.out.println(userDetails.getAuthorities());
		
    //     // UserLK user = lkRepository.findByNick(nick);
    //     // if (user != null) {
    //     //     return ResponseEntity.ok(user);
    //     // }
    //     return ResponseEntity.ok().build();
        

    // }

}