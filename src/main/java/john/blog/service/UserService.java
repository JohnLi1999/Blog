package john.blog.service;

import john.blog.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /** Obtain blog owner's information */
    User getOwner();

    /** Update username */
    void updateUsername(String username);

    /** Update password */
    void updatePassword(String password);

    /** Update personal sign */
    void updatePersonalSign(String personalSign);

    /** Update avatar */
    void updateAvatar(String avatar);

    /** Search for a user through his/her username*/
    User findUserByUsername(String username);
}
