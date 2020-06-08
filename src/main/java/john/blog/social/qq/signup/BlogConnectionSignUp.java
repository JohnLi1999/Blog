package john.blog.social.qq.signup;

import john.blog.domain.Visitor;
import john.blog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class BlogConnectionSignUp implements ConnectionSignUp {

    @Autowired
    private VisitorService visitorService;

    // Create a new visitor and return the unique identifier of the user through his/her information
    @Override
    public String execute(Connection<?> connection) {
        Visitor visitor = new Visitor(null, connection.getDisplayName(), "123", connection.getImageUrl(), new HashSet<>(), new HashSet<>(), new HashSet<>());
        // Save visitor information into the database
        visitor = visitorService.saveVisitor(visitor);

        // Id is the unique identifier
        return visitor.getId().toString();
    }
}
