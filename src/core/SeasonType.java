package core;


/**
 * Lists four seasons
 */
public enum SeasonType {
    SPRING("Spring"),
    SUMMER("Summer"),
    FALL("Fall"),
    WINTER("Winter");

    private final String name;

    /**
     * Store the real season name
     *
     * @param s The real season name
     */
    private SeasonType(String s) {
        this.name = s;
    }

    /**
     * Check for equality between two seasons
     *
     * @param otherName The other season name
     * @return True if equals
     */
    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : this.name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
