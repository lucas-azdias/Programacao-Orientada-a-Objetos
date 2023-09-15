package com.iosystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IOBufferedObjects {
	
	private String path;
	
	public IOBufferedObjects(String path) {
		this.path = path;
	}
	
	public Object read() throws IOException, ClassNotFoundException {
		ObjectInputStream stream = new ObjectInputStream(new FileInputStream(path));
		Object object = stream.readObject();
		stream.close();
		return object;
	}
	
	public void write(Object object) throws IOException {
		ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path));
		stream.writeObject(object);
		stream.close();
	}

}
