// package com.example.EnglishApp.words;

// import java.util.ArrayList;
// import java.util.Date;
 
// import org.springframework.data.annotation.Id;

// public record UserLK (@Id Long id, String nick, String level, int learntWords){
    
// }


package com.example.EnglishApp.words;
 
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "user_lk", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserLK {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nick")
	private String nick;


	private String email;

	public UserLK() {

	}

	public UserLK(String firstName, String email) {

		this.nick = firstName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String firstName) {
		this.nick = firstName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}


