package john.blog.service;

import john.blog.domain.Album;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AlbumService {

    /** Save a new album */
    void saveAlbum(Album album);

    /** Find all the albums */
    List<Album> findAllAlbums();

    /** Find an album through its ID */
    Album findAlbumById(Long id);

    /** Find all the albums and sort the returned albums by their created time */
    List<Album> findAllAlbumsOrderByCreatedTime();

    /** Find created time of all albums */
    List<String> findAlbumTimeLine();

    /** Find all albums that contain the specified keyword */
    List<Album> findAlbumsByKeyword(String keyword);

    /** Find the latest three albums */
    List<Album> findIndexAlbums();
}
