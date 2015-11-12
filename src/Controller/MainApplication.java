package Controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Common.Constants;
import Model.Comments;
import Model.ResultOpinionDetailExtractor;
import Model.ResultOpinionExtractor;
import View.CommentView;

public class MainApplication {
	public static void main(String[] args) throws IOException {

		File targetFile = new File(Constants.TARGET_FILE);

		if (!targetFile.exists() || !targetFile.isFile()) {
			Frame f = new Frame();
			FileDialog fileChooser = new FileDialog(f, "Load", FileDialog.LOAD);
			fileChooser.setVisible(true);
			targetFile = new File(fileChooser.getDirectory() + fileChooser.getFile());
			if (!targetFile.exists() || !targetFile.isFile()) {
				JOptionPane.showMessageDialog(null, "File invalid", "Error", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}

		//// Extraction from a file
		// Results directory
		File resultDir = new File(Constants.COMMENTS_PATH);
		if (!resultDir.exists() || !resultDir.isDirectory()) {
			if (!resultDir.mkdir()) {
				System.err.println("Can't create the output directory");
				System.exit(1);
			}
		}

		StringBuilder jsons = new StringBuilder();
		try {

			Scanner fileStream = new Scanner(new FileInputStream(targetFile));
			while (fileStream.hasNext()) {
				jsons.append(fileStream.nextLine());
			}
			fileStream.close();

			//// Creation of model
			JSONManager jsonManager = new JSONManager();
			Comments comments = jsonManager.toComments(jsons.toString());
			ResultOpinionExtractor resultOpinionExtractor = new ResultOpinionExtractor(new File(Constants.OPINION_FILE));
			resultOpinionExtractor.load();
			ResultOpinionDetailExtractor resultOpinionDetailExtractor = new ResultOpinionDetailExtractor(new File(Constants.OPINION_DETAIL_FILE));
			resultOpinionDetailExtractor.load();
			new CommentView(comments, resultOpinionExtractor, resultOpinionDetailExtractor);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fatal error! Don't do that again!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
