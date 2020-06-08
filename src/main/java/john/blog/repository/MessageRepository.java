package john.blog.repository;

import john.blog.domain.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query( value = "SELECT count(*) " +
                    "FROM message",
            nativeQuery = true)
    Integer findTotalMessagesCount();

    @Query( value = "SELECT * " +
                    "FROM message " +
                    "LIMIT ?1, ?2",
            nativeQuery = true)
    List<Message> findMessagePageList(Integer startPosition, Integer pageSize);
}
