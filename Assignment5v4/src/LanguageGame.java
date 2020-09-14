import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JProgressBar;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Button;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import java.awt.Canvas;
import javax.swing.JEditorPane;

public class LanguageGame extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField instructions;
	private JTextField userInputField;
	private JTextField translatedWordField;
	private JTextField translatedLanguageField;
	private JTextField toBeTranslatedLanguageField;
	private JTextField timerField;
	private JButton okayButton;
	
	private Timer myTimer;
	private JTextField timerLabel;
	private int count = 11;
	private int breakTime = 11;
	private int increment = -1;
	
	private String userWord, testLanguage, givenLanguage, givenWord, correctAnswer, displayRounds;
	private JButton chckbxSpanish;
	private JButton chckbxGerman;
	private JButton chckbxFrench;
	private JButton chckbxLatin;
	private double numberCorrect = 0, totalNumber = 0;
	private int score = 0, numberOfRounds = 1;
	private JTextField scoreField;
	private JTextField finalMessage;
	private JTextField numOfRoundsField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LanguageGame frame = new LanguageGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LanguageGame() throws IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1000, 1000);
		setUndecorated(true);
		// ( x , x , width , height , x , x )
        setShape(new RoundRectangle2D.Double(000,0, 1000,500, 600,600));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        setLocationRelativeTo(null);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(176, 224, 230));
		
		TestWordGenerator.initializeArrayLists();
		Library.setEngLanguage();
		
		JButton okayButton = new JButton("Okay");
		okayButton.addActionListener(new OkayButtonListener());
		okayButton.setBounds(756, 291, 70, 23);
		okayButton.setVisible(false);
		okayButton.setForeground(new Color(34,139,34));
		okayButton.setBackground(new Color(255, 255, 255));
		okayButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		okayButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(okayButton);
		
		JButton beginButton = new JButton("Begin");
		beginButton.setVisible(true);
		beginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Library.getOtherLang().size() != 0) {
					userInputField.setEditable(true);
					userInputField.setBackground(SystemColor.info);
					userInputField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
					beginButton.setVisible(false);
					chckbxSpanish.setVisible(false);
					chckbxGerman.setVisible(false);
					chckbxFrench.setVisible(false);
					chckbxLatin.setVisible(false);
					okayButton.setVisible(true);
					numOfRoundsField.setVisible(true);
					numOfRoundsField.setText(Integer.toString(numberOfRounds) + "/10");
					myTimer = new Timer(1000, new TimerButtonListener());
					myTimer.start();
					TestWordGenerator.setTestWords();
					initializeTestVariables();
					translatedWordField.setText(givenWord);
					translatedLanguageField.setText(givenLanguage);
					toBeTranslatedLanguageField.setText(testLanguage);
					instructions.setText("Type the translation into the field below.");
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "You need to choose at least one language.");
				}
			}
		});
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setVisible(false);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setFont(new Font("High Tower Text", Font.ITALIC, 18));
		lblScore.setBounds(448, 219, 51, 20);
		contentPane.add(lblScore);
		
		scoreField = new JTextField();
		scoreField.setBorder(null);
		scoreField.setVisible(false);
		scoreField.setHorizontalAlignment(SwingConstants.CENTER);
		scoreField.setText("50");
		scoreField.setFont(new Font("High Tower Text", Font.PLAIN, 44));
		scoreField.setBounds(432, 253, 86, 59);
		contentPane.add(scoreField);
		scoreField.setColumns(10);
		scoreField.setBackground(new Color(176, 224, 230));
		
		beginButton.setForeground(new Color(34, 139, 34));
		beginButton.setBackground(new Color(255, 255, 255));
		beginButton.setFont(new Font("Felix Titling", Font.PLAIN, 12));
		beginButton.setBounds(413, 437, 160, 47);
		contentPane.add(beginButton);
		beginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		JLabel gameTitle = new JLabel("Language GUI Game");
		gameTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		gameTitle.setForeground(new Color(0, 128, 0));
		gameTitle.setFont(new Font("High Tower Text", Font.ITALIC, 32));
		gameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gameTitle.setBounds(331, 45, 336, 35);
		contentPane.add(gameTitle);
		
		JButton btnQuit = new JButton("");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				myTimer.stop();
				}catch(Exception NullPointerException) {
					
				}
				dispose();
			}
		});
		btnQuit.setIcon(new ImageIcon(LanguageGame.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-error@2x.png")));
		btnQuit.setForeground(new Color(220, 20, 60));
		btnQuit.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnQuit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnQuit.setBounds(775, 46, 51, 47);
		contentPane.add(btnQuit);
		
		instructions = new JTextField();
		instructions.setBorder(null);
		instructions.setHorizontalAlignment(SwingConstants.CENTER);
		instructions.setFont(new Font("High Tower Text", Font.PLAIN, 16));
		instructions.setText("Choose the languages you'd like to test.");
		instructions.setBounds(199, 172, 378, 20);
		instructions.setEditable(false);
		instructions.setBackground(new Color(176, 224, 230));
		contentPane.add(instructions);
		instructions.setColumns(10);
		
		userInputField = new JTextField();
		userInputField.addKeyListener(new EnterButtonListener());
		userInputField.setText("");
		userInputField.setBorder(null);
		userInputField.setHorizontalAlignment(SwingConstants.CENTER);
		userInputField.setFont(new Font("High Tower Text", Font.PLAIN, 16));
		userInputField.setColumns(10);
		userInputField.setBounds(486, 292, 235, 20);
		userInputField.setEditable(false);
		//userInputField.setBackground(SystemColor.info);
		userInputField.setBackground(new Color(176, 224, 230));
		contentPane.add(userInputField);
		
		translatedWordField = new JTextField();
		translatedWordField.setBorder(null);
		translatedWordField.setText("");
		translatedWordField.setHorizontalAlignment(SwingConstants.CENTER);
		translatedWordField.setFont(new Font("High Tower Text", Font.PLAIN, 16));
		translatedWordField.setColumns(10);
		translatedWordField.setBounds(199, 292, 235, 20);
		translatedWordField.setEditable(false);
		translatedWordField.setBackground(new Color(176, 224, 230));
		contentPane.add(translatedWordField);
		
		translatedLanguageField = new JTextField();
		translatedLanguageField.setBorder(null);
		translatedLanguageField.setText("");
		translatedLanguageField.setHorizontalAlignment(SwingConstants.CENTER);
		translatedLanguageField.setFont(new Font("High Tower Text", Font.PLAIN, 16));
		translatedLanguageField.setColumns(10);
		translatedLanguageField.setBounds(199, 253, 235, 20);
		translatedLanguageField.setEditable(false);
		translatedLanguageField.setBackground(new Color(176, 224, 230));
		contentPane.add(translatedLanguageField);
		
		toBeTranslatedLanguageField = new JTextField();
		toBeTranslatedLanguageField.setBorder(null);
		toBeTranslatedLanguageField.setText("");
		toBeTranslatedLanguageField.setHorizontalAlignment(SwingConstants.CENTER);
		toBeTranslatedLanguageField.setFont(new Font("High Tower Text", Font.PLAIN, 16));
		toBeTranslatedLanguageField.setColumns(10);
		toBeTranslatedLanguageField.setBounds(484, 253, 237, 20);
		toBeTranslatedLanguageField.setEditable(false);
		toBeTranslatedLanguageField.setBackground(new Color(176, 224, 230));
		contentPane.add(toBeTranslatedLanguageField);
		
		timerField = new JTextField();
		timerField.setBorder(null);
		timerField.setFont(new Font("High Tower Text", Font.PLAIN, 16));
		timerField.setHorizontalAlignment(SwingConstants.CENTER);
		timerField.setBounds(727, 172, 99, 20);
		timerField.setEditable(false);
		timerField.setBackground(new Color(176, 224, 230));
		contentPane.add(timerField);
		timerField.setColumns(10);
		
		timerLabel = new JTextField();
		timerLabel.setBorder(null);
		timerLabel.setFont(new Font("High Tower Text", Font.PLAIN, 14));
		timerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timerLabel.setText("Time Remaining:");
		timerLabel.setBounds(619, 172, 101, 20);
		timerLabel.setEditable(false);
		timerLabel.setBackground(new Color(176, 224, 230));
		contentPane.add(timerLabel);
		timerLabel.setColumns(10);
		
		chckbxSpanish = new JButton("Spanish");
		chckbxSpanish.setFont(new Font("Ravie", Font.PLAIN, 10));
		chckbxSpanish.addActionListener(new SpanishButtonListener());
		chckbxSpanish.setBounds(199, 353, 97, 23);
		chckbxSpanish.setForeground(new Color(34,139,34));
		chckbxSpanish.setBackground(new Color(255, 255, 255));
		chckbxSpanish.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		chckbxSpanish.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(chckbxSpanish);
		
		chckbxGerman = new JButton("German");
		chckbxGerman.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxGerman.setFont(new Font("Old English Text MT", Font.PLAIN, 16));
		chckbxGerman.setForeground(new Color(34,139,34));
		chckbxGerman.setBackground(new Color(255, 255, 255));
		chckbxGerman.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		chckbxGerman.addActionListener(new GermanButtonListener());
		chckbxGerman.setBounds(370, 353, 97, 23);
		
		chckbxGerman.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(chckbxGerman);
		
		chckbxFrench = new JButton("French");
		chckbxFrench.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxFrench.setFont(new Font("Script MT Bold", Font.PLAIN, 16));
		chckbxFrench.setForeground(new Color(34,139,34));
		chckbxFrench.setBackground(new Color(255, 255, 255));
		chckbxFrench.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		chckbxFrench.addActionListener(new FrenchButtonListener());
		chckbxFrench.setBounds(548, 353, 97, 23);
		chckbxFrench.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(chckbxFrench);
		
		chckbxLatin = new JButton("Latin");
		chckbxLatin.setFont(new Font("Monotype Corsiva", Font.PLAIN, 17));
		chckbxLatin.addActionListener(new LatinButtonListener());
		chckbxLatin.setBounds(716, 353, 97, 23);
		chckbxLatin.setForeground(new Color(34,139,34));
		chckbxLatin.setBackground(new Color(255, 255, 255));
		chckbxLatin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		chckbxLatin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.add(chckbxLatin);
		
		finalMessage = new JTextField();
		finalMessage.setBorder(null);
		finalMessage.setVisible(false);
		finalMessage.setFont(new Font("High Tower Text", Font.PLAIN, 14));
		finalMessage.setHorizontalAlignment(SwingConstants.CENTER);
		finalMessage.setText("Congradulations!");
		finalMessage.setBounds(338, 323, 286, 23);
		finalMessage.setBackground(new Color(176, 224, 230));
		contentPane.add(finalMessage);
		finalMessage.setColumns(10);
		
		numOfRoundsField = new JTextField();
		numOfRoundsField.setBackground(new Color(176, 224, 230));
		numOfRoundsField.setDisabledTextColor(new Color(176, 224, 230));
		numOfRoundsField.setVisible(false);
		numOfRoundsField.setCaretColor(new Color(176, 224, 230));
		numOfRoundsField.setBorder(null);
		numOfRoundsField.setEditable(false);
		numOfRoundsField.setFont(new Font("High Tower Text", Font.PLAIN, 16));
		numOfRoundsField.setHorizontalAlignment(SwingConstants.CENTER);
		numOfRoundsField.setForeground(new Color(0, 0, 0));
		numOfRoundsField.setBounds(448, 387, 86, 39);
		contentPane.add(numOfRoundsField);
		numOfRoundsField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Matt\\Pictures\\Charles Darwin picture.jpg"));
		btnNewButton.setBounds(49, 152, 171, 190);
		contentPane.add(btnNewButton);
		
		if (totalNumber == 10) {
			okayButton.setVisible(false);
		}
		
		if (count < 0) {
			okayButton.setEnabled(false);
		}
		else {
			okayButton.setEnabled(true);
		}
		
	}

	class TimerButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			count += increment;
			timerField.setForeground(Color.black);
			timerField.setFont(new Font("High Tower Text", Font.PLAIN, 16));
			if (((count > -7) && count < -1) || (count > 0 && count <= 5)) {
				timerField.setForeground(Color.RED);
				timerField.setFont(new Font("High Tower Text", Font.BOLD, 16));
			}
			else {
				timerField.setForeground(Color.black);
				timerField.setFont(new Font("High Tower Text", Font.PLAIN, 16));
			}
			
			if (count == 0){
				timerField.setForeground(Color.black);
				count = -6;
				increment = 1;
				userInputField.setEditable(false);
				instructions.setText("Time's up! The correct answer is " + correctAnswer + ".");
				totalNumber++;
				calculateScore();
				numberOfRounds++;
				numOfRoundsField.setText(Integer.toString(numberOfRounds) + "/10");
			}
			
			if (count == -1) {
				count = 10;
				increment = -1;
				TestWordGenerator.setTestWords();
				initializeTestVariables();
				instructions.setText("Translate the " + givenLanguage + " word to " + testLanguage + ".");
				translatedWordField.setText(givenWord);
				userInputField.setEditable(true);
				translatedLanguageField.setText(givenLanguage);
				toBeTranslatedLanguageField.setText(testLanguage);
				
			}
			
			if (count < 0) {
				timerLabel.setText("Break Time:");
				timerField.setText((Integer.toString((count+1)*-1)));
			}
			
			if (count >= 0) {
				timerLabel.setText("Time Remaining:");
				timerField.setText(Integer.toString(count));
			}
			
			if (count == 20) {
				myTimer.stop();
				dispose();
			}
			
			if (totalNumber == 10) {
				count = 30;
				userInputField.setVisible(false);
				instructions.setVisible(false);
				translatedWordField.setVisible(false);
				toBeTranslatedLanguageField.setVisible(false);
				translatedLanguageField.setVisible(false);
				timerField.setVisible(false);
				//userInputField.setVisible(false);
				contentPane.remove(userInputField);
				timerLabel.setVisible(false);
				numOfRoundsField.setText("10/10");
				scoreField.setText(Integer.toString(score) + "%");;
				if (score < 60) {
					finalMessage.setText("You will need to keep working on your languages.");
				}
				else if ((score > 69) && (score < 100)) {
					finalMessage.setText("Great job! You passed!");
				}
				else {
					finalMessage.setForeground((new Color(0,128,0)));
					finalMessage.setText("Perfect score! Great job!");
				}
				scoreField.setVisible(true);
				finalMessage.setVisible(true);
			}
			
		}
		
	} // end TimerButtonListener class

	class EnterButtonListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
				userWord = userInputField.getText();
				userInputField.setText("");
				if (count > 0) {
					if (userWord.equalsIgnoreCase(correctAnswer)) {
						count = -6;
						increment = 1;
						userInputField.setEditable(false);
						instructions.setText("Correct! The answer " + correctAnswer + ".");
						numberCorrect++;
						totalNumber++;
						calculateScore();
						numberOfRounds++;
						numOfRoundsField.setText(Integer.toString(numberOfRounds) + "/10");

					}
					else {
						count = -6;
						increment = 1;
						userInputField.setEditable(false);
						instructions.setText("Incorrect! The answer " + correctAnswer + ".");
						totalNumber++;
						calculateScore();
					}
				}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}

	class SpanishButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Library.getLangFile(1);
				chckbxSpanish.setEnabled(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	class GermanButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Library.getLangFile(2);
				chckbxGerman.setEnabled(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	class FrenchButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Library.getLangFile(3);
				chckbxFrench.setEnabled(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	class LatinButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				Library.getLangFile(4);
				chckbxLatin.setEnabled(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	class OkayButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			userWord = userInputField.getText();
			userInputField.setText("");
			if (count > 0) {
				if (userWord.equalsIgnoreCase(correctAnswer)) {
					count = -16;
					increment = 1;
					userInputField.setEditable(false);
					instructions.setText("Correct! The answer " + correctAnswer + ".");
					numberCorrect++;
					totalNumber++;
					calculateScore();
					numberOfRounds++;
					numOfRoundsField.setText(Integer.toString(numberOfRounds) + "/10");
				}
				else {
					count = -16;
					increment = 1;
					userInputField.setEditable(false);
					instructions.setText("Incorrect! The answer " + correctAnswer + ".");
					totalNumber++;
					calculateScore();
				}
			}
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
	
	public void initializeTestVariables() {
		
		testLanguage = TestWordGenerator.getTestLanguage();
		givenLanguage = TestWordGenerator.getProvidedLanguage();
		givenWord = TestWordGenerator.getTestWord();
		correctAnswer = TestWordGenerator.getCorrectAnswer();
		
	}
	
	public void calculateScore() {
		if (numberCorrect > 0) {
			score = (int)((numberCorrect/totalNumber) * 100);
		}
		else {
			score = 0;
		}
	}
}



