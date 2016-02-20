package com.danilafe.datalib.elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.danilafe.datalib.DataUtils;

public class ElementString extends Element {

	public String data;

	@Override
	public void writeSelf(OutputStream outputStream) throws IOException {
		super.writeSelf(outputStream);
		DataUtils.writeString(outputStream, data);
	}

	@Override
	public void parseSelf(InputStream inputStream) throws IOException {
		super.parseSelf(inputStream);
		data = DataUtils.readString(inputStream);
	}

	@Override
	public int getType() {
		return 4;
	}
}
