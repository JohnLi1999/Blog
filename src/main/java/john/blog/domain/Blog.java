package john.blog.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

    private String title;
    private String content;
    private String createdTime;
    private Long viewNumber;
    private Long commentNumber;
    private String cover;
    private String image;
    private String subTitle;
    private String subContent;

    @ManyToOne(targetEntity = Mood.class)
    @JoinColumn(name = "mood_id") // foreign key in the "blog" table
    private Mood mood;

    // When save a blog, we are able to save transient Objects into the database
    @ManyToMany(cascade = CascadeType.PERSIST)
    /* Use @JoinTable to create a junction table between Blog and Topic, Blog is the owner of this association */
    @JoinTable(
            // Name of the junction table
            name = "blog_topic",
            // Column name of the owner side (Blog) in the junction table
            joinColumns = @JoinColumn(name = "blog_id"),
            // Column name of the other side (Topic) in the junction table
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private Set<Topic> topics;

    @OneToMany(targetEntity = BlogComment.class)
    @JoinColumn(name = "blog_id")
    private Set<BlogComment> comments;


    protected Blog() {}

    public Blog(Long id, String title, String content, String createdTime, Long viewNumber, Long commentNumber, String cover, String image, String subTitle, String subContent, Mood mood, Set<Topic> topics, Set<BlogComment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
        this.viewNumber = viewNumber;
        this.commentNumber = commentNumber;
        this.cover = cover;
        this.image = image;
        this.subTitle = subTitle;
        this.subContent = subContent;
        this.mood = mood;
        this.topics = topics;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", viewNumber=" + viewNumber +
                ", commentNumber=" + commentNumber +
                ", cover='" + cover + '\'' +
                ", image='" + image + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", subContent='" + subContent + '\'' +
                ", mood=" + mood +
                ", topics=" + topics +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Long getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Long viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Long getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Long commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<BlogComment> getComments() {
        return comments;
    }

    public void setComments(Set<BlogComment> comments) {
        this.comments = comments;
    }
}
