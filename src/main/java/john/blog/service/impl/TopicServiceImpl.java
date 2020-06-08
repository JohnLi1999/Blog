package john.blog.service.impl;

import john.blog.domain.Topic;
import john.blog.repository.TopicRepository;
import john.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public Topic saveTopic(Topic topic) {
        Topic savedTopic = topicRepository.save(topic);
        return savedTopic;
    }

    @Override
    public Topic findTopicByTopic(String topic) {
        Topic returnedTopic = topicRepository.findTopicByTopic(topic);
        return returnedTopic;
    }

    @Override
    public List<Topic> findAllTopics() {
        return (List<Topic>) topicRepository.findAll();
    }

    @Override
    public Topic findTopicById(Long id) {
        Optional<Topic> topic = topicRepository.findById(id);
        return topic.get();
    }
}
