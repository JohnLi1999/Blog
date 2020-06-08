package john.blog.domain;

import javax.persistence.*;

@Entity
public class AlbumComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String createdTime;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToOne(targetEntity = Visitor.class)
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;


    protected AlbumComment() {}

    public AlbumComment(Long id, String content, String createdTime, Album album, Visitor visitor) {
        this.id = id;
        this.content = content;
        this.createdTime = createdTime;
        this.album = album;
        this.visitor = visitor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
