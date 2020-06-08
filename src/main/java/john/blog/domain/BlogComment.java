package john.blog.domain;

import javax.persistence.*;

@Entity
public class BlogComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String createdTime;

    @ManyToOne(targetEntity = Blog.class)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @ManyToOne(targetEntity = Visitor.class)
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;


    protected BlogComment() {}

    public BlogComment(Long id, String content, String createdTime, Blog blog, Visitor visitor) {
        this.id = id;
        this.content = content;
        this.createdTime = createdTime;
        this.blog = blog;
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

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
