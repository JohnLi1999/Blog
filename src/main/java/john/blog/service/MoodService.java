package john.blog.service;

import john.blog.domain.Album;
import john.blog.domain.Mood;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MoodService {

    /** Save a new mood */
    void saveMood(Mood mood);

    /** Find all the moods */
    List<Mood> findAllMoods();

    /** Delete a mood */
    void deleteMoodById(Long id);

    /** Find a mood through its ID */
    Mood findMoodById(Long id);

    /** Find all the moods and sort the returned moods by their created time */
    List<Mood> findAllMoodsOrderByCreatedTime();

    /** Find created time of all moods */
    List<String> findMoodTimeLine();

    /** Find all moods that contain the specified keyword */
    List<Mood> findMoodsByKeyword(String keyword);

    /** Find the latest three moods */
    List<Mood> findIndexMoods();
}
