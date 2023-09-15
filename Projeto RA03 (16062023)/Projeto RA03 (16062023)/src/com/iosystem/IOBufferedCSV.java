package com.iosystem;

import java.io.IOException;

public class IOBufferedCSV extends IOBufferedText {
	
	public static final String DEFAULT_SEPARATOR = ";";
	
	private String separator;
	
	public IOBufferedCSV(String path, String separator) {
		super(path);
		this.separator = separator;
	}
	
	public IOBufferedCSV(String path) {
		super(path);
		this.separator = DEFAULT_SEPARATOR;
	}

	public String[][] readLinesCSV() throws IOException {
		String[] lines = super.readLines();
		String[][] linesCSV = new String[lines.length][];
		
		for (int i = 0; i < lines.length; i++) {
			linesCSV[i] = lines[i].split(separator);
		}
		
		return linesCSV;
	}
	
	public void writeLinesCSV(String[][] linesCSV) throws IOException {
		String[] lines = new String[linesCSV.length];
		
		for (int i = 0; i < lines.length; i++) {
			lines[i] = String.join(separator, linesCSV[i]);
		}
		
		super.writeLines(lines);
	}

}
