package john.blog.service;

import john.blog.domain.Visitor;
import org.springframework.stereotype.Service;

@Service
public interface VisitorService {

    /** Save the visitor's information */
    Visitor saveVisitor(Visitor visitor);

    /** Find the visitor through his/her username */
    Visitor findVisitorByUsername(String username);
}
