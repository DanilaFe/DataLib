package com.danilafe.datalib.elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.danilafe.datalib.DataUtils;

/**
 * Base element class.
 * @author vanilla
 *
 */
public class Element {

	/**
	 * The name of this element
	 */
	public String name;

	/**
	 * Outputs this element into the given output stream as parseable data.
	 * @param outputStream the output stream to write to.
	 * @throws IOException thrown if writing fails.
	 */
	public void writeSelf(OutputStream outputStream) throws IOException {
		DataUtils.writeString(outputStream, name);
	}

	/**
	 * Reads this element from the given input stream. This expects the beginning of element data,
	 * not its type ID as the first byte.
	 * @param inputStream the stream to read from.
	 * @throws IOException thrown in case of read failure.
	 */
	public void parseSelf(InputStream inputStream) throws IOException {
		name = DataUtils.readString(inputStream);
	}

	/**
	 * Gets the type of this element.
	 * @return
	 */
	public int getType(){
		return 0;
	}
}
