import java.util.ArrayList;
import java.util.List;

/**
 * represent the user's profile
 */
public class Profile {
    private String username;
    private String displayName;
    private String bio;
    private Library library;

    /**
     * construct a profile and creates a library inside
     * @param username is the username of the profile
     * @param displayName display name of the profile
     * @param bio bio of the profile
     */
    public Profile(String username, String displayName, String bio)
    {
        this.username = username;
        this.displayName = displayName;
        this.bio = bio;
        this.library = new Library();
    }

    /**
     * get username of profile
     * @return username of profile
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * get display name of profile
     * @return display name
     */
    public String getDisplayName()
    {
        return displayName;
    }

    /**
     * get bio of profile
     * @return bio
     */
    public String getBio()
    {
        return bio;
    }

    /**
     * get library of profile
     * @return library
     */
    public Library getLibrary()
    {
        return library;
    }

}