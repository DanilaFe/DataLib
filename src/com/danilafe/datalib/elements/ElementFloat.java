package com.danilafe.datalib.elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.danilafe.datalib.DataUtils;

/**
 * Float element. Carries a single float as data.
 * @author vanilla
 *
 */
public class ElementFloat extends Element {

	public float data = 0;

	@Override
	public void writeSelf(OutputStream outputStream) throws IOException {
		super.writeSelf(outputStream);
		DataUtils.writeFloat(outputStream, data);
	}

	@Override
	public void parseSelf(InputStream inputStream) throws IOException {
		super.parseSelf(inputStream);
		data = DataUtils.readFloat(inputStream);
	}

	@Override
	public int getType() {
		return 1;
	}

}
