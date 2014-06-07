//Andrey Melnikov
//Created 5.30.2014

//A GUI that displays a question and allows a user to answer it

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class QuestionPrompt extends GamePortion implements ActionListener
{
	private JTextArea outputArea;
	private JTextField inputArea;
	private JButton submitAnswer;
	private String questionType;
	
	ActionListener parentListener;
	
	public QuestionPrompt(String question, String questionType, ActionListener listener)
	{
		this.questionType = questionType;
		this.parentListener = listener;
		
		
		this.outputArea = new JTextArea(7, 30);
		this.outputArea.setBackground(Color.WHITE);
		this.outputArea.setEditable(false);
		Font outputFont = new Font("Arial", Font.PLAIN, 16 );
		this.outputArea.setFont(outputFont);
		
		this.outputArea.setText(question);
		
		this.inputArea = new JTextField(16);
		this.inputArea.setBackground(Color.WHITE);
		
		this.submitAnswer = new JButton("Submit Answer");
		this.submitAnswer.addActionListener(this);
		
		this.window = new JFrame("Question");
		this.window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.window.setLayout(new BorderLayout());
		
		
		this.window.add(outputArea, BorderLayout.NORTH);
		this.window.add(inputArea, BorderLayout.CENTER);
		this.window.add(submitAnswer, BorderLayout.SOUTH);
		
		this.window.setAlwaysOnTop(true);
		
		this.window.pack();
		this.centerOnScreen();		
	}
	
	public String getAnswer()
	{
		return this.inputArea.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource() == this.submitAnswer)
		{
			String answer = this.getAnswer();
			
			boolean validAnswer = false;
			
			//Debugging code
			if( answer.equalsIgnoreCase("abba") )
			{
				validAnswer = true;
			}
			//End debugging code
			
			else if(this.questionType.equals( TriviaItem.TYPE_MULTIPLE_CHOICE ) )
			{
				validAnswer = answer.equalsIgnoreCase("a") || 
						   			  answer.equalsIgnoreCase("b") ||
						   			  answer.equalsIgnoreCase("c") ||
						   			  answer.equalsIgnoreCase("d");
			} 
			else if(this.questionType.equals( TriviaItem.TYPE_TRUE_FALSE ) )
			{
				validAnswer = answer.equalsIgnoreCase("a") || 
			   			  answer.equalsIgnoreCase("b");				
			}
			
			if(validAnswer)
			{
				this.parentListener.actionPerformed(new ActionEvent(this, 0, "questionPromptAnswer"));
			}
			else
			{
				this.inputArea.setText("Invalid Answer.");
				this.inputArea.setSelectionStart(0);
				this.inputArea.requestFocus();
			}
		}
	}
	
	public void setQuestion(String question, String questionType)
	{
		this.questionType = questionType;
		this.outputArea.setText(question);
		this.window.pack();
		this.centerOnScreen();
		this.inputArea.requestFocus();
	}
	
	public void setVisible(boolean visible)
	{
		this.window.setVisible(visible);
	}
	
	public void clearScreen()
	{
		this.inputArea.setText("");
		this.outputArea.setText("");
	}
}

