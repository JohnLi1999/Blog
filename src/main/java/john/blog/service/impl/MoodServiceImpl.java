package john.blog.service.impl;

import john.blog.domain.Album;
import john.blog.domain.Mood;
import john.blog.repository.MoodRepository;
import john.blog.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class MoodServiceImpl implements MoodService {

    @Autowired
    private MoodRepository moodRepository;

    @Override
    public List<Mood> findAllMoods() {
        Iterable<Mood> moods = moodRepository.findAll();
        return (List<Mood>) moods;
    }

    @Override
    public void saveMood(Mood mood) {
        moodRepository.save(mood);
    }

    @Override
    public void deleteMoodById(Long id) {
        moodRepository.deleteById(id);
    }

    @Override
    public Mood findMoodById(Long id) {
        Optional<Mood> mood = moodRepository.findById(id);
        return mood.get();
    }

    @Override
    public List<Mood> findAllMoodsOrderByCreatedTime() {
        return moodRepository.findAllMoodsOrderByCreatedTime();
    }

    @Override
    public List<String> findMoodTimeLine() {
        return moodRepository.findMoodTimeLine();
    }

    @Override
    public List<Mood> findMoodsByKeyword(String keyword) {
        return moodRepository.findMoodsByKeyword(keyword);
    }

    @Override
    public List<Mood> findIndexMoods() {
        return moodRepository.findIndexMoods();
    }
}
