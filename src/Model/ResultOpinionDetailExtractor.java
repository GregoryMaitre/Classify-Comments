package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import Controller.JSONManager;

public class ResultOpinionDetailExtractor {
	private File resultOpinionsDetails;
	private OpinionsDetails opinionsDetails;

	public ResultOpinionDetailExtractor(File opinionsDetails) {
		resultOpinionsDetails = opinionsDetails;
		this.opinionsDetails = new OpinionsDetails();
	}

	public boolean load() throws FileNotFoundException {
		boolean done = false;

		if (resultOpinionsDetails.isFile()) {

			StringBuilder json = new StringBuilder();
			Scanner fileStream = new Scanner(new FileInputStream(resultOpinionsDetails));
			while (fileStream.hasNext()) {
				json.append(fileStream.nextLine());
			}
			fileStream.close();
			JSONManager jsonManager = new JSONManager();
			OpinionDetails[] opinionsDetails = jsonManager.toOpinionsDetails(json.toString());

			for (OpinionDetails opinion : opinionsDetails) {
				this.opinionsDetails.add(opinion);
			}

			done = true;
		}

		return done;
	}

	public Vector<Vector<String>> getForTable(int id) {
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		ArrayList<OpinionDetails> opinionsDetails = this.opinionsDetails.get(id);

		if (opinionsDetails != null) {
			for (OpinionDetails detail : opinionsDetails) {
				result.add(detail.getForTable());
			}
		}

		return result;
	}
}
