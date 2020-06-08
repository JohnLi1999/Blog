package john.blog.service.impl;

import john.blog.domain.Blog;
import john.blog.repository.BlogRepository;
import john.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public List<Blog> findAllBlogs() {
        return (List<Blog>) blogRepository.findAll();
    }

    @Override
    @Transactional
    public Blog findBlogById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        blog.get().setViewNumber(blog.get().getViewNumber() + 1);
        return blog.get();
    }

    @Override
    public List<Blog> findAllBlogsOrderByCreatedTime() {
        return blogRepository.findAllBlogsOrderByCreatedTime();
    }

    @Override
    public List<String> findBlogTimeLine() {
        return blogRepository.findBlogTimeLine();
    }

    @Override
    public List<Blog> findBlogsByKeyword(String keyword) {
        return blogRepository.findBlogsByKeyword(keyword);
    }

    @Override
    public List<Blog> findIndexBlogs() {
        return blogRepository.findIndexBlogs();
    }
}
