package model;

/**
 * represents the album of a music artist
 */
public class Album {
    private String title;
    private String genre;
    private int trackCount;
    private int songsListened;
    private int year;

    /**
     * Constructs a new album.
     * songsListened is initialized to 0
     * @param title title of the album
     * @param genre genre of the album
     * @param year year when album is released
     * @param trackCount number of track count in the album
     */
    public Album(String title, String genre, int year, int trackCount)
    {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.trackCount = trackCount;
        this.songsListened = 0;
    }

    /**
     * get the title of the album
     *
     * @return the title of the album
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * change the title of the album
     * @param title is the new title of the album
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * get the genre of the album
     * @return the genre of the album
     */
    public String getGenre()
    {
        return genre;
    }

    /**
     * change the genre of the album
     * @param genre is the new genre of the album
     */
    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    /**
     * get the year when the album released
     * @return album release year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * change the year of the album
     * @param year is the new album release year
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * get the track count of the album
     * @return the track count
     */
    public int getTrackCount()
    {
        return trackCount;
    }

    /**
     * set the total number of tracks in the album
     * @param trackCount the new track count
     */
    public void setTrackCount(int trackCount)
    {
        this.trackCount = trackCount;
    }

    /**
     * get the number of songs the user has listened to
     * @return the number of songs listened to
     */
    public int getSongsListened()
    {
        return songsListened;
    }

    /**
     * set the number of songs the user has listened to.
     * prints an error message if input is out of range
     * @param songsListened number of songs listened to
     */
    public void setSongsListened(int songsListened)
    {
        if(songsListened <= trackCount && songsListened >= 0)
            this.songsListened = songsListened;
        else
            System.out.printf("input must be between 0 and " + trackCount);
    }

    /**
     * checks whether an album is completed, meaning songs listened is equal to the track count
     * @return true when songs listened is equal to track count, false otherwise
     */
    public boolean isCompleted()
    {
        return trackCount == songsListened;
    }

    /**
     * displays a summary of the album
     * @return a string of the summary
     */
    public String displayInfo()
    {
        return "model.Album: " + title +
                "\nGenre: " + genre +
                "\nYear: " + year +
                "\nProgress: " + songsListened + "/" + trackCount + "songs\n";
    }
}
