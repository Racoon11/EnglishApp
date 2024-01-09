package com.example.EnglishApp.baseOfWords;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.EnglishApp.dto.UserRegistrationDto;
import com.example.EnglishApp.User.Role;
import com.example.EnglishApp.User.User;
import com.example.EnglishApp.repository.UserRepository;

@Service
public class baseServiceImpl implements baseService {

	private baseOfWordsRepository userRepository;


	public baseServiceImpl(baseOfWordsRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public baseOfWords save(baseDto registrationDto) {


		baseOfWords user = new baseOfWords(
				registrationDto.getWordEng(), registrationDto.getWordRus());

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		baseOfWords user = userRepository.findByWordEng(username);
		Collection<Role> col;
		col = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(user.getWordEng(),
				username, mapRolesToAuthorities(col));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<baseOfWords> getAll() {

		return (List<baseOfWords>) userRepository.findByWordEng("s");
	}

}
