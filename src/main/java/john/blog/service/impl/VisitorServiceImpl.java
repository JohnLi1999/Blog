package john.blog.service.impl;

import john.blog.domain.Visitor;
import john.blog.repository.VisitorRepository;
import john.blog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    public Visitor saveVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

    @Override
    public Visitor findVisitorByUsername(String username) {
        return visitorRepository.findVisitorByUsername(username);
    }
}
