package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.cedarsoftware.util.io.JsonWriter;

public class util {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static <E> void println(E thing) {
	System.out.println(thing);
    }

    public static String readLine() {
	try {
	    String result = br.readLine();
	    return result;
	} catch (IOException e) {
	    return null;
	}
    }

    public static <E> String reprObject(E object) {
	try {
	    return JsonWriter.formatJson(JsonWriter.objectToJson(object));
	} catch (IOException e) {
	    return null;
	}
    }
}
