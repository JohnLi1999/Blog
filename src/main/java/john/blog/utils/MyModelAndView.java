package john.blog.utils;

import org.springframework.web.servlet.ModelAndView;

public class MyModelAndView extends ModelAndView implements Comparable<MyModelAndView> {

    private int priority;

    public MyModelAndView(String viewName, String modelName, Object modelObject, int priority) {
        super(viewName, modelName, modelObject);
        this.priority = priority;
    }

    @Override
    public int compareTo(MyModelAndView o) {
        return this.priority - o.getPriority();
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
