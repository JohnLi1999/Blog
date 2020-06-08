package john.blog.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

    private String username;
    private String password;
    private String image;

    @OneToMany(targetEntity = Message.class)
    @JoinColumn(name = "visitor_id")
    private Set<Message> messages;

    @OneToMany(targetEntity = BlogComment.class)
    @JoinColumn(name = "visitor_id")
    private Set<BlogComment> blogComments;

    @OneToMany(targetEntity = AlbumComment.class)
    @JoinColumn(name = "visitor_id")
    private Set<AlbumComment> albumComments;


    protected Visitor() {}

    public Visitor(Long id, String username, String password, String image, Set<Message> messages, Set<BlogComment> blogComments, Set<AlbumComment> albumComments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.image = image;
        this.messages = messages;
        this.blogComments = blogComments;
        this.albumComments = albumComments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<BlogComment> getBlogComments() {
        return blogComments;
    }

    public void setBlogComments(Set<BlogComment> blogComments) {
        this.blogComments = blogComments;
    }

    public Set<AlbumComment> getAlbumComments() {
        return albumComments;
    }

    public void setAlbumComments(Set<AlbumComment> albumComments) {
        this.albumComments = albumComments;
    }
}
