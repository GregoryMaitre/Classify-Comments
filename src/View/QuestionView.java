package View;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Model.Comment;
import Model.Questions;
import Model.RadioButtonListener;

public class QuestionView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Create the frame.
	 * 
	 * @param commentView
	 */
	public QuestionView(Comment comment, CommentView commentView) {

		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new GridLayout(Questions.QUESTIONS.length + 2, 1, 0, 0));

		int i = 0;
		while (i < Questions.QUESTIONS.length) {
			JPanel panel = new JPanel(new GridLayout(1, 3));
			add(panel);

			JTextArea textArea = new JTextArea(Questions.QUESTIONS[i]);
			textArea.setBackground(new Color(240, 240, 240));
			panel.add(textArea);
			ButtonGroup group = new ButtonGroup();
			for (int j = 0; j < 3; j++) {
				if (j < Questions.ANSWERS[i].length) {
					JRadioButton radioButton = new JRadioButton(Questions.ANSWERS[i][j]);
					radioButton.addActionListener(new RadioButtonListener(i, radioButton, comment));
					group.add(radioButton);
					panel.add(radioButton);
					radioButton.setSelected(true);
					comment.setAnswer(Questions.QUESTIONS[i], radioButton.getText());
				} else {
					panel.add(new JPanel());
				}
			}
			i++;
		}

		// Aspect/Opinion
		JPanel panel = new JPanel(new GridLayout(1, 3));
		add(panel);

		JTextArea textArea = new JTextArea("Entrez l'aspect et l'opinion");
		textArea.setBackground(new Color(240, 240, 240));
		panel.add(textArea);
		JTextArea aspect = new JTextArea();
		aspect.setToolTipText("Aspect");
		JTextArea opinion = new JTextArea();
		opinion.setToolTipText("Opinion");
		panel.add(aspect);
		panel.add(opinion);

		Border border = BorderFactory.createLineBorder(Color.BLACK);
		aspect.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		opinion.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		JButton button = new JButton("Next Comment");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comment.setOpinion(aspect.getText(), opinion.getText());
				commentView.saveComment();
				commentView.nextComment();
			}
		});
		add(button);
	}
}
