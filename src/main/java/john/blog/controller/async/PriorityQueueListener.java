package john.blog.controller.async;

import john.blog.domain.Album;
import john.blog.domain.Blog;
import john.blog.domain.Mood;
import john.blog.service.AlbumService;
import john.blog.service.BlogService;
import john.blog.service.MoodService;
import john.blog.utils.MyModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/* When the priority holds at least one element, the listener will returned
   its elements through the map in DeferredResultHolder to the front end    */
@Component
public class PriorityQueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private PriorityQueue<MyModelAndView> priorityQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @Autowired
    private BlogService blogService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private MoodService moodService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // Open a new thread to listen the priority queue
        new Thread(() -> {
            while (true) {
                if (!priorityQueue.isEmpty()) {
                    // Receive the ModelAndView with highest priority
                    MyModelAndView myModelAndView = priorityQueue.dequeue();

                    // Obtain the latest three Blogs, Alums, and Moods
                    List<Blog> blogList = blogService.findIndexBlogs();
                    List<Album> albumList = albumService.findIndexAlbums();
                    List<Mood> moodList = moodService.findIndexMoods();

                    // Set all three lists into the model
                    myModelAndView.getModel().put("blogList", blogList);
                    myModelAndView.getModel().put("albumList", albumList);
                    myModelAndView.getModel().put("moodList", moodList);

                /* Obtain the expected result through the given priority, and
                   set the ModelAndView into the result.
                   So the ModelAndView can be received by the front end       */
                    deferredResultHolder.getMap().get(myModelAndView.getPriority()).setResult(myModelAndView);

                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
