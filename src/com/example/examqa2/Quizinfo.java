package com.example.examqa2;

public class Quizinfo {

	private Integer questionId;
	private String question;
	private String choice;
	private String answers;
	
	public Integer getQuestionNumber() {
		return questionId;
	}
	public void setQuestionNumber(Integer questionNumber) {
		this.questionId = questionNumber;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getChoices() {
		return choice;
	}
	public void setChoices(String choices){
         this.choice=choices;
	}
	public String getAnswer() {
		return answers;
	}
	public void setAnswer(String answer) {
		this.answers = answer;
	}
	

}
