package john.blog.repository;

import john.blog.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query( value = "UPDATE user " +
                    "SET username = ?1 " +
                    "WHERE id = 1", // Owner has the id of 1 in the database
            nativeQuery = true)
    @Modifying
    void updateUsername(String username);

    @Query( value = "UPDATE user " +
                    "SET password = ?1 " +
                    "WHERE id = 1", // Owner has the id of 1 in the database
            nativeQuery = true)
    @Modifying
    void updatePassword(String password);

    @Query( value = "UPDATE user " +
                    "SET personal_sign = ?1 " +
                    "WHERE id = 1", // Owner has the id of 1 in the database
            nativeQuery = true)
    @Modifying
    void updatePersonalSign(String personalSign);

    @Query( value = "UPDATE user " +
                    "SET avatar = ?1 " +
                    "WHERE id = 1", // Owner has the id of 1 in the database
            nativeQuery = true)
    @Modifying
    void updateAvatar(String avatar);

    @Query( value = "SELECT * " +
                    "FROM user " +
                    "WHERE username = ?1",
            nativeQuery = true)
    User findUserByUsername(String username);
}
