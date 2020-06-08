package john.blog.dto;

/* Reorganize blogs, albums, and moods obtained from the backend.
   Display proper information on the front end page */
public class TimeLineObject {

    private String createdTime;
    private String title;
    private String href;
    private String other;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
