package com.danilafe.datalib.elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.danilafe.datalib.DataUtils;

/**
 * Integer element. Carries a single int as data.
 * @author vanilla
 *
 */
public class ElementInt extends Element {

	public int data = 0;

	@Override
	public void writeSelf(OutputStream outputStream) throws IOException {
		super.writeSelf(outputStream);
		DataUtils.writeInt(outputStream, data);
	}

	@Override
	public void parseSelf(InputStream inputStream) throws IOException {
		super.parseSelf(inputStream);
		data = DataUtils.readInt(inputStream);
	}

	@Override
	public int getType() {
		return 2;
	}

}
