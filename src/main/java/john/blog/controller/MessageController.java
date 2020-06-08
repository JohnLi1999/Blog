package john.blog.controller;

import john.blog.domain.Message;
import john.blog.domain.Visitor;
import john.blog.dto.PageBean;
import john.blog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;


    @RequestMapping("/messages")
    public ModelAndView findAllMessages(Integer currentPage, Model model) {
        PageBean messagePageBean = messageService.getMessagePageBean(currentPage);
        model.addAttribute("messagePageBean", messagePageBean);

        return new ModelAndView("guest-book.html", "messageModel", model);
    }

    @RequestMapping("/saveMessage")
    public String saveMessage(Message message, HttpSession session) {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createdTime = format.format(date);
        message.setCreatedTime(createdTime);

        Visitor visitor = (Visitor) session.getAttribute("visitor");
        if (visitor != null) {
            message.setVisitor(visitor);
            messageService.saveMessage(message);
        }

        return "redirect:/messages";
    }
}
