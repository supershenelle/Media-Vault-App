package model;

/**
 * represents a videogame entry
 */
public class Videogame extends Media {
    private String title;
    private String developer;
    private int year;
    private String description;
    private int hoursPlayed;

    /**
     * constructs a new videogame
     * @param title title of the videogame
     * @param developer developer of the videogame
     * @param year year when videogame released
     * @param description description of the videogame
     * @param hoursPlayed how many hours played
     * @param status initial status of the videogame entry
     */
    public Videogame(String title, String developer, int year, String description, int hoursPlayed, Status status)
    {
        super("model.Videogame", status);
        this.title = title;
        this.developer = developer;
        this.year = year;
        this.description = description;
        this.hoursPlayed = hoursPlayed;
    }

    /**
     * get title of the videogame
     * @return title of the videogame
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * get specific videogame info
     * @return a string of the summary
     */
    @Override
    protected String getSpecificInfo()
    {
        return "Title: " + title +
                "\nDeveloper: " + developer +
                "\nYear: " + year +
                "\nDescription: " + description +
                "\nHours Played: " + hoursPlayed;
    }

    /**
     * set title of the videogame
     * @param title new title of the videogame
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * get developer of the videogame
     * @return the developer of the videogame
     */
    public String getDeveloper()
    {
        return developer;
    }

    /**
     * set developer of the videogame
     * @param developer the new developer of the videogame
     */
    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    /**
     * get year when videogame released
     * @return the year when videogame released
     */
    public int getYear()
    {
        return year;
    }

    /**
     * set year when videogame released
     * @param year the new year when videogame released
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * get description of the videogame
     * @return the description of the videogame
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * set description of the videogame
     * @param description is the new description of the videogame
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * get how many hours played
     * @return how many hours played
     */
    public int getHoursPlayed()
    {
        return hoursPlayed;
    }

    /**
     * set how many hours played
     * @param hoursPlayed is the new hours played
     */
    public void setHoursPlayed(int hoursPlayed)
    {
        this.hoursPlayed = hoursPlayed;
    }

}
