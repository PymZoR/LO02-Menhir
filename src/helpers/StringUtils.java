package helpers;

public class StringUtils {
	static public String getLine(String from, int lineN, String s) {
		String line = from.split(s)[lineN]; 
		return line.substring(0, line.length());
	}
	
	static public String getLine(String from, int lineN) {
		return StringUtils.getLine(from, lineN, "\n");
	}
	static public String replaceCharAt(String from, int index, String c) {
		StringBuilder builder = new StringBuilder(from);
		builder.setCharAt(index, c.charAt(0));
		return builder.toString();
	}
}
