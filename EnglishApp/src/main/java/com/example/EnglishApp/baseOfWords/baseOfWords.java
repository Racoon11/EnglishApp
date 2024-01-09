package com.example.EnglishApp.baseOfWords;


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
@Table(name = "baseOfWords", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class baseOfWords {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "wordEng")
	private String wordEng;

	@Column(name = "wordRus")
	private String wordRus;

	public baseOfWords() {

	}

	public baseOfWords(String wordEng, String wordRus) {

		this.wordEng = wordEng;
		this.wordRus = wordRus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
