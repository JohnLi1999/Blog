package john.blog.controller;

import john.blog.domain.Mood;
import john.blog.domain.User;
import john.blog.service.MoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MoodController {

    @Autowired
    private MoodService moodService;

    /** Find all the moods */
    @RequestMapping("/moods")
    public ModelAndView findAllMoods(Model model) {
        // Search for all Moods
        List<Mood> moodList = moodService.findAllMoods();

        // Put all Moods into the model
        model.addAttribute("moodList", moodList);
        return new ModelAndView("mood.html", "moodModel", model);
    }

    /** Select all available Moods */
    @RequestMapping("/selectMoods")
    public ModelAndView selectMoods(Model model) {
        // Search for all Moods
        List<Mood> moodList = moodService.findAllMoods();

        // Put all Moods into the model
        model.addAttribute("moodList", moodList);
        return new ModelAndView("select-mood.html", "moodModel", model);
    }

    /** Save a new Mood */
    @RequestMapping("/saveMood")
    public String saveMood(Mood mood, @RequestParam("file") MultipartFile file, ServletRequest request) {
        // Encapsulate "createdTime" for Mood Object
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createdTime = format.format(date);
        mood.setCreatedTime(createdTime);

        // Get the uploaded im  age
        mood.setImage(file.getOriginalFilename());

        // Encapsulate User (Maintain many-to-one association)
        User owner = (User) request.getAttribute("owner");
        mood.setUser(owner);

        // Save the new Mood
        moodService.saveMood(mood);

        return "redirect:/moods";
    }

    /** Delete a Mood */
    @RequestMapping("/deleteMood")
    public String deleteMood(String deletedId) {
        moodService.deleteMoodById(Long.valueOf(deletedId));
        return "redirect:/moods";
    }
}
