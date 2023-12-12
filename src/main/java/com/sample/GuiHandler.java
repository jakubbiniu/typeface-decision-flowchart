package com.sample;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GuiHandler {
    
   public static String showQuestion(String question, ArrayList<String> answersList){
	   Object[] possibleAnswers = answersList.toArray();
	   int index = JOptionPane.showOptionDialog(null,
    											 question,
                                                 "",
                                                 JOptionPane.DEFAULT_OPTION,
                                                 JOptionPane.INFORMATION_MESSAGE,
                                                 null,
                                                 possibleAnswers,
                                                 possibleAnswers[0]);
	   return answersList.get(index);
    }
    
   public static void showFinalChoice(String choice){
	   JOptionPane.showMessageDialog(null,choice);
   }
}