package john.blog.service;

import john.blog.domain.AlbumComment;
import john.blog.dto.PageBean;
import org.springframework.stereotype.Service;

@Service
public interface AlbumCommentService {

    /** Save a new AlbumComment */
    void saveAlbumComment(AlbumComment albumComment);

    /** Obtain all comments under the specified album (with Pagination) */
    PageBean getAlbumCommentPageBean(Long albumId, Integer currentPage);
}
