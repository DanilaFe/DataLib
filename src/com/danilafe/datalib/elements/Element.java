package com.danilafe.datalib.elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.danilafe.datalib.DataUtils;

public class Element {

	public String name;

	public void writeSelf(OutputStream outputStream) throws IOException {
		DataUtils.writeString(outputStream, name);
	}

	public void parseSelf(InputStream inputStream) throws IOException {
		name = DataUtils.readString(inputStream);
	}

	public int getType(){
		return 0;
	}
}
