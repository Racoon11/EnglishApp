package com.example.EnglishApp.baseOfWords;


import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.EnglishApp.User.User;

public interface baseService extends UserDetailsService {

	baseOfWords save(baseDto registrationDto);

	List<baseOfWords> getAll(); 
}
