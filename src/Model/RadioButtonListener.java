package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

public class RadioButtonListener implements ActionListener {

	private int questionIndex;
	private JRadioButton radioButton;
	private Comment comment;
	
	public RadioButtonListener(int questionIndex, JRadioButton radioButton, Comment comment) {
		this.questionIndex = questionIndex;
		this.radioButton = radioButton;
		this.comment = comment;
	}
	
	public void actionPerformed(ActionEvent e) {
		comment.setAnswer(Questions.QUESTIONS[questionIndex], radioButton.getText());
	}

}
