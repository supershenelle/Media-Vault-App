/**
 * represents all shared media behavior and where ratings and reviews happen
 */
public abstract class Media {
    private final String type;
    private Status status;
    private int rating;
    private String review;

    /**
     * constructs shared media state
     * @param type media type label
     * @param status initial status
     */
    protected Media(String type, Status status)
    {
        this.type = type;
        this.status = status;
        rating = 0;
        review = "";
    }

    /**
     * get media type
     * @return media type
     */
    public String getType()
    {
        return type;
    }

    /**
     * get the title/name of the media entry
     * @return title/name of the media entry
     */
    public abstract String getTitle();

    /**
     * get media status
     * @return media status
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * checks if this media entry meets its subtype's requirements to be marked completed
     * default: no extra conditions
     * @return true if it can be marked completed
     */
    protected boolean canComplete()
    {
        return true;
    }

    /**
     * set status of the media entry.
     * if media type is music artist, cant set to completed unless all albums in the artist's discography is completed
     * @param status is the new status
     * @return true if status is set, otherwise print error message and return false
     */
    public boolean setStatus(Status status) {
    if (status == Status.COMPLETED && !canComplete()) 
    {
        System.out.println("Cannot mark as completed yet.");
        return false;
    }
    this.status = status;
    return true;
    }

    /**
     * set rating for media entry
     * @param rating new rating
     * @return true if rating is set, otherwise return false and print error message
     */
    public boolean setRating(int rating)
    {
        if (rating < 1 || rating > 5)
        {
            System.out.println("ERROR: Input a number between 1 to 5 only");
            return false;
        }

        this.rating = rating;
        return true;
    }

    /**
     * get rating of media entry
     * @return rating of media entry
     */
    public int getRating()
    {
        return rating;
    }

    /**
     * set review of media entry
     * @param review is the new review of media entry
     */
    public void setReview(String review)
    {
        this.review = review;
    }

    /**
     * get details specific to the media subtype
     * @return subtype specific details
     */
    protected abstract String getSpecificInfo();

    /**
     * get review of the media entry
     * @return review of media entry
     */
    public String getReview()
    {
        return review;
    }

    /**
     * display a summary of media entry
     * @return summary of media entry
     */
    public String displayInfo()
    {
        String info = "";

        info += "Type: " + type + "\n";
        info += "Status: " + status + "\n";
        info += getSpecificInfo();

        if(status == Status.COMPLETED)
        {
            info += "\nRating: " + rating;
            info += "\nReview: " + review;
        }
        return info;
    }
}