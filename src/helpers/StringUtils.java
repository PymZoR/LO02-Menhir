package helpers;


/**
 * Various strings methods used to render cards in console
 */
public class StringUtils {
	/**
	 * Get one line by its number on a string splitted by line breaks
	 * @param from  Source text
	 * @param lineN Line Number
	 * @return The wanted line
	 */
	static public String getLine(String from, int lineN) {
		return StringUtils.getLine(from, lineN, "\n");
	}

	/**
	 * Get one line by its number on a splitted string
	 * @param from  Source text
	 * @param lineN Line Number
	 * @param s     Split separator
	 * @return The wanted line
	 */
	static public String getLine(String from, int lineN, String s) {
		String line = from.split(s)[lineN];
		return line.substring(0, line.length());
	}

	/**
	 * Replace a char at a given index in a string
	 * @param from  Source text
	 * @param index Where to replace the char
	 * @param c     The new char
	 * @return Replaced text
	 */
	static public String replaceCharAt(String from, int index, String c) {
		StringBuilder builder = new StringBuilder(from);
		builder.setCharAt(index, c.charAt(0));
		return builder.toString();
	}
}
