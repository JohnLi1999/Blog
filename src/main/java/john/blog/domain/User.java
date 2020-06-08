package john.blog.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // primary key

    private String username;
    private String password;
    private String personalSign;
    private String avatar;
    private String createdTime;

    @OneToMany(targetEntity = Mood.class)
    @JoinColumn(name = "user_id") // foreign key in the "mood" table
    private Set<Mood> moods;


    protected User() {}

    public User(String username, String password, String personalSign, String avatar, String createdTime, Set<Mood> moods) {
        this.username = username;
        this.password = password;
        this.personalSign = personalSign;
        this.avatar = avatar;
        this.createdTime = createdTime;
        this.moods = moods;
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

    public String getPersonalSign() {
        return personalSign;
    }

    public void setPersonalSign(String personalSign) {
        this.personalSign = personalSign;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Set<Mood> getMoods() {
        return moods;
    }

    public void setMoods(Set<Mood> moods) {
        this.moods = moods;
    }
}
