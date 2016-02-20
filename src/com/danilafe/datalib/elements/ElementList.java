package com.danilafe.datalib.elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.danilafe.datalib.DataUtils;

/**
 * List element. Holds other elements without a particular order.
 * @author vanilla
 *
 */
public class ElementList extends Element {

	public ArrayList<Element> elements = new ArrayList<Element>();

	@Override
	public void writeSelf(OutputStream outputStream) throws IOException {
		super.writeSelf(outputStream);
		outputStream.write(elements.size());
		for(Element e : elements) DataUtils.identifyAndWrite(outputStream, e);
	}

	@Override
	public void parseSelf(InputStream inputStream) throws IOException {
		super.parseSelf(inputStream);
		int length = inputStream.read();
		for(int i = 0; i < length; i++) elements.add(DataUtils.identifyAndParse(inputStream));
	}

	@Override
	public int getType() {
		return 3;
	}

}
