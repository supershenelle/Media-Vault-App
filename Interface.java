import java.util.ArrayList;
import java.util.Scanner;

public class Interface {

    /**
     * displays media vault title
     */
    public static void displayTitle()
    {
        System.out.println("\n\n");
        System.out.println("+====================================================================================+");
        System.out.println("  ███╗   ███╗███████╗██████╗ ██╗ █████╗    ██╗   ██╗ █████╗ ██╗   ██╗██╗  ████████╗");
        System.out.println("  ████╗ ████║██╔════╝██╔══██╗██║██╔══██╗   ██║   ██║██╔══██╗██║   ██║██║  ╚══██╔══╝");
        System.out.println("  ██╔████╔██║█████╗  ██║  ██║██║███████║   ██║   ██║███████║██║   ██║██║     ██║   ");
        System.out.println("  ██║╚██╔╝██║██╔══╝  ██║  ██║██║██╔══██║   ╚██╗ ██╔╝██╔══██║██║   ██║██║     ██║   ");
        System.out.println("  ██║ ╚═╝ ██║███████╗██████╔╝██║██║  ██║    ╚████╔╝ ██║  ██║╚██████╔╝███████╗██║   ");
        System.out.println("  ╚═╝     ╚═╝╚══════╝╚═════╝ ╚═╝╚═╝  ╚═╝     ╚═══╝  ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚═╝   ");
        System.out.println("+====================================================================================+\n");
    }

    /**
     * divider
     */
    public static void divider1()
    {
        System.out.println("+====================================================================================+");
    }

    /**
     * 2nd divider
     */
    public static void divider2()
    {
        System.out.println("+------------------------------------------------------------------------------------+");
    }

    /**
     * centers text
     * @param text text you want to center
     */
    public static void printCentered(String text)
    {
        int totalPad = 86 - text.length();
        int padLeft = totalPad / 2;
        int padRight = totalPad - padLeft;

        System.out.println(" ".repeat(Math.max(padLeft, 0)) + text + " ".repeat(Math.max(padRight, 0)));
    }

    //display profile function
    /**
     * displays profile
     * @param profile profile to display
     * @param films films to display
     * @param games games to display
     * @param music music to display
     */
    public static void displayProfile(Profile profile, String[] films, String[] games, String[] music)
    {
        System.out.println("");
        divider1();
        printCentered("=== Y O U R   P R O F I L E ===");
        divider1();
        printCentered("USERNAME: @" + profile.getUsername());
        divider2();
        printCentered("DISPLAY NAME: " + profile.getDisplayName());
        printCentered("BIO: " + profile.getBio());
        divider1();
        System.out.println("");

        printCentered("### -- MOST RECENT FILM ACTIVITY -- ###");
        printBoxes(films);

        printCentered("### -- MOST RECENT VIDEO GAME ACTIVITY -- ###");
        printBoxes(games);

        printCentered("### -- MOST RECENT ARTIST DISCOGRAPHY ACTIVITY -- ###");
        printBoxes(music);

        divider1();
    }

    // display library para di paulit ulit sa switch

    /**
     * displays library menu and gets input for menu
     * @param scanner input
     * @return input for menu choice
     */
    public static String libraryMenu(Scanner scanner)
    {
        printCentered("----- YOUR LIBRARY -----\n");
        System.out.println("   -->    (A) ADD MEDIA                  -->    (E) UPDATE MEDIA ENTRIES STATUS");
        System.out.println("   -->    (B) REMOVE MEDIA               -->    (F) UPDATE ARTIST DISCOGRAPHY LOGS");
        System.out.println("   -->    (C) DISPLAY/FILTER ENTRIES     -->    (G) BACK TO MAIN NAVIGATION/PROFILE");
        System.out.println("   -->    (D) RATE AND REVIEW COMPLETED ENTRIES\n");
    
        System.out.println("   -->    (X) VIEW LIBRARY SUMMARY (TOTAL ENTRIES AND AVERAGE RATINGS)");
    
        System.out.println("");
        System.out.print("Enter choice: ");
        String libChoice = scanner.nextLine();
        while(!libChoice.equalsIgnoreCase("A") && !libChoice.equalsIgnoreCase("B") && !libChoice.equalsIgnoreCase("C") && !libChoice.equalsIgnoreCase("D") && !libChoice.equalsIgnoreCase("E") && !libChoice.equalsIgnoreCase("F") && !libChoice.equalsIgnoreCase("G") && !libChoice.equalsIgnoreCase("X"))
        {
            System.out.println("Please enter valid choice.");
            System.out.print("Enter choice: ");
            libChoice = scanner.nextLine();
        }
        System.out.println("");
        return libChoice.toUpperCase();
    }

    // update status

    /**
     * display status menu and get input for status
     * @param scanner input scanner
     * @return status based on user's input
     */
    public static Status getInputStatus(Scanner scanner)
    {
        System.out.println("   -->    STATUS:\n      -- (1) Planning\n      -- (2) In Progress\n      -- (3) Finished");
        System.out.print("Enter status choice: ");
        String choice = scanner.nextLine();
        while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3"))
        {
            System.out.println("Please enter valid choice.");
            System.out.print("Enter status choice: ");
            choice = scanner.nextLine();
        }
        return Status.fromChoice(choice);

    }

    // eto naman for adding media entry cuz bawal completed agad

    /**
     * display status menu(when initially adding media entry) and get input
     * @param scanner input scanner
     * @return status based on user's input
     */
    public static Status getInputStatusAddMedia(Scanner scanner)
    {
        System.out.println("   -->    STATUS:\n      -- (1) Planning\n      -- (2) In Progress");
        System.out.print("Enter status choice: ");
        String choice = scanner.nextLine();
        while(!choice.equals("1") && !choice.equals("2"))
        {
            System.out.println("Please enter valid choice.");
            System.out.print("Enter status choice: ");
            choice = scanner.nextLine();
        }
        return Status.fromChoice(choice);

    }

    // kasi yung scanner.nextInt() nagkakaroon ng issue na magtthrow so eto nlng.

    /**
     * prompt user until it gives a valid integer
     * @param scanner user input
     * @param prompt message to display
     * @return int entered by user
     */
    public static int getIntInput(Scanner scanner, String prompt)
    {
        System.out.print(prompt);
        while(!scanner.hasNextInt())
        {
            System.out.println("ERROR: Please enter valid input.");
            scanner.nextLine();
            System.out.print(prompt);
        }

        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    /**
     * return media type based from the menu interface
     * @param choice user input
     * @return media type based on choice
     */
    public static String mediaTypeFromChoice(String choice)
    {
        switch(choice)
        {
            case "1":
                return "Movie";
            case "2":
                return "Videogame";
            case "3":
                return "Music Artist";
        }
        return null;
    }

    /**
     * displays the filtered media entries
     * @param results contains the filtered media entries to display
     */
    public static void displayFilteredResults(ArrayList<Media> results)
    {
        divider1();
        printCentered("=== RESULTS (" + results.size() + " FOUND) ===");
        divider2();

        if (results.isEmpty())
        {
            printCentered("[ No matching entries found ]");
        }
        else
        {
            for (Media media : results)
            {
                System.out.println(media.displayInfo());
                divider2();
            }
        }
        divider1();
        System.out.println("");
    }

    /**
     * prints three boxes
     * @param media contains of up to three titles to display inside the box
     */
    public static void printBoxes(String[] media)
    {
        int width = 20;
        String border = "+" + "-".repeat(width + 4) + "+";

        String t1 = getTitle(media, 0);
        String t2 = getTitle(media, 1);
        String t3 = getTitle(media, 2);

        // if ever mag exceed yung title sa box
        if (t1.length() > width) t1 = t1.substring(0, width - 3) + "...";
        if (t2.length() > width) t2 = t2.substring(0, width - 3) + "...";
        if (t3.length() > width) t3 = t3.substring(0, width - 3) + "...";

        // para dun sa empty space heh
        String blank = " ".repeat(width);

        System.out.println(" " + border + "   " + border + "   " + border);
        System.out.println(" |  " + blank + "  |   |  " + blank + "  |   |  " + blank + "  |");
        System.out.printf(" |  %-" + width + "s  |   |  %-" + width + "s  |   |  %-" + width + "s  |%n", t1, t2, t3);
        System.out.println(" |  " + blank + "  |   |  " + blank + "  |   |  " + blank + "  |");
        System.out.println(" " + border + "   " + border + "   " + border);
        System.out.println("");
    }

    // if ever wala pa favorites so maging null muna sya

    /**
     * get title based on index
     * @param filmTitle title to read from
     * @param index index to retrieve
     * @return title at the given index
     */
    private static String getTitle(String[] filmTitle, int index)
    {
        // diba 0-2 lang so magsstop na sya pag sobra na
        if (index >= filmTitle.length || filmTitle[index] == null) {
            return "";
        }
        return filmTitle[index];
    }

    /**
     * display media type menu selection and get input
     * @param scanner user scanner
     * @return selected media type
     */
    public static String getMediaTypeChoice(Scanner scanner)
    {
        printCentered("(1) FILMS           (2) GAMES              (3) DISCOGRAPHY");
        System.out.print("Enter choice: ");
        String choice = scanner.nextLine();
        while(!choice.equals("1") && !choice.equals("2") && !choice.equals("3"))
        {
            System.out.println("Please enter valid choice.");
            System.out.print("Enter choice: ");
            choice = scanner.nextLine();
        }
        return mediaTypeFromChoice(choice);
    }
}
