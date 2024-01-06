// package com.example.EnglishApp.words;

// import java.util.ArrayList;
// import java.util.Date;
 
// import org.springframework.data.annotation.Id;

// public record UserLK (@Id Long id, String nick, String level, int learntWords){
    
// }


package com.example.EnglishApp.words;
 
import java.util.Collection;
import java.util.Date;

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
@Table(name = "user_lk", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class UserLK {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "wordEng")
	private String wordEng;

	@Column(name = "wordRus")
	private String wordRus;

	@Column(name = "whenToTrain")
	private long whenToTrain;

	@Column(name = "count")
	private int count;

	@Column(name = "mainID")
	private int mainID;


	private String email;

	public UserLK() {

	}

	public UserLK(String email, String wordEng, String wordRus) {
		this.email = email;
		this.wordEng = wordEng;
		this.wordRus = wordRus;
		this.count = 0;
		this.whenToTrain = (new Date()).getTime();
		this.mainID = -1;
	}
	public UserLK(String email, String wordEng, String wordRus, int mainID) {
		this.email = email;
		this.wordEng = wordEng;
		this.wordRus = wordRus;
		this.count = 0;
		this.whenToTrain = (new Date()).getTime();
		this.mainID = mainID;
	}

	public UserLK(Long id, String email, String wordEng, String wordRus, long whenToTrain, int count, int mainID) {
		this.id = id;
		this.email = email;
		this.wordEng = wordEng;
		this.wordRus = wordRus;
		this.whenToTrain = whenToTrain;
		this.count = count;
		this.whenToTrain = (new Date()).getTime();
		this.mainID = mainID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getWordEng() {
		return wordEng;
	}

	public void setWordEng(String wordEng) {
		this.wordEng = wordEng;
	}

	public String getWordRus() {
		return wordRus;
	}

	public void setWordRus(String wordEng) {
		this.wordRus = wordEng;
	}

	public long getWhenToTrain() {
		return whenToTrain;
	}

	public void setWhenToTrain(long whenToTrain) {
		this.whenToTrain = whenToTrain;
	}
	public int getCounty() {
		return count;
	}

	public void setCount(int c) {
		this.count = c;
	}

	public int getMainID() {
		return mainID;
	}

	public void setMainID(int i) {
		this.mainID = i;
	}

}


