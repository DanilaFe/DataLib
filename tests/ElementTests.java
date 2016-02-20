import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

import com.danilafe.datalib.DataUtils;
import com.danilafe.datalib.elements.Element;
import com.danilafe.datalib.elements.ElementFloat;
import com.danilafe.datalib.elements.ElementInt;
import com.danilafe.datalib.elements.ElementList;
import com.danilafe.datalib.elements.ElementString;

public class ElementTests {

	public static final String IO_EXCEPTION = "Exception while writing data";

	@Test
	public void testString() {
		byte[] expectedData = new byte[]{
				4, 't', 'e', 's', 't', 4, 't', 'e', 's', 't'
		};

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(expectedData.length);

		ElementString elementString = new ElementString();
		elementString.name = "test";
		elementString.data = "test";

		try {
			elementString.writeSelf(byteArrayOutputStream);
			byte[] producedData = byteArrayOutputStream.toByteArray();
			assertArrayEquals(expectedData, producedData);
		} catch (IOException e) {
			fail(IO_EXCEPTION);
		}
	}

	@Test
	public void testList()  {
		byte[] expectedData = new byte[] {
				4, 'l', 'i', 's', 't', 1, 4, 4, 't', 'e', 's', 't', 4, 't', 'e', 's', 't'
		};

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(expectedData.length);

		ElementString elementString = new ElementString();
		elementString.name = "test";
		elementString.data = "test";

		ElementList elementList = new ElementList();
		elementList.name = "list";
		elementList.elements.add(elementString);

		try {
			elementList.writeSelf(byteArrayOutputStream);
			byte[] producedData = byteArrayOutputStream.toByteArray();
			assertArrayEquals(expectedData, producedData);
		} catch (IOException e) {
			fail(IO_EXCEPTION);
		}

	}

	@Test
	public void testNetworkString() {
		ElementString string = new ElementString();
		string.name = "test string";
		string.data = "a secret messege goes here!";

		try {
			ServerSocket serverSocket = new ServerSocket(8099);
			Socket socket = new Socket("localhost", 8099);
			Socket sSocket = serverSocket.accept();

			DataUtils.identifyAndWrite(socket.getOutputStream(), string);
			Element received = DataUtils.identifyAndParse(sSocket.getInputStream());

			assertTrue(received instanceof ElementString);
			assertEquals(string.name, received.name);
			assertEquals(string.data, ((ElementString)received).data);

			sSocket.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			fail(IO_EXCEPTION);
		}
	}

	@Test
	public void testNetworkList() {
		ElementString string_a = new ElementString();
		string_a.name = "test string a";
		string_a.data = "a secret messege goes here!";
		ElementString string_b = new ElementString();
		string_b.name = "test string b";
		string_b.data = "This is another secret message!";
		ElementList list = new ElementList();
		list.name = "list";
		list.elements.add(string_a);
		list.elements.add(string_b);

		try {
			ServerSocket serverSocket = new ServerSocket(8099);
			Socket socket = new Socket("localhost", 8099);
			Socket sSocket = serverSocket.accept();

			DataUtils.identifyAndWrite(socket.getOutputStream(), list);
			Element received = DataUtils.identifyAndParse(sSocket.getInputStream());
			assertTrue(received instanceof ElementList);

			ElementList receivedList = (ElementList) received;
			assertEquals(receivedList.name, "list");
			assertEquals(receivedList.elements.size(), 2);
			assertTrue(receivedList.elements.get(0) instanceof ElementString);
			assertTrue(receivedList.elements.get(1) instanceof ElementString);

			ElementString receivedStringA = (ElementString) receivedList.elements.get(0);
			ElementString receivedStringB = (ElementString) receivedList.elements.get(1);
			assertEquals(receivedStringA.name, string_a.name);
			assertEquals(receivedStringB.name, string_b.name);
			assertEquals(receivedStringA.data, string_a.data);
			assertEquals(receivedStringB.data, string_b.data);

			sSocket.close();
			socket.close();
			serverSocket.close();

		} catch (IOException e) {
			fail(IO_EXCEPTION);
		}
	}

	@Test
	public void testNetworkFloat() {
		ElementFloat flt = new ElementFloat();
		flt.name = "test float";
		flt.data = 3.1415F;

		try {
			ServerSocket serverSocket = new ServerSocket(8099);
			Socket socket = new Socket("localhost", 8099);
			Socket sSocket = serverSocket.accept();

			DataUtils.identifyAndWrite(socket.getOutputStream(), flt);
			Element received = DataUtils.identifyAndParse(sSocket.getInputStream());

			assertTrue(received instanceof ElementFloat);
			assertEquals(flt.name, received.name);
			assertTrue(flt.data == ((ElementFloat)received).data);

			sSocket.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			fail(IO_EXCEPTION);
		}
	}

	@Test
	public void testNetowrkInt() {
		ElementInt in = new ElementInt();
		in.name = "test int";
		in.data = 5;

		try {
			ServerSocket serverSocket = new ServerSocket(8099);
			Socket socket = new Socket("localhost", 8099);
			Socket sSocket = serverSocket.accept();

			DataUtils.identifyAndWrite(socket.getOutputStream(), in);
			Element received = DataUtils.identifyAndParse(sSocket.getInputStream());

			assertTrue(received instanceof ElementInt);
			assertEquals(in.name, received.name);
			assertTrue(in.data == ((ElementInt)received).data);

			sSocket.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			fail(IO_EXCEPTION);
		}
	}


}
