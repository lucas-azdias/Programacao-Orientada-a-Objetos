package com.iosystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IOBufferedText {
	
	private String path;
	
	public IOBufferedText(String path) {
		this.path = path;
	}

	public String[] readLines() throws IOException {
		ArrayList<String> lines = new ArrayList<>();

		BufferedReader buffer = new BufferedReader(new FileReader(path));
		
		String line;
		while ((line = buffer.readLine()) != null) {
			lines.add(line);
		}
		
		buffer.close();

		return lines.toArray(new String[lines.size()]);
	}
	
	public void writeLines(String[] lines) throws IOException {
		BufferedWriter buffer = new BufferedWriter(new FileWriter(path));
		
		for (String line : lines) {
			buffer.write(line + "\n");
		}
		
		buffer.close();
	}

}
