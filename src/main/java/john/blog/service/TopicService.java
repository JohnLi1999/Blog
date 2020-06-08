package john.blog.service;

import john.blog.domain.Topic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopicService {

    /** Save a new Topic */
    Topic saveTopic(Topic topic);

    /** Find the Topic through its name */
    Topic findTopicByTopic(String topic);

    /** Find all the Topics */
    List<Topic> findAllTopics();

    /** Find a Topic through its ID */
    Topic findTopicById(Long id);
}
