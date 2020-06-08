package john.blog.repository;

import john.blog.domain.AlbumComment;
import john.blog.domain.BlogComment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumCommentRepository extends CrudRepository<AlbumComment, Long> {

    @Query( value = "SELECT count(*) " +
                    "FROM album_comment " +
                    "WHERE album_id = ?1",
            nativeQuery = true)
    Integer findTotalCommentsCountById(Long albumId);

    @Query( value = "SELECT * " +
                    "FROM album_comment " +
                    "WHERE album_id = ?1 " +
                    "LIMIT ?2, ?3",
            nativeQuery = true)
    List<AlbumComment> getCommentPageListById(Long albumId, Integer startPosition, Integer pageSize);
}
