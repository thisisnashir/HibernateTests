package com.map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Answer {
	
	@Id
	private int answerId;
	private String answer;
	
	@OneToOne
	@JoinColumn(name = "question_id")
	private Question question;
	public Answer(int answerId, String answer) {
		super();
		this.answerId = answerId;
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Answer [answerId=" + answerId + ", answer=" + answer + "]";
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	

}
