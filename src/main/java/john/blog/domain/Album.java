package john.blog.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

    private String title;
    private String content;
    private String cover;
    private String createdTime;
    private Long imageNumber;

    @OneToMany(targetEntity = Image.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Set<Image> images;

    @OneToMany(targetEntity = AlbumComment.class)
    @JoinColumn(name = "album_id")
    private Set<AlbumComment> comments;


    protected Album() {}

    public Album(Long id, String title, String content, String cover, String createdTime, Long imageNumber, Set<Image> images, Set<AlbumComment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.cover = cover;
        this.createdTime = createdTime;
        this.imageNumber = imageNumber;
        this.images = images;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", cover='" + cover + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", imageNumber=" + imageNumber +
                ", images=" + images +
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Long getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(Long imageNumber) {
        this.imageNumber = imageNumber;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<AlbumComment> getComments() {
        return comments;
    }

    public void setComments(Set<AlbumComment> comments) {
        this.comments = comments;
    }
}
