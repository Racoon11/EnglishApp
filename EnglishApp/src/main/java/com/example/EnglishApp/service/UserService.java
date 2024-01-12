package com.example.EnglishApp.service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.EnglishApp.dto.UserRegistrationDto;
import com.example.EnglishApp.User.User;

public interface UserService extends UserDetailsService {

	User save(UserRegistrationDto registrationDto);

	User findByEmail(String email);

	List<User> getAll(); 
}
 