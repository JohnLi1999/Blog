package john.blog.service;

import john.blog.domain.Message;
import john.blog.dto.PageBean;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {

    /** Save a new Message */
    void saveMessage(Message message);

    /** Obtain all messages (with Pagination) */
    PageBean getMessagePageBean(Integer currentPage);

}
