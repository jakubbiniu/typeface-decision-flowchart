package com.sample;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import org.kie.api.KieServices;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


public class DroolsTest extends JFrame {
	
    private JLabel questionLabel = new JLabel("");
    private KieSession kieSession;
    
    private WindowState windowState;


    public DroolsTest() {
        setTitle("Choose Your Game");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        initializeDrools();
        updateWindowState(gbc, panel);
        
        add(panel, BorderLayout.CENTER);
    }

    private void initializeDrools() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        kieSession = kContainer.newKieSession("ksession-rules");
        kieSession.fireAllRules();
    }
    
    private void updateWindowState(GridBagConstraints gbc, JPanel panel) {
    	windowState = (WindowState) kieSession.getObjects(new ClassObjectFilter(WindowState.class))
        	    .stream()
        	    .findFirst()
        	    .orElse(null);
        String result = "empty";
        if (windowState != null) {
        	result = windowState.getText();
        }
        questionLabel.setText("<html>" + result + "</html>");
        
        //clear all buttons
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JButton) {
                panel.remove(component);
            }
        }
        panel.revalidate();
        panel.repaint();
        
        //render new buttons
        if(Objects.nonNull(windowState.getPossibleAnswers())) {
        	gbc.gridy = 1;
            for(String answer : windowState.getPossibleAnswers()) {
            	System.out.println(answer);
            	if(answer == "") continue;
            	JButton button = new JButton(answer);
            	gbc.gridy++;
                panel.add(button, gbc);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                    	clickButton(answer, gbc, panel);
                    }
                });    
            }
        }
    }

    private void clickButton(String setAnswerString, GridBagConstraints gbc, JPanel panel) {
        windowState.setAnswer(setAnswerString);
        kieSession.update(kieSession.getFactHandle(windowState), windowState);
        kieSession.fireAllRules();
        updateWindowState(gbc, panel);
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