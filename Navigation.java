package com.game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.IOException;



import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

public class Navigation {
	
	JFrame window;
	Container con;
	JPanel titleNamePanel, imagePanel, textPanel, choicePanel, topPanel;
	JLabel titleNameLabel, imageLabel;
	JButton choice1, choice2;
	JTextArea textArea, healthTextArea; 
	Clip clip;
	
	public static void main(String[] args) {
		new Navigation();
	}
	
	public Navigation() {
		// Setup window and panels
		window = new JFrame();
		window.setSize(1200, 900);
		window.setBackground(Color.black);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        con = window.getContentPane();
        
        // Create a panel for the title
 		titleNamePanel = new JPanel();
 		titleNamePanel.setBounds(300, 10, 600, 100);
 		titleNamePanel.setBackground(Color.black);
 		
 		// Create a label for the title text
		titleNameLabel = new JLabel("Capture The Diamond");
		titleNameLabel.setForeground(Color.white);
		titleNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 60));
		titleNamePanel.add(titleNameLabel);
		con.add(titleNamePanel);

		// Create the image panel
		imagePanel = new JPanel();
		imagePanel.setBackground(Color.white);
		imagePanel.setBounds(0, 0, 300, 300);
		con.add(imagePanel);
		
		// Set up image in a JLabel
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/diamond.png"));
		imageLabel = new JLabel(imageIcon);
		imagePanel.add(imageLabel);
		
		// Text Panel
		textPanel = new JPanel();
		textPanel.setBackground(Color.black);
		textPanel.setBounds(0, 300, 600, 600);
		con.add(textPanel);
		
		textArea = new JTextArea("You wake up in a dense forest... "
				+ "Your head aches, and you have no memory of how you got here. "
				+ "What is your next move??");
		textArea.setBounds(20, 20, 560, 560); // Set position and size
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);
		textArea.setFont(new Font("Calibri", Font.ITALIC, 30));
		textArea.setLineWrap(true);  // Wrap text automatically
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);  // Make the text area non-editable
		textPanel.add(textArea);
		
		// Top margin
		textArea.setMargin(new java.awt.Insets(200, 10, 10, 10)); // 100 pixels top, 10 left, 10 bottom, 10 right
		
		// Top Panel with 3 parallel boxes
		topPanel = new JPanel();
		topPanel.setBackground(Color.GRAY);
		topPanel.setBounds(600, 150, 600, 150);
		topPanel.setLayout(new GridLayout(1, 3)); // 1 row, 3 columns
		
		JPanel inventory1 = new JPanel();
		inventory1.setBackground(Color.black);
		inventory1.setBorder(new LineBorder(Color.white, 3));  // Adding a white border of thickness 3
		
		JPanel inventory2 = new JPanel();
		inventory2.setBackground(Color.black);
		inventory2.setBorder(new LineBorder(Color.white, 3));  // Adding a white border of thickness 3
		
		JPanel helthBox = new JPanel();
		helthBox.setBackground(Color.black);
		helthBox.setBorder(new LineBorder(Color.white, 3));  // Adding a white border of thickness 3
		healthTextArea = new JTextArea("HP: 3"); //This will call Player.health
		healthTextArea.setForeground(Color.white);
		healthTextArea.setBackground(Color.black);
		healthTextArea.setFont(new Font("Calibri", Font.PLAIN, 25));
		healthTextArea.setEditable(false);  // Make the text area non-editable
		healthTextArea.setMargin(new java.awt.Insets(50, 10, 10, 10)); // 50 pixels top, 10 left, 10 bottom, 10 right
		helthBox.add(healthTextArea);
		
		
		
		topPanel.add(inventory1);
		topPanel.add(inventory2);
		topPanel.add(helthBox);
		con.add(topPanel);
		
		// Choice Panel
		choicePanel = new JPanel();
		choicePanel.setBackground(Color.black);
		choicePanel.setBounds(600, 300, 600, 600);
		choicePanel.setLayout(new GridBagLayout());  // Use GridBagLayout for flexible positioning
		con.add(choicePanel);
		
		// GridBagConstraints for positioning the buttons
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0; 
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 0, 10, 0);  // Adjusted insets to move buttons up slightly
		
		// Create two buttons for choices
		choice1 = new JButton("Go deeper into the forest");
		choice1.setFont(new Font("Calibri", Font.PLAIN, 24));  // Increased font size for buttons
		choice1.setForeground(Color.black);
		choice1.setPreferredSize(new java.awt.Dimension(400, 60)); // Set preferred size for buttons
		choicePanel.add(choice1, gbc);  // First button with padding
		
		gbc.gridy++;  // Move to next row for second button
		choice2 = new JButton("Head towards the clearing");
		choice2.setForeground(Color.black);
		choice2.setFont(new Font("Calibri", Font.PLAIN, 24));  // Increased font size for buttons
		choice2.setPreferredSize(new java.awt.Dimension(400, 60)); // Set preferred size for buttons
		choicePanel.add(choice2, gbc);  // Second button with padding
		

		// Set window visible
		window.setVisible(true); // this needs to be last
		
		// Play background music
		playBackgroundMusic("/resources/backgroundMusic_test.wav"); // Replace with your own path
	}
	

	// Method to play background music
	public void playBackgroundMusic(String filePath) {
		try {
			// Load the audio file as a resource
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filePath));
			
			// Get a sound clip resource
			clip = AudioSystem.getClip();
			
			// Open the audio clip and load the audio data
			clip.open(audioInputStream);
			
			// Start playing the music in a loop
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
