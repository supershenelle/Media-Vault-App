package model;

import java.util.ArrayList;
import java.util.List;

/**
 * manages a list of media entry objects
 */
public class Library {
    private ArrayList<Media> entries = new ArrayList<>();

    /**
     * display summary of the library. includes total number
     * of entries, counts per status, and average rating of all rated entries
     */
    public void displaySummary()
    {
        int plannedCount = 0;
        int inProgressCount = 0;
        int completedCount = 0;

        // eto ung if narate na so hiwalay sya sa completed entries gets ba...
        int completedRatedCount = 0;
        int completedRatingTotal = 0;

        for (Media media : entries)
        {
            if (media.getStatus() == Status.PLANNED)
                plannedCount++;

            else if (media.getStatus() == Status.IN_PROGRESS)
                inProgressCount++;

            else if (media.getStatus() == Status.COMPLETED)
            {
                completedCount++;
                if (media.getRating() > 0)
                {
                    completedRatedCount++;
                    completedRatingTotal += media.getRating();
                }
            }
        }

        System.out.println("--- LIBRARY SUMMARY ---");
        System.out.println("   -->    Total entries: " + entries.size());
        System.out.println("   -->    Planned: " + plannedCount);
        System.out.println("   -->    In Progress: " + inProgressCount);
        System.out.println("   -->    Completed: " + completedCount);

        if (completedRatedCount > 0)
        {
            double averageRating = (double)completedRatingTotal / completedRatedCount;
            System.out.printf("   -->    Average rating of completed entries: %.2f%n", averageRating);
        }
        else
            System.out.println("   -->    Average rating of completed entries: N/A (no rated completed entries yet)");

        System.out.println("--- TO VIEW ALL ENTRIES, PLEASE USE OPTION (C) IN THE LIBRARY MENU ---");
        System.out.println("-----------------------\n");
    }

    /**
     * displays all movies currently in the library
     */
    public void displayMovies()
    {
        System.out.println("--- CURRENT FILMS IN LIBRARY ---");
        boolean hasMovies = false;
        for (Media media : entries)
        {
            if (media instanceof Movie)
            {
                System.out.println("   -> " + media.getTitle());
                hasMovies = true;
            }
        }
        if (!hasMovies)
            System.out.println("   [ No films logged ]");

        System.out.println("--------------------------------");
    }

    /**
     * displays all videogames currently in the library
     */
    public void displayGames() {
        System.out.println("--- CURRENT GAMES IN LIBRARY ---");
        boolean hasGames = false;
        for (Media media : entries)
        {
            if (media instanceof Videogame)
            {
                System.out.println("   -> " + media.getTitle());
                hasGames = true;
            }
        }
        if (!hasGames)
            System.out.println("   [ No games logged ]");

        System.out.println("--------------------------------");
    }


    /**
     * displays all music artists currently in the library
     */
    public void displayArtists()
    {
        System.out.println("--- CURRENT ARTISTS IN LIBRARY ---");
        boolean hasArtists = false;
        for (Media media : entries)
        {
            if (media instanceof MusicArtist) {
                System.out.println("   -> " + media.getTitle());
                hasArtists = true;
            }
        }
        if (!hasArtists)
            System.out.println("   [ No artist activity logged ]");

        System.out.println("--------------------------------");
    }

    /**
     * get the top 3 recent titles in the library
     * @param type contains media type
     * @return array of the 3 most recent title
     */
    public String[] getRecentTitles(String type)
    {
        // arraylist since pwede na wala munang naka-log or pwedeng isa lang rin etc etc.
        List<String> titles = new ArrayList<>();

        // pabackward tas top 3 lang
        for (int i = entries.size() - 1; i >= 0 && titles.size() < 3; i--)
        {
            // get ung media na pinaka recent
            Media media = entries.get(i);

            if (media.getType().equals(type))
            {
                titles.add(media.getTitle());
            }
        }

        return titles.toArray(new String[0]);
    }

    /**
     * get the top 3 recent artist discography
     * @param type is the media type
     * @return array of the 3 recent artist discography
     */
    public String[] getRecentArtistDiscography(String type)
    {
        List<String> discography = new ArrayList<>();

        for (int i = entries.size() - 1; i >= 0 && discography.size() < 3; i--)
        {
            Media media = entries.get(i);

            if (media instanceof MusicArtist && media.getType().equals(type))
            {
                MusicArtist artist = (MusicArtist) media;

                for (int j = artist.getAlbums().size() - 1; j >= 0 && discography.size() < 3; j--)
                {
                    Album album = artist.getAlbums().get(j);
                    discography.add(artist.getName() + ": " + album.getTitle());
                }
            }
        }

        return discography.toArray(new String[0]);
    }

    /**
     * add new media entry to library
     * @param media the media entry to add
     */
    public void addEntry(Media media)
    {
        entries.add(media);
    }


    /**
     * remove media entry from the library
     * @param media the entry to remove
     * @return true if succesfully removed, false otherwise
     */
    public boolean removeEntry(Media media)
    {
        if (media != null)
        {
            entries.remove(media);
            return true;
        }
        return false;
    }

    /**
     * get all media entries in library
     * @return all media entries
     */
    public ArrayList<Media> getEntries()
    {
        return entries;
    }

    /**
     * finds the media entry in the library
     * @param type is the media type
     * @param title is the title/ name of the media you are searching for
     * @return the media entry if found, otherwise return null
     */
    public Media findEntry(String type, String title)
    {
        for (Media media : entries)
        {
            if (media.getType().equalsIgnoreCase(type) && media.getTitle().equalsIgnoreCase(title))
                return media;
        }
        return null;
    }

    /**
     * filter media entries by status
     * @param status contains the status you want to filter by
     * @return new arraylist containing only media entries with that status
     */
    public ArrayList<Media> filterByStatus(Status status)
    {
        ArrayList<Media> filtered = new ArrayList<>();
        for (Media media : entries)
        {
            if (media.getStatus() == status)
                filtered.add(media);
        }
        return filtered;
    }

    /**
     * filter media entries by type
     * @param type contains the type you want to filter by
     * @return new arraylist containing only entries with that media type
     */
    public ArrayList<Media> filterByType(String type)
    {
        ArrayList<Media> filtered = new ArrayList<>();
        for (Media media : entries)
        {
            if (media.getType().equalsIgnoreCase(type))
                filtered.add(media);
        }
        return filtered;
    }
}