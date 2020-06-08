package john.blog.service.impl;

import io.netty.handler.codec.http.HttpScheme;
import john.blog.domain.User;
import john.blog.domain.Visitor;
import john.blog.service.UserService;
import john.blog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SecurityUserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession seesion;

    /* The method will be called when a user logs in with his/her username */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Admin Login
        User user  = userService.findUserByUsername(username);
        if (user != null) {
            seesion.setAttribute("user", user);
            return new SocialUser(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN")
            );
        }

        // Visitor Login
        Visitor visitor = visitorService.findVisitorByUsername(username);
        if (visitor != null) {
            seesion.setAttribute("visitor", visitor);
            return new SocialUser(
                visitor.getUsername(),
                passwordEncoder.encode(visitor.getPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList("VISITOR")
            );
        }

        throw new UsernameNotFoundException("Username does not exist!");
    }
}
