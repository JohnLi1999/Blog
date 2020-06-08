package john.blog.controller;

import john.blog.domain.Album;
import john.blog.domain.Blog;
import john.blog.domain.Mood;
import john.blog.domain.Topic;
import john.blog.service.AlbumService;
import john.blog.service.BlogService;
import john.blog.service.MoodService;
import john.blog.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private MoodService moodService;

    @Autowired
    private TopicService topicService;

    @RequestMapping("/search")
    public ModelAndView search(String keyword, Model model) {
        // Obtain blog list
        List<Blog> blogList = blogService.findBlogsByKeyword(keyword);

        // Obtain album list
        List<Album> albumList = albumService.findAlbumsByKeyword(keyword);

        // Obtain mood list
        List<Mood> moodList = moodService.findMoodsByKeyword(keyword);

        // Put all lists into the model
        model.addAttribute("blogList", blogList);
        model.addAttribute("albumList", albumList);
        model.addAttribute("moodList", moodList);

        // Put the keyword itself into the model
        model.addAttribute("keyword", keyword);

        return new ModelAndView("search.html", "searchModel", model);
    }

    @RequestMapping("/searchTopic")
    public ModelAndView searchTopic(String topicId, Model model) {
        // Obtain a list of blog that under the specified topic
        Topic topic = topicService.findTopicById(Long.valueOf(topicId));

        // Put the blog list into the model
        model.addAttribute("blogList", topic.getBlogs());
        // Put searched topic into the model
        model.addAttribute("topic", topic.getTopic());

        return new ModelAndView("search-topic.html", "searchTopicModel", model);
    }
}
