package john.blog.repository;

import john.blog.domain.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Long> {

    @Query( value = "SELECT * " +
                    "FROM topic " +
                    "WHERE topic = ?1",
            nativeQuery = true)
    Topic findTopicByTopic(String topic);
}
