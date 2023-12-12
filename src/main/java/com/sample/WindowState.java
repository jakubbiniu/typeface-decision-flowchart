package com.sample;

public class WindowState {
	
	private String questionText;
	
	private String answer = "";
	
	private String[] possibleAnswers;

    public WindowState(String text) {
        this.questionText = text;
    }
    public WindowState(String text, String[] answers) {
    	this.questionText = text;
        this.possibleAnswers = answers;
    }

    public String getText() {
        return questionText;
    }
    
    public void setText(String text) {
        this.questionText = text;
    }
    
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    
    public String[] getPossibleAnswers() {
        return possibleAnswers;
    }
    public void setPossibleAnswers(String[] answers) {
        this.possibleAnswers = answers;
    }
}