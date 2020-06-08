package john.blog.domain;

import javax.persistence.*;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;
    private String createdTime;

    @ManyToOne(targetEntity = Visitor.class)
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;


    protected Message() {}

    public Message(Long id, String content, String createdTime, Visitor visitor) {
        this.id = id;
        this.content = content;
        this.createdTime = createdTime;
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

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
