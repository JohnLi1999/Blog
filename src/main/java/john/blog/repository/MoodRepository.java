package john.blog.repository;

import john.blog.domain.Mood;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoodRepository extends CrudRepository<Mood, Long> {

    @Query( value = "SELECT * " +
                    "FROM mood " +
                    "ORDER BY created_time DESC",
            nativeQuery = true)
    List<Mood> findAllMoodsOrderByCreatedTime();

    @Query( value = "SELECT created_time " +
                    "FROM mood " +
                    "ORDER BY created_time DESC",
            nativeQuery = true)
    List<String> findMoodTimeLine();

    @Query( value = "SELECT * " +
                    "FROM mood " +
                    "WHERE title LIKE %?1% " +
                    "OR content LIKE %?1% " +
                    "ORDER BY created_time DESC",
            nativeQuery = true)
    List<Mood> findMoodsByKeyword(String keyword);

    @Query( value = "SELECT * " +
                    "FROM mood " +
                    "ORDER BY created_time DESC " +
                    "LIMIT 0, 3",
            nativeQuery = true)
    List<Mood> findIndexMoods();
}
