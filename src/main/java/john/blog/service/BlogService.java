package john.blog.service;

import john.blog.domain.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {

    /** Save a new blog */
    void saveBlog(Blog blog);

    /** Find all the blogs */
    List<Blog> findAllBlogs();

    /** Find a blog through its ID */
    Blog findBlogById(Long id);

    /** Find all the blogs and sort the returned blogs by their created time */
    List<Blog> findAllBlogsOrderByCreatedTime();

    /** Find created time of all blogs */
    List<String> findBlogTimeLine();

    /** Find all blogs that contain the specified keyword */
    List<Blog> findBlogsByKeyword(String keyword);

    /** Find the latest three blogs */
    List<Blog> findIndexBlogs();
}
