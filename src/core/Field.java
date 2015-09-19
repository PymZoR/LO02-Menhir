package core;

/**
 * Field management
 */
public class Field {
	/**
	 * Big and small rocks amounts
	 */
	private int bigRockNumber   = 0;
	private int smallRockNumber = 0;

	/**
	 * Create a field
	 */
	public Field() {

	}

	/**
	 * Get the big rock amount
	 * @return The big rock amount
	 */
	public int getBigRockNumber() {
		return this.bigRockNumber;
	}

	/**
	 * Add big rocks to the field
	 * @param number Big rocks to add
	 */
	public void addBigRockNumber(int number) {
		this.bigRockNumber += number;
	}

	/**
	 * Get the small rocks amount
	 * @return The small rocks amount
	 */
	public int getSmallRockNumber() {
		return this.smallRockNumber;
	}

	/**
	 * Add small rocks to the field
	 * @param number Small rocks to add
	 */
	public void addSmallRockNumber(int number) {
		this.smallRockNumber += number;
	}

	/**
	 * Set the small rocks amount
	 * @param number The small rocks amount
	 */
	public void setSmallRockNumber(int number) {
		this.smallRockNumber = number;
	}
}
