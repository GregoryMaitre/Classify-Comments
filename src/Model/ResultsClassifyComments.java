package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import Controller.JSONManager;

public class ResultsClassifyComments {
	
	private String resultDirectory;
	private HashMap<Integer, Comment> comments;
	public ResultsClassifyComments(String directory) {
		resultDirectory = directory;
		comments = new HashMap<Integer, Comment>();
	}
	
	public boolean load() {
		boolean done = false;
		File directory = new File(resultDirectory);
		
		if (directory.isDirectory()) {
			try {
				done = loadAllComments(directory);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return done;
	}

	private boolean loadAllComments(File file) throws FileNotFoundException {
		boolean result = false;
				
		if (file.isFile()) {
			StringBuilder json = new StringBuilder();
			Scanner fileStream = new Scanner(new FileInputStream(file));
			while (fileStream.hasNext()) {
				json.append(fileStream.nextLine());
			}
			fileStream.close();

			JSONManager manager = new JSONManager();
			Comment comment = manager.toComment(json.toString());
			comments.put(comment.id, comment);
			
			result = true;
		} else if (file.isDirectory()) {
			File[] children = file.listFiles();
			
			for (File child : children) {
				result = loadAllComments(child) || result;
			}
		}
		
		return result;
	}
	
	public Comment get(int id) {
		return comments.get(id);
	}
}
