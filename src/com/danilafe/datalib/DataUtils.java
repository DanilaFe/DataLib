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

public class DataUtils {

	public static void writeString(OutputStream stream, String str) throws IOException {
		byte[] data = new byte[str.length() + 1];
		data[0] = (byte) str.length();
		for(int i = 0; i < str.length(); i++) data[i + 1] = (byte) str.charAt(i);

		stream.write(data);
	}

	public static String readString(InputStream stream) throws IOException {
		byte length = (byte) stream.read();
		String output = "";
		for(int i = 0; i < length; i++) output += (char) stream.read();

		return output;
	}

	public static void writeFloat(OutputStream stream, float flt) throws IOException {
		DataOutputStream dos = new DataOutputStream(stream);
		dos.writeFloat(flt);
	}

	public static float readFloat(InputStream stream, float flt) throws IOException{
		DataInputStream dis = new DataInputStream(stream);
		return dis.readFloat();
	}

	public static void writeInt(OutputStream stream, int in) throws IOException {
		DataOutputStream dos = new DataOutputStream(stream);
		dos.writeInt(in);
	}

	public static int readInt(InputStream stream) throws IOException {
		DataInputStream dis = new DataInputStream(stream);
		return dis.readInt();
	}

	public static void identifyAndWrite(OutputStream stream, Element element) throws IOException {
		stream.write(element.getType());
		element.writeSelf(stream);
	}

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
