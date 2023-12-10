package com.sample;

public class WindowState {
	
	private String questionText;
	
	private String buttonText1 = "";
	
	private String buttonText2 = "";
	
	private String buttonText3 = "";

	private String buttonText4 = "";
	
	private String answer = "";

    public WindowState(String text) {
        this.questionText = text;
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
    
    public String getButtonText1() {
        return buttonText1;
    }
    public void setButtonText1(String buttonText1) {
        this.buttonText1 = buttonText1;
    }
    
    public String getButtonText2() {
        return buttonText2;
    }
    public void setButtonText2(String buttonText2) {
        this.buttonText2 = buttonText2;
    }
    
    public String getButtonText3() {
        return buttonText3;
    }
    public void setButtonText3(String buttonText3) {
        this.buttonText3 = buttonText3;
    }
    
    public String getButtonText4() {
        return buttonText4;
    }
    public void setButtonText4(String buttonText4) {
        this.buttonText4 = buttonText4;
    }
}