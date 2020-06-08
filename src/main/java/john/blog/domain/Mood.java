package john.blog.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Mood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

    private String title;
    private String content;
    private String image;
    private String createdTime;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id") // foreign key in the "mood" table
    private User user;

    @OneToMany(targetEntity = Blog.class)
    @JoinColumn(name = "mood_id") // foreign key in the "blog" table
    private Set<Blog> blogs;


    protected Mood() {}

    public Mood(String title, String content, String image, String createdTime, User user, Set<Blog> blogs) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.createdTime = createdTime;
        this.user = user;
        this.blogs = blogs;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }
}
