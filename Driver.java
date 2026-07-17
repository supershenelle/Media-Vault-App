import java.util.Scanner;
import java.util.ArrayList;

public class Driver {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Profile profile = null;
        Library library = null;
        boolean profileCreated = false;
        boolean running = true;

        String[] films = new String[0];
        String[] games = new String[0];
        String[] music = new String[0];

        while (running)
        {
            Interface.displayTitle();
            Interface.printCentered("=== MAIN NAVIGATION: CHOOSE OPTION ===");
            Interface.printCentered("(1) CREATE NEW PROFILE      (2) EXIT PROGRAM");
            if (profileCreated)
                Interface.printCentered("(3) VIEW CURRENT PROFILE");
            System.out.println("");
            System.out.print("Enter input: ");
            String input = scanner.nextLine();

            switch(input)
            {
                case "1":
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter display name: ");
                    String displayName = scanner.nextLine();
                    System.out.print("Enter bio: ");
                    String bio = scanner.nextLine();

                    // set up everything for new profile
                    profile = new Profile(username, displayName, bio);
                    profileCreated = true;
                    library = profile.getLibrary();
                    films = library.getRecentTitles("Movie");
                    games = library.getRecentTitles("Videogame");
                    music = library.getRecentArtistDiscography("Music Artist");

                    Interface.displayProfile(profile, films, games, music);
                    boolean exitLibrary = false;

                    while (!exitLibrary)
                    {
                        String libChoice = Interface.libraryMenu(scanner);
                        switch(libChoice)
                        {
                            // add entry
                            case "A":
                                Interface.divider2();
                                Interface.printCentered("ENTER MEDIA TYPE");
                                String mediaChoice =Interface.getMediaTypeChoice(scanner);
                                System.out.println("");

                                switch(mediaChoice)
                                {
                                    case "Movie":
                                        System.out.println("");
                                        Interface.divider1();
                                        Interface.printCentered("=== ADDING FILM ENTRY ===");
                                        Interface.divider2();

                                        System.out.print("   -->    FILM TITLE: ");
                                        String filmTitle = scanner.nextLine();
                                        System.out.print("   -->    DIRECTOR: ");
                                        String director = scanner.nextLine();
                                        int filmYear = Interface.getIntInput(scanner, "   -->    YEAR RELEASED: ");
                                        System.out.print("   -->    DESCRIPTION: ");
                                        String filmDescription = scanner.nextLine();
                                        Status statusFilm = Interface.getInputStatusAddMedia(scanner);
                                        Movie movie = new Movie(filmTitle, director, filmYear, filmDescription, statusFilm);
                                        library.addEntry(movie);
                                        Interface.divider2();
                                        Interface.printCentered("=== FILM SUCCESFULLY ADDED! ===");
                                        Interface.divider1();
                                        System.out.println("");
                                        break;

                                    case "Videogame":
                                        System.out.println("");
                                        Interface.divider1();
                                        Interface.printCentered("=== ADDING GAME ENTRY ===");
                                        Interface.divider2();

                                        System.out.print("   -->    GAME TITLE: ");
                                        String gameTitle = scanner.nextLine();
                                        System.out.print("   -->    DEVELOPER: ");
                                        String developer = scanner.nextLine();
                                        int gameYear = Interface.getIntInput(scanner, "   -->    YEAR RELEASED: ");
                                        System.out.print("   -->    DESCRIPTION: ");
                                        String gameDescription = scanner.nextLine();
                                        int hoursPlayed = Interface.getIntInput(scanner, "   -->    HOURS PLAYED: ");
                                        Status statusGame = Interface.getInputStatusAddMedia(scanner);

                                        Videogame game = new Videogame(gameTitle, developer, gameYear, gameDescription, hoursPlayed, statusGame);
                                        library.addEntry(game);
                                        Interface.divider2();
                                        Interface.printCentered("=== GAME SUCCESFULLY ADDED! ===");
                                        Interface.divider1();
                                        System.out.println("");
                                        break;

                                    case "Music Artist":
                                        // display
                                        System.out.println("");
                                        Interface.divider1();
                                        Interface.printCentered("=== ADDING MUSIC ARTIST ENTRY ===");
                                        Interface.divider2();

                                        // get name and description, then create musicArtist object
                                        System.out.print("   -->    ARTIST NAME: ");
                                        String artistName = scanner.nextLine();
                                        System.out.print("   -->    DESCRIPTION: ");
                                        String artistDescription = scanner.nextLine();
                                        // get status nung artist then create the media and add it sa library
                                        Status statusArtist = Interface.getInputStatusAddMedia(scanner);
                                        MusicArtist musicArtist = new MusicArtist(artistName, artistDescription, statusArtist);

                                        // get ilan albums meron sa artist na yyun
                                        int albumCount = Interface.getIntInput(scanner, "   -->    HOW MANY ALBUMS DOES THIS ARTIST HAVE: ");
                                        while(albumCount < 1)
                                        {
                                            System.out.println("An artist cannot have zero albums");
                                            System.out.print("Enter choice: ");
                                            albumCount = Interface.getIntInput(scanner, "   -->    HOW MANY ALBUMS DOES THIS ARTIST HAVE: ");
                                        }

                                        // create the albums (SHEN KAW NA BAHALA SA INTERFACE NETO)
                                        for(int i = 1; i <= albumCount; i++)
                                        {
                                            System.out.println("--- Input Album " + i + " --------------");

                                            System.out.print("   -->    Title: ");
                                            String title = scanner.nextLine();

                                            System.out.print("   -->    Genre: ");
                                            String genre = scanner.nextLine();

                                            int year = Interface.getIntInput(scanner, "   -->    Year: ");

                                            int tracks = Interface.getIntInput(scanner, "   -->    Number of tracks: ");

                                            musicArtist.addAlbum(title, genre, year, tracks);
                                        }
                                        library.addEntry(musicArtist);
                                        Interface.divider2();
                                        Interface.printCentered("=== MUSIC SUCCESFULLY ADDED! ===");
                                        Interface.divider1();
                                        System.out.println("");
                                        break;
                                } // switch mediaChoice
                                films = library.getRecentTitles("Movie");
                                games = library.getRecentTitles("Videogame");
                                music = library.getRecentArtistDiscography("Music Artist");
                                Interface.displayProfile(profile, films, games, music);
                                break;

                            // remove entry
                            case "B" :
                                // display and get anong media type ireremove
                                Interface.divider2();
                                Interface.printCentered("REMOVE MEDIA TYPE");
                                String removeType = Interface.getMediaTypeChoice(scanner);
                                System.out.println("");

                                // cuz string is immutable, magagalaw remove type variable so we make anotha variable for displaying
                                // DISPLAY PURPOSES ONLY
                                String removeLabel;
                                if (removeType.equals("Movie"))
                                    removeLabel = "FILM";

                                else if (removeType.equals("Videogame"))
                                    removeLabel = "GAME";

                                else
                                    removeLabel = "MUSIC ARTIST";


                                System.out.println("");
                                Interface.divider1();
                                Interface.printCentered("=== REMOVING " + removeLabel + " ENTRY ===");
                                Interface.divider2();

                                // display yung napiling media type
                                if (removeType.equals("Movie"))
                                    library.displayMovies();
                                else if (removeType.equals("Videogame"))
                                    library.displayGames();
                                else
                                    library.displayArtists();
                                System.out.println("");

                                // display
                                System.out.print("   -->    Enter " + removeLabel + " title to remove: ");

                                // kunin anong title ireremove, then find it sa library then remove it sa library
                                String removeTitle = scanner.nextLine();
                                Media entryToRemove = library.findEntry(removeType, removeTitle);
                                boolean isRemoved = library.removeEntry(entryToRemove);

                                // display and checking if successful!
                                Interface.divider2();
                                if (isRemoved) {
                                    Interface.printCentered("=== ENTRY SUCCESSFULLY REMOVED! ===");
                                } else {
                                    Interface.printCentered("!!! REMOVE FAILED: ENTRY NOT FOUND IN THIS CATEGORY !!!");
                                }
                                Interface.divider1();
                                System.out.println("");
                                films = library.getRecentTitles("Movie");
                                games = library.getRecentTitles("Videogame");
                                music = library.getRecentArtistDiscography("Music Artist");
                                Interface.displayProfile(profile, films, games, music);
                                break;

                            // filter entry
                            case "C" :
                                // display
                                Interface.divider2();
                                Interface.printCentered("=== DISPLAYING ALL ENTRIES FROM LIBRARY ===\n");
                                library.displayMovies();
                                library.displayGames();
                                library.displayArtists();

                                // kunin if sosort ba by status or media type
                                Interface.divider2();
                                Interface.printCentered("FILTER MEDIA ENTRIES BY");
                                Interface.printCentered("(1) STATUS           (2) MEDIA TYPE");
                                System.out.print("Enter choice: ");
                                String filterChoice = scanner.nextLine();
                                while(!filterChoice.equals("1") && !filterChoice.equals("2"))
                                {
                                    System.out.println("Please enter valid choice.");
                                    System.out.print("Enter choice: ");
                                    filterChoice = scanner.nextLine();
                                }
                                System.out.println("");

                                // dito istostore yung filtered medias (what if ideclare na lahat ng variables na gagamitin sa taas parang sa ccprog1 and 2?)
                                ArrayList<Media> filteredResults;

                                // sort by status
                                if (filterChoice.equals("1"))
                                {
                                    Status filterStatus = Interface.getInputStatus(scanner); // kunin anong status
                                    filteredResults = library.filterByStatus(filterStatus); // return the filtered status
                                }

                                // sort by media type
                                else
                                {
                                    // display/ kunin anong media type ififilter
                                    String filterType= Interface.getMediaTypeChoice(scanner);

                                    // return the filtered media type
                                    filteredResults = library.filterByType(filterType);
                                }

                                //display the filtered list
                                Interface.displayFilteredResults(filteredResults);
                                break;

                            // rate review completed entries
                            case "D" :
                                Interface.divider2();
                                Interface.printCentered("=== RATE/REVIEW FROM COMPLETED LIBRARY ENTRIES ===");
                                ArrayList<Media> completedEntries = library.filterByStatus(Status.COMPLETED);
                                Interface.displayFilteredResults(completedEntries);

                                if(!completedEntries.isEmpty())
                                {
                                    System.out.print("   -->    Enter title of entry to rate/review: ");
                                    String rateTitle = scanner.nextLine();

                                    Media rateEntry = null;
                                    for (Media media : completedEntries)
                                    {
                                        if (media.getTitle().equalsIgnoreCase(rateTitle))
                                            rateEntry = media;
                                    }

                                    // not found
                                    while (rateEntry == null)
                                    {
                                        System.out.println("ERROR: Entry not found among completed entries list.");
                                        System.out.print("   -->    Enter artist/title of entry to rate/review: ");
                                        rateTitle = scanner.nextLine();

                                        for (Media media : completedEntries)
                                        {
                                            if (media.getTitle().equalsIgnoreCase(rateTitle))
                                                rateEntry = media;
                                        }
                                    }

                                    System.out.println("");
                                    Interface.printCentered("MEDIA SUCCESFULLY FOUND");
                                    Interface.divider2();
                                    int rating = Interface.getIntInput(scanner, "   -->    Enter rating (1-5): ");
                                    while (!rateEntry.setRating(rating))
                                    {
                                        rating = Interface.getIntInput(scanner, "   -->    Enter rating (1-5): ");
                                    }

                                    System.out.print("   -->    Enter review: ");
                                    String review = scanner.nextLine();
                                    rateEntry.setReview(review);

                                    Interface.divider2();
                                    Interface.printCentered("=== RATING/REVIEW UPDATED! ===");
                                    Interface.displayFilteredResults(completedEntries);
                                }
                                break;


                            case "E":
                                // display kung anong media type iuupdate yung status
                                Interface.divider2();
                                Interface.printCentered("=== UPDATE STATUS OF MEDIA ENTRY ===");
                                String updateType = Interface.getMediaTypeChoice(scanner);
                                System.out.println("");

                                if (library.filterByType(updateType).isEmpty())
                                    {
                                        Interface.divider2();
                                        Interface.printCentered("ERROR: No " + updateType + " entries found in library.");
                                        Interface.divider1();
                                        System.out.println();
                                        break;
                                    }

                                else
                                {
                                    // display the chosen media type
                                    if (updateType.equals("Movie"))
                                        library.displayMovies();

                                    else if (updateType.equals("Videogame"))
                                        library.displayGames();

                                    else
                                        library.displayArtists();

                                    System.out.println("");

                                    // kunin yung media entry na yun
                                    System.out.print("   -->    Enter title of entry to update: ");
                                    String updateTitle = scanner.nextLine();
                                    Media updateEntry = library.findEntry(updateType, updateTitle); //nakuha na yung media entry

                                    //pag wala nakuha, break
                                    if (updateEntry == null)
                                    {
                                        Interface.divider2();
                                        Interface.printCentered("ERROR: ENTRY NOT FOUND");
                                        Interface.divider1();
                                        System.out.println();
                                        break;
                                    }

                                    // display current status nung napiling media
                                    Interface.divider2();
                                    Interface.printCentered("CURRENT STATUS: " + updateEntry.getStatus().getDisplay());

                                    // get new status
                                    Status newStatus = Interface.getInputStatus(scanner);
                                    boolean statusUpdated = updateEntry.setStatus(newStatus);

                                    // display if naupdate status
                                    Interface.divider2();
                                    if (statusUpdated)
                                        Interface.printCentered("=== STATUS SUCCESSFULLY UPDATED TO " + newStatus.getDisplay().toUpperCase() + "! ===");
                                    else
                                        Interface.printCentered("!!! STATUS NOT UPDATED !!!");
                                    Interface.divider1();
                                    System.out.println("");
                                }                                
                                break;

                            case "F":
                                // display
                                Interface.divider2();
                                Interface.printCentered("=== LOG SONGS LISTENED (MUSIC ARTIST ALBUMS) ===");
                                library.displayArtists();
                                System.out.println("");
                                
                                if (library.filterByType("Music Artist").isEmpty())
                                {
                                    Interface.divider2();
                                    Interface.printCentered("ERROR: No artists in library.");
                                    Interface.divider1();
                                    System.out.println();
                                    break;
                                }

                                // get/search artist media entry
                                System.out.print("   -->    Enter artist name: ");
                                String logArtistTitle = scanner.nextLine();
                                Media logArtistEntry = library.findEntry("Music Artist", logArtistTitle);

                                // break if not found
                                if (logArtistEntry == null)
                                {
                                    Interface.divider2();
                                    Interface.printCentered("ERROR: Artist not found in library.");
                                    Interface.divider1();
                                    System.out.println();
                                    break;
                                }

                                // from the media class, kunin yung music artist class don
                                MusicArtist logArtist = (MusicArtist) logArtistEntry;

                                // display the albums
                                Interface.divider2();
                                Interface.printCentered("ALBUMS FOR " + logArtist.getName().toUpperCase());
                                logArtist.displayAlbums();
                                System.out.println("");

                                // get album  to be updated
                                System.out.print("   -->    Enter album title to update: ");
                                String logAlbumTitle = scanner.nextLine();
                                Album logAlbum = logArtist.findAlbum(logAlbumTitle);

                                //input validation
                                while (logAlbum == null)
                                {
                                    System.out.println("ERROR: Album not found for this artist.");
                                    System.out.print("   -->    Enter album title to update: ");
                                    logAlbumTitle = scanner.nextLine();
                                    logAlbum = logArtist.findAlbum(logAlbumTitle);
                                }

                                // display and get how many songs listened sa album na yun
                                int songsListened = Interface.getIntInput(scanner, "   -->    Enter total songs listened (0-" + logAlbum.getTrackCount() + "): ");
                                while (songsListened < 0 || songsListened > logAlbum.getTrackCount()) //input validation
                                {
                                    songsListened = Interface.getIntInput(scanner, "   -->    Enter total songs listened (0-" + logAlbum.getTrackCount() + "): ");
                                }
                                // set songs listened
                                logAlbum.setSongsListened(songsListened);

                                // display
                                Interface.divider2();
                                if (logAlbum.isCompleted())
                                    Interface.printCentered("=== ALBUM \"" + logAlbum.getTitle() + "\" COMPLETED! ===");
                                else
                                    Interface.printCentered("=== PROGRESS SAVED FOR \"" + logAlbum.getTitle() + "\" ===");

                                if (logArtist.isCompleted())
                                {
                                    Interface.printCentered("ALL ALBUMS COMPLETE! You can now mark " + logArtist.getName() + " as Completed via option (E).");
                                }
                                Interface.divider1();
                                System.out.println("");
                                break;

                            case "X":
                                Interface.divider2();
                                Interface.printCentered("=== LIBRARY SUMMARY ===");
                                Interface.divider2();
                                library.displaySummary();
                                Interface.divider1();
                                break;

                            case "G" :
                                exitLibrary = true;
                                break;
                        } // switch libchoice
                    } // while
                    break;

                case "3":
                    if (!profileCreated)
                    {
                        System.out.println("Please create a profile first.");
                        break;
                    }

                    Interface.displayProfile(profile, films, games, music);
                    break;

                case "2":
                    System.exit(0);

                default:
                    while (!input.equals("1") && !input.equals("2"))
                    {
                        System.out.println("Please enter valid input.");
                        System.out.print("Enter input: ");
                        input = scanner.nextLine();
                    }
            } // switch input
        }// while running

        } // void
}