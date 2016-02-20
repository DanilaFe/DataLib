package com.danilafe.datalib;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.danilafe.datalib.elements.Element;
import com.danilafe.datalib.elements.ElementFloat;
import com.danilafe.datalib.elements.ElementInt;
import com.danilafe.datalib.elements.ElementList;
import com.danilafe.datalib.elements.ElementString;

/**
 * Utilities for writing / reading data.
 * @author vanilla
 *
 */
public class DataUtils {

	/**
	 * Writes the given string in the specialized format to the stream.
	 * @param stream stream to write to.
	 * @param str the string to write
	 * @throws IOException
	 */
	public static void writeString(OutputStream stream, String str) throws IOException {
		byte[] data = new byte[str.length() + 1];
		data[0] = (byte) str.length();
		for(int i = 0; i < str.length(); i++) data[i + 1] = (byte) str.charAt(i);

		stream.write(data);
	}

	/**
	 * Reads the specially formatted string from the stream.
	 * @param stream stream to read from
	 * @return the generated string.
	 * @throws IOException
	 */
	public static String readString(InputStream stream) throws IOException {
		byte length = (byte) stream.read();
		String output = "";
		for(int i = 0; i < length; i++) output += (char) stream.read();

		return output;
	}

	/**
	 * Writes the float to the output stream using a data output stream
	 * @param stream stream to write to
	 * @param flt the float to write
	 * @throws IOException
	 */
	public static void writeFloat(OutputStream stream, float flt) throws IOException {
		DataOutputStream dos = new DataOutputStream(stream);
		dos.writeFloat(flt);
	}

	/**
	 * Reads the float from the input tream using a data input stream
	 * @param stream the stream to read from
	 * @return the generated float.
	 * @throws IOException
	 */
	public static float readFloat(InputStream stream) throws IOException{
		DataInputStream dis = new DataInputStream(stream);
		return dis.readFloat();
	}

	/**
	 * Writes the integer to the output stream.
	 * @param stream the stream to write to
	 * @param in the integer to write
	 * @throws IOException
	 */
	public static void writeInt(OutputStream stream, int in) throws IOException {
		DataOutputStream dos = new DataOutputStream(stream);
		dos.writeInt(in);
	}

	/**
	 * Reads an integer from the input stream
	 * @param stream the stream to read from
	 * @return the generated integer
	 * @throws IOException
	 */
	public static int readInt(InputStream stream) throws IOException {
		DataInputStream dis = new DataInputStream(stream);
		return dis.readInt();
	}

	/**
	 * Writes the identity and contents of the given element to the stream
	 * @param stream the stream to write to
	 * @param element the element being written
	 * @throws IOException
	 */
	public static void identifyAndWrite(OutputStream stream, Element element) throws IOException {
		stream.write(element.getType());
		element.writeSelf(stream);
	}

	/**
	 * Reads an element from the input stream. This expects the element type before everything else.
	 * @param stream the stream to read from
	 * @return the generated element.
	 * @throws IOException
	 */
	public static Element identifyAndParse(InputStream stream) throws IOException {
		int elementType = stream.read();
		Element toParse;

		if(elementType == 1) toParse = new ElementFloat();
		else if(elementType == 2) toParse = new ElementInt();
		else if(elementType == 3) toParse = new ElementList();
		else if(elementType == 4) toParse = new ElementString();
		else toParse = new Element();

		toParse.parseSelf(stream);
		return toParse;
	}
}
