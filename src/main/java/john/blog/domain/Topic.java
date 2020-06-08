package john.blog.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String topic;

    /* 1. Topic Class does not maintain the many-to-many association with Blog Class
       2. Blog Class does it with a set of Topics (Set<Topic> topics).
       3. Names should match!   */
    @ManyToMany(mappedBy = "topics")
    private Set<Blog> blogs;


    protected Topic() {}

    public Topic(String topic, Set<Blog> blogs) {
        this.topic = topic;
        this.blogs = blogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }
}
