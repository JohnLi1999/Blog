package john.blog.service;

import john.blog.domain.BlogComment;
import john.blog.dto.PageBean;
import org.springframework.stereotype.Service;

@Service
public interface BlogCommentService {

    /** Save a new BlogComment */
    void saveBlogComment(BlogComment blogComment);

    /** Obtain all comments under the specified blog (with Pagination) */
    PageBean getBlogCommentPageBean(Long blogId, Integer currentPage);

}
