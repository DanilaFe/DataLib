import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

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

}
