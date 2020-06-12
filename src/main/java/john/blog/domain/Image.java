package john.blog.domain;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

    private String image;

    @ManyToOne(targetEntity = Album.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;


    protected Image() {}

    public Image(String image, Album album) {
        this.image = image;
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
