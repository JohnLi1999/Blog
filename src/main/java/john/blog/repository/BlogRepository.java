package john.blog.repository;

import john.blog.domain.Blog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Long> {

    @Query( value = "SELECT * " +
                    "FROM blog " +
                    "ORDER BY created_time DESC",
            nativeQuery = true)
    List<Blog> findAllBlogsOrderByCreatedTime();

    @Query( value = "SELECT created_time " +
                    "FROM blog " +
                    "ORDER BY created_time DESC",
            nativeQuery = true)
    List<String> findBlogTimeLine();

    @Query( value = "SELECT * " +
                    "FROM blog " +
                    "WHERE title LIKE %?1% " +
                    "OR content LIKE %?1% " +
                    "ORDER BY created_time DESC",
            nativeQuery = true)
    List<Blog> findBlogsByKeyword(String keyword);

    @Query( value = "SELECT * " +
                    "FROM blog " +
                    "ORDER BY created_time DESC " +
                    "LIMIT 0, 3 ",
            nativeQuery = true)
    List<Blog> findIndexBlogs();
}
