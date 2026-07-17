package model;

/**
 * represents the status of a media, which are planned, in progress, and completed
 */
public enum Status {
    PLANNED("Planned"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String display;

    /**
     * constructs a status with display
     * @param display is the display
     */
    Status(String display)
    {
        this.display = display;
    }

    /**
     * gets the display
     * @return display
     */
    public String getDisplay()
    {
        return display;
    }

    /**
     * is used for the menu in the interface
     * @param choice is the user input based on the choices sa interface
     * @return PLANNED for "1", IN_PROGRESS for "2", COMPLETED for "3', and null if no match
     */
    public static Status fromChoice(String choice)
    {
        switch(choice)
        {
            case "1":
                return PLANNED;
            case "2":
                return IN_PROGRESS;
            case "3":
                return COMPLETED;
        }

        return null;
    }
}
