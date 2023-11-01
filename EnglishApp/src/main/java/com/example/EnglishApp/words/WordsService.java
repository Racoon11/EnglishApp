package com.example.EnglishApp.words;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.EnglishApp.User.User;

public interface WordsService extends UserDetailsService {

	UserLK save(WordsRegistrationDto registrationDto);

	List<UserLK> getAll(); 
}
