import java.util.ArrayList;

/**
 * represent a music artist entry
 */
public class MusicArtist extends Media {
    private String name;
    private ArrayList<Album> albums;
    private String description;

    /**
     * constructs a new music artist
     * @param name is the name of the artist
     * @param description description of the artist
     * @param status initial status of the artist entry
     */
    public MusicArtist(String name, String description, Status status)
    {
        super("Music Artist", status);
        this.name = name;
        this.description = description;
        albums = new ArrayList<>();
    }

    /**
     * get name of the artist
     * @return name of the artist
     */
    public String getName()
    {
        return name;
    }

    /**
     * get shared media title
     * @return the artist name
     */
    @Override
    public String getTitle()
    {
        return name;
    }

    /**
     * set name of the arist
     * @param name the new name of the artist
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * get the albums of the artist
     * @return the artist's albums
     */
    public ArrayList<Album> getAlbums()
    {
        return albums;
    }

    /**
     * creates an album and adds it to the artist's discography
     * @param title title of the album
     * @param genre genre of the album
     * @param year year of the album
     * @param trackCount track count of the album
     */
    public void addAlbum(String title, String genre, int year, int trackCount)
    {
        Album album = new Album(title, genre, year, trackCount);
        albums.add(album);
    }

    /**
     * removes an album from artist
     * @param album is the album to be removed
     */
    public void removeAlbum(Album album)
    {
        albums.remove(album);
    }

    /**
     * get description of the artist
     * @return description of the artist
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * set description of the artist
     * @param description is the new description of the artist
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * checks whether all albums of the artist is completed
     * @return true if all albums are completed, otherwise false
     */
    public boolean isCompleted()
    {
        // if no albums, then false
        if(albums.isEmpty())
            return false;

        // if all albums not complete, then false
        for(Album album : albums)
        {
            if(!album.isCompleted())
                return false;
        }

        return true;
    }

    /**
     * get how many of the albums are completed
     * @return the progress of completing all albums
     */
    public String getProgress()
    {
        int completed = 0;

        for(Album album : albums)
        {
            if(album.isCompleted())
                completed++;
        }

        return completed + "/" + albums.size();
    }

    /**
     * find album in artist's discography using title
     * @param title is the title of the album
     * @return album when found, null if not found
     */
    public Album findAlbum(String title)
    {
        for (Album album : albums)
        {
            if (album.getTitle().equalsIgnoreCase(title))
                return album;
        }
        return null;
    }

    /**
     * prints each album of the artist, as well as the progress and status of the album
     */
    public void displayAlbums()
    {
        for (Album album : albums)
        {
            System.out.print("   -> " + album.getTitle() + " (" + album.getSongsListened() + "/" + album.getTrackCount() + " tracks");

            if (album.isCompleted())
            {
                System.out.print(", COMPLETED");
            }

            System.out.println(")");
            System.out.println("");
        }

    }

    /**
     * get specific artist info
     * @return a string of the summary
     */
    @Override
    protected String getSpecificInfo()
    {
        String info = "";

        info += "Artist: " + name + "\n";
        info += "Description: " + description + "\n";
        info += "Albums:\n";

        for (Album album : albums) {
            info += "- " + album.getTitle() + "\n";
        }

        return info;
    }

    @Override
    protected boolean canComplete() {
    if (!isCompleted())
    {
        System.out.println("You haven't finished all albums yet.");
        System.out.println(getProgress());
    }
    return isCompleted();
}
}
