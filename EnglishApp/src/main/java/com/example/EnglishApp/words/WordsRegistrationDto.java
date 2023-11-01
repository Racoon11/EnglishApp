package com.example.EnglishApp.words;

public class WordsRegistrationDto {

	private String email;
	private String wordEng;
	private String wordRus;
	private long whenToTrain;
	private int count;

	public WordsRegistrationDto() {

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


}
