package john.blog.controller;

import john.blog.domain.Blog;
import john.blog.domain.BlogComment;
import john.blog.domain.Mood;
import john.blog.domain.Topic;
import john.blog.dto.PageBean;
import john.blog.service.BlogCommentService;
import john.blog.service.BlogService;
import john.blog.service.MoodService;
import john.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private MoodService moodService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private BlogCommentService blogCommentService;

    @RequestMapping("/blogs")
    public ModelAndView findAllBlogs(Model model) {
        // Obtain all available Blogs and Topics
        List<Blog> blogList = blogService.findAllBlogs();
        List<Topic> topicList = topicService.findAllTopics();

        // Set the Blogs and Topics into the model
        model.addAttribute("blogList", blogList);
        model.addAttribute("topicList", topicList);

        return new ModelAndView("/blog", "blogModal", model);
    }

    /** Add a new Blog */
    @RequestMapping("/addBlog")
    public String addBlog() {
        return "add-blog.html";
    }

    @RequestMapping("/saveBlog")
    public String saveBlog(Blog blog, String moodId, String topicsStr) {
        // Set default created time
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createdTime = format.format(date);
        blog.setCreatedTime(createdTime);
        // Set default view number
        blog.setViewNumber(0L);
        // Set default comment number
        blog.setCommentNumber(0L);

        // Maintain association between Blog and Mood
        Mood mood = null;
        if (!moodId.isEmpty()) {
            mood = moodService.findMoodById(Long.valueOf(moodId));
        }
        blog.setMood(mood);

        /* Maintain association between Blog and Topic
           Analyze topics string - split by semicolon, every substring is a different topic */

        // Initialize the set that saves Topics
        HashSet<Topic> topics = new HashSet<>();

        // Split topicsStr by semicolon
        String[] topicsArr = topicsStr.split(";");

        // Traverse topics
        for (String t : topicsArr) {
            // Remove potential leading and trailing empty spaces
            String topicStr = t.trim();

            // Check if the topic exists
            Topic topic = topicService.findTopicByTopic(topicStr);

            // If the topic does not exist, we need to initialize the topic
            if (topic == null) {
                // Convert the Topic String to a Topic Object
                topic = new Topic(topicStr, new HashSet<>());

                // Add the new topic into "topic" table in the database
                topic = topicService.saveTopic(topic);
            }

            // Add the Topic Object to the Set of Topics in the Blog
            topics.add(topic);
        }

        // Maintain the association between Blog and Topic
        blog.setTopics(topics);

        // Save the new Blog into database
        blogService.saveBlog(blog);

        return "redirect:/blogs";
    }

    @RequestMapping("/previewBlog")
    public String previewBlog(Blog blog, String moodId, String topicsStr, ServletRequest request) {
        // Set default created time
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createdTime = format.format(date);
        blog.setCreatedTime(createdTime);

        // Set Mood through moodId - find the mood only if the user selected one
        Mood mood = null;
        if (!moodId.isEmpty()) {
            mood = moodService.findMoodById(Long.valueOf(moodId));
        }
        blog.setMood(mood);

        /* Set Topics */
        // Split topicsStr by semicolon
        String[] topicsArr = topicsStr.split(";");
        // Add topics into a list
        List<String> topicList = new LinkedList<>();
        Arrays.stream(topicsArr)
            .map(topic -> topic.trim())
            .forEach(topic -> topicList.add(topic));

        // Give all the data to  blog detail page
        request.setAttribute("blog", blog);
        request.setAttribute("mood", mood);
        request.setAttribute("topicList", topicList);

        return "preview-blog.html";
    }

    @RequestMapping("/blogDetail")
    public ModelAndView blogDetail(String id, Integer currentPage, Model model) {
        // Search the blog through its id
        Blog blog = blogService.findBlogById(Long.valueOf(id));
        // Obtain all comments under the blog (with pagination)
        PageBean blogCommentPageBean = blogCommentService.getBlogCommentPageBean(Long.valueOf(id), currentPage);

        // Set the blog into the model
        model.addAttribute("blog", blog);
        // Put all comments in the blog into the model
        model.addAttribute("blogCommentPageBean", blogCommentPageBean);

        return new ModelAndView("blog-detail.html", "blogModel", model);
    }


    /* TODO: update an existing Blog */
    /* TODO: delete an existing Blog */

}
