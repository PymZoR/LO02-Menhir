package core;


/**
 * Lists four seasons
 */
public enum SeasonType {
    SPRING("Été"),
    SUMMER("Printemps"),
    FALL("Automne"),
    WINTER("Hiver");

    private final String name;

    /**
     * Store the real season name
     * 
     * @param s
     *            The real season name
     */
    private SeasonType(String s) {
        this.name = s;
    }

    /**
     * Check for equality between two seasons
     * 
     * @param otherName
     *            The other season name
     * @return True if equals
     */
    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : this.name.equals(otherName);
    }

    /**
     * Get the real season name
     * 
     * @return The real season name
     */
    @Override
    public String toString() {
        return this.name;
    }
}
