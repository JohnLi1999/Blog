package john.blog.controller;

import john.blog.domain.Album;
import john.blog.domain.Blog;
import john.blog.domain.Mood;
import john.blog.service.AlbumService;
import john.blog.service.BlogService;
import john.blog.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private MoodService moodService;

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public ModelAndView index(Model model) {
        // Obtain the latest three Blogs, Alums, and Moods
        List<Blog> blogList = blogService.findIndexBlogs();
        List<Album> albumList = albumService.findIndexAlbums();
        List<Mood> moodList = moodService.findIndexMoods();

        // Set all three lists into the model
        model.addAttribute("blogList", blogList);
        model.addAttribute("albumList", albumList);
        model.addAttribute("moodList", moodList);

        return new ModelAndView("index.html", "indexModel", model);
    }

    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup.html";
    }

}
