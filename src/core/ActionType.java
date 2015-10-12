package core;


/**
 * Every possible action
 */
public enum ActionType {
    GIANT("Géant"),
    FERTILIZER("Fertilisant"),
    HOBGOBLIN("Farfadet"),
    TAUPE("Taupe"),
    DOG("Chien");
    private final String name;

    /**
     * Store the real action name
     *
     * @param s The real action name
     */
    private ActionType(String s) {
        this.name = s;
    }

    /**
     * Check for equality between two actions
     *
     * @param otherName The other action name
     * @return True if equals
     */
    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : this.name.equals(otherName);
    }

    /**
     * Get the real action name
     *
     * @return The real action name
     */
    @Override
    public String toString() {
        return this.name;
    }
}
