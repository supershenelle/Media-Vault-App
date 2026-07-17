package model;

/**
 * represents a movie entry
 */
public class Movie extends Media {
    private String title;
    private String director;
    private int year;
    private String description;

    /**
     * constructs a new movie
     * @param title title of the movie
     * @param director director of the movie
     * @param year year when film released
     * @param description description of the movie
     * @param status initial status of the movie entry
     */
    public Movie(String title, String director, int year, String description, Status status)
    {
        super("model.Movie", status);
        this.title = title;
        this.director = director;
        this.year = year;
        this.description = description;
    }

    /**
     * get title of the movie
     * @return title of the movie
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * get specific movie info
     * @return a string of the summary
     */
    @Override
    protected String getSpecificInfo()
    {
        return "Title: " + title +
                "\nDirector: " + director +
                "\nYear: " + String.valueOf(year) +
                "\nDescription: " + description;
    }

    /**
     * set title of the movie
     * @param title is the new title of the movie
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * get director of the movie
     * @return director of the movie
     */
    public String getDirector()
    {
        return director;
    }

    /**
     * set director of the movie
     * @param director is the new director of the movie
     */
    public void setDirector(String director)
    {
        this.director = director;
    }

    /**
     * get year when movie released
     * @return year when movie released
     */
    public int getYear()
    {
        return year;
    }

    /**
     * set year when movie released
     * @param year is the new year when movie released
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * get description of the movie
     * @return description of the movie
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * set description of the movie
     * @param description the new description of the movie
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

}
