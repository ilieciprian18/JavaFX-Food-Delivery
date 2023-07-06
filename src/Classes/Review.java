package Classes;

public class Review {

    protected int idRestaurant;
    protected int reviewRate;
    protected String usernameReview;
    protected String reviewDescription;

    public Review(int idRestaurant, int reviewRate, String usernameReview, String reviewDescription) {
        this.idRestaurant = idRestaurant;
        this.reviewRate = reviewRate;
        this.usernameReview = usernameReview;
        this.reviewDescription = reviewDescription;
    }

    public int getReviewRate() {
        return reviewRate;
    }

    public String getUsernameReview() {
        return usernameReview;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }
}
