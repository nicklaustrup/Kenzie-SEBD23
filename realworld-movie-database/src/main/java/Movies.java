import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Movies {
    @com.fasterxml.jackson.annotation.JsonProperty("imageUrl")
    private String imageUrl;
    @com.fasterxml.jackson.annotation.JsonProperty("vote_count")
    private int vote_count;
    @com.fasterxml.jackson.annotation.JsonProperty("vote_average")
    private double vote_average;
    @com.fasterxml.jackson.annotation.JsonProperty("video")
    private boolean video;
    @com.fasterxml.jackson.annotation.JsonProperty("title")
    private String title;
    @com.fasterxml.jackson.annotation.JsonProperty("release_date")
    private String release_date;
    @com.fasterxml.jackson.annotation.JsonProperty("poster_path")
    private String poster_path;
    @com.fasterxml.jackson.annotation.JsonProperty("popularity")
    private double popularity;
    @com.fasterxml.jackson.annotation.JsonProperty("overview")
    private String overview;
    @com.fasterxml.jackson.annotation.JsonProperty("original_title")
    private String original_title;
    @com.fasterxml.jackson.annotation.JsonProperty("original_language")
    private String original_language;
    @com.fasterxml.jackson.annotation.JsonProperty("id")
    private int id;
    @com.fasterxml.jackson.annotation.JsonProperty("genre_ids")
    private List<Integer> genre_ids;
    @com.fasterxml.jackson.annotation.JsonProperty("backdrop_path")
    private String backdrop_path;
    @com.fasterxml.jackson.annotation.JsonProperty("adult")
    private boolean adult;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public boolean getVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean getAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }
}
