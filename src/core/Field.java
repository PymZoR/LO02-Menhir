package core;


/**
 * Field management
 */
public class Field implements Comparable<Field> {
    /**
     * Big and small rocks amounts
     */
    private int bigRockNumber   = 0;
    private int smallRockNumber = 0;
    private int bigRockSum      = 0;
    private int smallRockSum    = 0;

    /**
     * Create a field
     */
    public Field() {

    }

    /**
     * Add big rocks to the field
     *
     * @param number Big rocks to add
     */
    public void addBigRockNumber(int number) {
        this.bigRockNumber += number;
        this.bigRockSum    += number;
    }

    /**
     * Add small rocks to the field
     *
     * @param number Small rocks to add
     */
    public void addSmallRockNumber(int number) {
        this.smallRockNumber += number;
        this.smallRockSum    += number;
    }

    @Override
    public int compareTo(Field compareField) {
        if (compareField.getBigRockSum() == this.getBigRockSum()) {
            return this.getSmallRockSum() - compareField.getSmallRockSum();
        } else {
            return this.getBigRockSum() - compareField.getBigRockSum();
        }
    }

    /**
     * Get the big rock amount
     *
     * @return The big rock amount
     */
    public int getBigRockNumber() {
        return this.bigRockNumber;
    }

    /**
     * Get the small rock sum
     *
     * @return The small rock sum
     */
    public int getBigRockSum() {
        return this.bigRockSum;
    }

    /**
     * Get the small rock amount
     *
     * @return The small rock amount
     */
    public int getSmallRockNumber() {
        return this.smallRockNumber;
    }

    /**
     * Get the small rock sum
     *
     * @return The small rock sum
     */
    public int getSmallRockSum() {
        return this.smallRockSum;
    }

    /**
     * Reset field after round
     */
    public void reset() {
        this.smallRockNumber = Round.INIT_SMALL_ROCK_NUMBER;
        this.smallRockSum += this.smallRockNumber;
        this.bigRockNumber = 0;
    }

    /**
     * Set the big rocks amount
     *
     * @param number The big rocks amount
     */
    public void setBigRockNumber(int number) {
        this.bigRockSum   += (number - this.bigRockNumber);
        this.bigRockNumber = number;
    }

    /**
     * Set the small rocks amount
     *
     * @param number The small rocks amount
     */
    public void setSmallRockNumber(int number) {
        this.smallRockSum   += (number - this.smallRockNumber);
        this.smallRockNumber = number;
    }

    @Override
    public String toString() {
        return "Field : " + this.bigRockNumber + " menhirs; " + this.smallRockNumber + " seeds.";
    }
}
