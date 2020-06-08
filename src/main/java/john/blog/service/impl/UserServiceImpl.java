package john.blog.service.impl;

import john.blog.domain.User;
import john.blog.repository.UserRepository;
import john.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getOwner() {
        // Owner has the id of 1 in the database
        Optional<User> owner = userRepository.findById(1L);
        return owner.get();
    }

    @Override
    @Transactional // Need to handle Transactions when updating database
    public void updateUsername(String username) {
        userRepository.updateUsername(username);
    }

    @Override
    @Transactional // Need to handle Transactions when updating database
    public void updatePassword(String password) {
        userRepository.updatePassword(password);
    }

    @Override
    @Transactional // Need to handle Transactions when updating database
    public void updatePersonalSign(String personalSign) {
        userRepository.updatePersonalSign(personalSign);
    }

    @Override
    @Transactional // Need to handle Transactions when updating database
    public void updateAvatar(String avatar) {
        userRepository.updateAvatar(avatar);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


}
