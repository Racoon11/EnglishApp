package com.example.EnglishApp.words;

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
public class WordsServiceImpl implements WordsService {

	private LKRepository userRepository;


	public WordsServiceImpl(LKRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserLK save(WordsRegistrationDto registrationDto) {

		UserLK user = new UserLK(registrationDto.getFirstName(), registrationDto.getEmail());

		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserLK user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		Collection<Role> col;
		col = new ArrayList<>();
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				username, mapRolesToAuthorities(col));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public List<UserLK> getAll() {

		return userRepository.findAll();
	}

}
