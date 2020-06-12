package john.blog.controller.async;

import john.blog.utils.MyModelAndView;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

@Component
public class DeferredResultHolder {

    /* Integer - priority
     * DeferredResult - returned result  */
    private Map<Integer, DeferredResult<MyModelAndView>> map = new HashMap<>();

    public Map<Integer, DeferredResult<MyModelAndView>> getMap() {
        return map;
    }

    public void setMap(Map<Integer, DeferredResult<MyModelAndView>> map) {
        this.map = map;
    }
}
