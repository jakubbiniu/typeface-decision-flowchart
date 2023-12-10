package com.sample;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class DroolsTest extends JFrame {

    private JButton answerButton1;
    private JButton answerButton2;
    private JButton answerButton3;
    private JButton answerButton4;
    private JLabel questionLabel;
    private KieSession kieSession;
    
    private WindowState windowState;


    public DroolsTest() {
        setTitle("Choose Your Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        answerButton1 = new JButton("");
        answerButton2 = new JButton("");
        answerButton3 = new JButton("");
        answerButton4 = new JButton("");
        questionLabel = new JLabel("");

        Font font = questionLabel.getFont();
        questionLabel.setFont(new Font(font.getName(), Font.PLAIN, 18));
        
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setVerticalAlignment(SwingConstants.CENTER); 
        questionLabel.setVerticalTextPosition(SwingConstants.CENTER); 
        questionLabel.setOpaque(true); 
        questionLabel.setBackground(Color.WHITE);
        questionLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JPanel questionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        questionPanel.add(questionLabel);
        panel.add(questionPanel, gbc);

        
        gbc.gridy++;
        panel.add(answerButton1, gbc);
        gbc.gridy++;
        panel.add(answerButton2, gbc);
        gbc.gridy++;
        panel.add(answerButton3, gbc);
        gbc.gridy++;
        panel.add(answerButton4, gbc);
        add(panel, BorderLayout.CENTER);
        
        initializeDrools();
        updateWindowState();
        
        answerButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	clickButton(windowState.getButtonText1());
            }
        });
        answerButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	clickButton(windowState.getButtonText2());
            }
        });
        answerButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	clickButton(windowState.getButtonText3());
            }
        });
        answerButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	clickButton(windowState.getButtonText4());
            }
        });
    }

    private void initializeDrools() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kieSession = kContainer.newKieSession("ksession-rules");
        kieSession.fireAllRules();
    }
    
    private void updateWindowState() {
    	windowState = (WindowState) kieSession.getObjects(new ClassObjectFilter(WindowState.class))
        	    .stream()
        	    .findFirst()
        	    .orElse(null);
        String result = "empty";
        if (windowState != null) {
        	result = windowState.getText();
        }
        questionLabel.setText("<html>" + result + "</html>");
        
        String button1Description = (windowState != null) ? windowState.getButtonText1() : "Empty button text";
        answerButton1.setText(button1Description);
        if(windowState.getButtonText1().equals(""))
        	answerButton1.setVisible(false);
        
        String button2Description = (windowState != null) ? windowState.getButtonText2() : "Empty button text";
        answerButton2.setText(button2Description);
        if(windowState.getButtonText2().equals(""))
        	answerButton2.setVisible(false);
        
        String button3Description = (windowState != null) ? windowState.getButtonText3() : "Empty button text";
        answerButton3.setText(button3Description);
        if(windowState.getButtonText3().equals(""))
        	answerButton3.setVisible(false);
        
        String button4Description = (windowState != null) ? windowState.getButtonText4() : "Empty button text";
        answerButton4.setText(button4Description);
        if(windowState.getButtonText4().equals(""))
        	answerButton4.setVisible(false);
    }

    private void clickButton(String setAnswerString) {
        windowState.setAnswer(setAnswerString);
        kieSession.update(kieSession.getFactHandle(windowState), windowState);
        kieSession.fireAllRules();
        updateWindowState();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DroolsTest().setVisible(true);
            }
        });
    }
}