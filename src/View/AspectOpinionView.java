package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Model.Comment;

public class AspectOpinionView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AspectOpinionView(Comment comment) {
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
		
		JTextArea textArea = new JTextArea("Entrez l'aspect et l'opinion");
		textArea.setBackground(new Color(240, 240, 240));
		panel.add(textArea, BorderLayout.NORTH);
		JTextArea aspect = new JTextArea();
		aspect.setToolTipText("Aspect");
		JTextArea opinion = new JTextArea();
		opinion.setToolTipText("Opinion");
		JPanel panel2 = new JPanel(new GridLayout(1, 2, 0, 0));
		panel2.add(aspect,  BorderLayout.WEST);
		panel2.add(opinion,  BorderLayout.EAST);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		aspect.setBorder(BorderFactory.createCompoundBorder(border, 
		            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		opinion.setBorder(BorderFactory.createCompoundBorder(border, 
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		panel.add(panel2, BorderLayout.CENTER);
		
		JButton button = new JButton("Next question");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				synchronized (comment) {
					AspectOpinionView.this.setVisible(false);
					comment.setOpinion(aspect.getText(), opinion.getText());
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
