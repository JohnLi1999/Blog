package john.blog.service.impl;

import john.blog.domain.Visitor;
import john.blog.repository.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class SocialVisitorService implements SocialUserDetailsService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        // Search a visitor through his/her userId
        Optional<Visitor> optional = visitorRepository.findById(Long.valueOf(userId));
        Visitor visitor = optional.get();

        session.setAttribute("visitor", visitor);

        if (visitor == null) {
            throw new UsernameNotFoundException(userId);
        }

        return new SocialUser(
            visitor.getUsername(),
            passwordEncoder.encode(visitor.getPassword()),
            true,
            true,
            true,
            true,
            AuthorityUtils.commaSeparatedStringToAuthorityList("VISITOR")
        );
    }
}
