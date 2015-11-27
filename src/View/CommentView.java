package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Common.Constants;
import Controller.MetaData;
import Model.Comment;
import Model.Comments;
import Model.Opinion;
import Model.OpinionDetails;
import Model.ResultOpinionDetailExtractor;
import Model.ResultOpinionExtractor;
import Model.ResultsClassifyComments;

public class CommentView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Comments comments;
	private Comment currentComment = null;

	private JPanel questionPanel = null;
	private JPanel contentPane;
	private JLabel commentLabel;
	private JTextArea commentContent;
	private ResultOpinionExtractor resultOpinionExtractor;
	private ResultOpinionDetailExtractor resultOpinionDetailExtractor;
	private ResultsClassifyComments classify;

	/**
	 * Create the frame.
	 * 
	 * @param resultOpinionExtractor
	 * @param resultOpinionDetailExtractor
	 * @param classify
	 */
	public CommentView(Comments comments, ResultOpinionExtractor resultOpinionExtractor,
			ResultOpinionDetailExtractor resultOpinionDetailExtractor, ResultsClassifyComments classify) {

		this.comments = comments;
		this.resultOpinionExtractor = resultOpinionExtractor;
		this.resultOpinionDetailExtractor = resultOpinionDetailExtractor;
		this.classify = classify;

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setTitle("Comment");
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
				CommentView.this.nextComment();
			}
		});
		mnFile.add(mntmLoad);
		mnFile.add(mntmSaveAndClose);
		contentPane = new JPanel(new GridLayout(1, 2));
		setContentPane(contentPane);

		JPanel commentPanel = new JPanel();
		commentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(commentPanel);

		commentPanel.setLayout(new BorderLayout(0, 0));

		commentLabel = new JLabel();
		commentPanel.add(commentLabel, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		commentPanel.add(scrollPane, BorderLayout.CENTER);

		commentContent = new JTextArea();
		commentContent.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		commentContent.setLineWrap(true);
		commentContent.setEditable(false);
		scrollPane.setViewportView(commentContent);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(CommentView.this, "Do you want to save your progression?", "Closing",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					MetaData.save(comments.getIndex());
					System.exit(0);
				}
			}
		});

		nextComment();
		// setUndecorated(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setResizable(false);
		setVisible(true);
	}

	private String getLabel(Comment comment) {
		return "<html>Article : " + comment.title + "<br/>Comment : " + comment.id + "<br/>Controversy : "
				+ comment.label + "<br/>Pro : " + comment.label_pro + "<br/>Con : " + comment.label_con + "<br>Index: "
				+ comments.getIndex() + "/" + (comments.size() - 1) + "</html>";
	}

	private void setComment(Comment comment) {
		commentLabel.setText(getLabel(comment));
		commentContent.setText(comment.message);

		if (questionPanel != null) {
			contentPane.remove(questionPanel);
		}

		questionPanel = new JPanel(new GridLayout(2, 1));
		questionPanel.add(new QuestionView(comment, this));

		addTableOpinion(comment);

		contentPane.add(questionPanel);
		contentPane.repaint();
	}

	private void addTableOpinion(Comment comment) {

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(new JLabel(((resultOpinionExtractor.get(comment.id) != null)
				? "Opinions detecte: " + resultOpinionExtractor.get(comment.id).getOpinionValue().toString() : "")),
				BorderLayout.NORTH);

		Opinion opinion = resultOpinionExtractor.get(comment.id);

		if (opinion == null) {
			return;
		}

		Vector<Vector<String>> dataList = resultOpinionDetailExtractor.getForTable(opinion.id);

		if (dataList == null) {
			return;
		}

		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);

		tableModel.setColumnCount(0);
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();

		Vector<String> header = OpinionDetails.getHeaders();

		tableModel.setDataVector(dataList, header);

		panel.add(scrollPane);

		questionPanel.add(panel, BorderLayout.CENTER);
	}

	public void nextComment() {
		currentComment = comments.getNextComment();
		if (currentComment != null) {
			if (classify.get(currentComment.id) != null) {
				nextComment();
			} else {
				setComment(currentComment);
			}
		} else {
			if (questionPanel != null) {
				contentPane.remove(questionPanel);
			}
			contentPane.repaint();
			JOptionPane.showMessageDialog(null, "No comments left", "Information", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void saveComment() {
		currentComment.save();
	}
}
