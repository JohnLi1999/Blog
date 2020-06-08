package john.blog.service.impl;

import john.blog.domain.BlogComment;
import john.blog.dto.PageBean;
import john.blog.repository.BlogCommentRepository;
import john.blog.service.BlogCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogCommentServiceImpl implements BlogCommentService {

    @Autowired
    private BlogCommentRepository blogCommentRepository;

    @Override
    public void saveBlogComment(BlogComment blogComment) {
        blogCommentRepository.save(blogComment);
    }

    @Override
    public PageBean getBlogCommentPageBean(Long blogId, Integer currentPage) {
        // Create a PageBean object for the comments of the specified blog
        Integer totalCount = blogCommentRepository.findTotalCommentsCountById(blogId);
        PageBean pageBean = new PageBean(currentPage, PageBean.DEFAULT_BLOG_COMMENT_PAGE_SIZE, totalCount);

        // Get the list of all comments in the specified blog in current page
        List<BlogComment> list = blogCommentRepository.getCommentPageListById(blogId, pageBean.startPosition(), pageBean.getPageSize());
        pageBean.setList(list);

        return pageBean;
    }
}
