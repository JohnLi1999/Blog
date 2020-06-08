package john.blog.service.impl;

import john.blog.domain.Message;
import john.blog.dto.PageBean;
import john.blog.repository.MessageRepository;
import john.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    @Override
    public PageBean getMessagePageBean(Integer currentPage) {
        // Create a PageBean object for messages
        Integer totalCount = messageRepository.findTotalMessagesCount();
        PageBean pageBean = new PageBean(currentPage, PageBean.DEFAULT_MESSAGE_PAGE_SIZE, totalCount);

        // Get the list of all messages in current page
        List<Message> messageList = messageRepository.findMessagePageList(pageBean.startPosition(), pageBean.getPageSize());
        pageBean.setList(messageList);

        return pageBean;
    }
}
