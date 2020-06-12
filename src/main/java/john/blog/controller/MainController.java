package john.blog.controller;

import john.blog.controller.async.DeferredResultHolder;
import john.blog.controller.async.PriorityQueue;
import john.blog.utils.MyModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Random;

@Controller
public class MainController {

    @Autowired
    private PriorityQueue<MyModelAndView> priorityQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public DeferredResult<MyModelAndView> index(Model model) {
        // Give the ModelAndView a random priority between 0 and 999
        Random r = new Random();
        int priority = r.nextInt(1000);
        MyModelAndView myModelAndView = new MyModelAndView("index.html", "indexModel", model, priority);

        // Put the new ModelAndView into the priority queue
        priorityQueue.enqueue(myModelAndView);

        // Set up a DeferredResult and put the result into a DeferredResultHolder
        DeferredResult<MyModelAndView> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(priority, result);

        return result;
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
