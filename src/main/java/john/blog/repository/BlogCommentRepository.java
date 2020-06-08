package john.blog.repository;

import john.blog.domain.BlogComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogCommentRepository extends CrudRepository<BlogComment, Long> {

    @Query( value = "SELECT count(*) " +
                    "FROM blog_comment " +
                    "WHERE blog_id = ?1",
            nativeQuery = true)
    Integer findTotalCommentsCountById(Long blogId);

    @Query( value = "SELECT * " +
                    "FROM blog_comment " +
                    "WHERE blog_id = ?1 " +
                    "LIMIT ?2, ?3",
            nativeQuery = true)
    List<BlogComment> getCommentPageListById(Long blogId, Integer startPosition, Integer pageSize);
}
