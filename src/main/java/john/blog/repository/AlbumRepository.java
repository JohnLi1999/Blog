package john.blog.repository;

import john.blog.domain.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Long> {

    @Query( value = "SELECT * " +
            "FROM album " +
            "ORDER BY created_time DESC",
            nativeQuery = true)
    List<Album> findAllAlbumsOrderByCreatedTime();

    @Query( value = "SELECT created_time " +
                    "FROM album " +
                    "ORDER BY created_time DESC",
            nativeQuery = true)
    List<String> findAlbumTimeLine();

    @Query( value = "SELECT * " +
                    "FROM album " +
                    "WHERE title LIKE %?1% " +
                    "OR content LIKE %?1% " +
                    "ORDER BY created_time DESC",
            nativeQuery = true)
    List<Album> findAlbumsByKeyword(String keyword);

    @Query( value = "SELECT * " +
                    "FROM album " +
                    "ORDER BY created_time DESC " +
                    "LIMIT 0, 3",
            nativeQuery = true)
    List<Album> findIndexAlbums();
}
