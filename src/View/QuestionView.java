package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Model.Comment;
import Model.Questions;

public class QuestionView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public QuestionView(Comment comment, int questionIndex) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Question");
		//setDefaultCloseOperation(JFrame.);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int h = 300;
        int x = dim.width/2;
        int y = 0;
        setBounds(x, y, x/2, h);

		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		JPanel panel = new JPanel(new BorderLayout(0,0));
		contentPane.add(panel);
		
		JTextArea textArea = new JTextArea(Questions.QUESTIONS[questionIndex]);
		textArea.setBackground(new Color(240, 240, 240));
		panel.add(textArea, BorderLayout.NORTH);
		ButtonGroup group = new ButtonGroup();
		for (int j = 0; j < Questions.ANSWERS[questionIndex].length; j++) {
			JRadioButton radioButton = new JRadioButton(Questions.ANSWERS[questionIndex][j]);
			radioButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					comment.setAnswer(Questions.QUESTIONS[questionIndex], radioButton.getText());
				}
			});
			group.add(radioButton);
			panel.add(radioButton, (j == 0) ? BorderLayout.WEST : (j == 1) ? BorderLayout.CENTER : BorderLayout.EAST);
			radioButton.setSelected(true);
			comment.setAnswer(Questions.QUESTIONS[questionIndex], radioButton.getText());
		}
		
		JButton button = new JButton((questionIndex == Questions.QUESTIONS.length - 1) ? "Next comment" : "Next question");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (comment) {
					QuestionView.this.setVisible(false);
					comment.notify();
				}
			}
		});
		contentPane.add(button);

		setVisible(true);
	}

	public void close() {
		setVisible(false);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
