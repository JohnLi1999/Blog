package john.blog.service.impl;

import john.blog.domain.AlbumComment;
import john.blog.domain.BlogComment;
import john.blog.dto.PageBean;
import john.blog.repository.AlbumCommentRepository;
import john.blog.service.AlbumCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumCommentServiceImpl implements AlbumCommentService {

    @Autowired
    private AlbumCommentRepository albumCommentRepository;

    @Override
    public void saveAlbumComment(AlbumComment albumComment) {
        albumCommentRepository.save(albumComment);
    }

    @Override
    public PageBean getAlbumCommentPageBean(Long albumId, Integer currentPage) {
        // Create a PageBean object for the comments of the specified album
        Integer totalCount = albumCommentRepository.findTotalCommentsCountById(albumId);
        PageBean pageBean = new PageBean(currentPage, PageBean.DEFAULT_ALBUM_COMMENT_PAGE_SIZE, totalCount);

        // Get the list of all comments in the specified album in current page
        List<AlbumComment> list = albumCommentRepository.getCommentPageListById(albumId, pageBean.startPosition(), pageBean.getPageSize());
        pageBean.setList(list);

        return pageBean;
    }
}
