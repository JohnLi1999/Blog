package john.blog.controller;

import john.blog.domain.Blog;
import john.blog.domain.BlogComment;
import john.blog.domain.Visitor;
import john.blog.service.BlogCommentService;
import john.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class BlogCommentController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private HttpSession session;

    @Autowired
    private BlogCommentService blogCommentService;

    @RequestMapping("/saveBlogComment")
    public String saveBlogComment(BlogComment blogComment, String blogId) {
        // Need to set created time, blog, and visitor manually

        // Set created time
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createdTime = format.format(date);
        blogComment.setCreatedTime(createdTime);

        // Set blog
        Blog blog = blogService.findBlogById(Long.valueOf(blogId));
        blogComment.setBlog(blog);

        // Set visitor
        Object visitor = session.getAttribute("visitor");
        if (visitor != null) {
            blogComment.setVisitor((Visitor) visitor);
        }

        // Increase comment number
        blog.setCommentNumber(blog.getCommentNumber() + 1);

        blogCommentService.saveBlogComment(blogComment);

        return "redirect:/blogDetail?id=" + blogId;
    }
}
