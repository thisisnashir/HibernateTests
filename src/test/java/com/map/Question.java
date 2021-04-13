package com.map;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Question {
	@Id
	@Column(name = "question_id")
	private int questionId;
	private String question;

	@OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
	private List<Answer> answers;

	public Question(int questionId, String question, List<Answer> answers) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.answers = answers;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		String str = "";
		for (Answer each : this.answers) {
			str += each.getAnswer() + " ";
		}
		return "Question [questionId=" + questionId + ", question=" + question + ", answer=" + str + "]";
	}

}
