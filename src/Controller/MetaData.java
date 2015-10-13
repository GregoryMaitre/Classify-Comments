package Controller;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MetaData {
	public static Properties properties = new Properties();
	public static String defaultname = "metadata";

	public static void save(int id) {
		Frame f = new Frame();
		FileDialog fileChooser = new FileDialog(f, "Save", FileDialog.SAVE);
		fileChooser.setVisible(true);
		String name = fileChooser.getDirectory() + fileChooser.getFile();
		
		if (fileChooser.getFile() == null) {
			return;
		}
		
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(name);
			properties.setProperty("comment_id", Integer.toString(id));
			properties.store(output, null);
		} catch (FileNotFoundException e) {
			System.err.println("Can't save metadata");
		} catch (IOException e) {
			System.err.println("Can't save metadata");
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static int load() {
		Frame f = new Frame();
		FileDialog fileChooser = new FileDialog(f, "Load", FileDialog.LOAD);
		fileChooser.setVisible(true);
		String name = fileChooser.getDirectory() + fileChooser.getFile();
		InputStream input = null;
		int comment_id = -1;

		try {

			input = new FileInputStream(name);
			properties.load(input);

			// get the property value and print it out
			comment_id = Integer.parseInt(properties.getProperty("comment_id"));

		} catch (IOException e) {
			System.err.println("Cant load metadata");
		} catch (NumberFormatException e) {
			System.err.println("Cant load metadata");
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return comment_id;
	}
}
