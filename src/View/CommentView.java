package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Controller.MainApplication;
import Controller.MetaData;
import Model.Comment;
import Model.Comments;

public class CommentView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CommentView(Comments comments, Comment comment) {
		setTitle("Comment");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dim.width / 2;
        int y = dim.height / 2;
        setBounds(0, 0, x, y);
        
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSaveAndClose = new JMenuItem("Save and Close");
		mntmSaveAndClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MetaData.save(comments.getIndex());
				System.exit(0);
			}
		});
		
		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				comments.setCommentNumber(MetaData.load());
				MainApplication.setLoad();
				synchronized (comment) {
					comment.notify();
				}
			}
		});
		mnFile.add(mntmLoad);
		mnFile.add(mntmSaveAndClose);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Comment : " + comment.id);
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JTextArea textArea = new JTextArea(comment.message);
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		
		setVisible(true);
	}

	public void close() {
		setVisible(false);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

}
