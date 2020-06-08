package john.blog.controller;

import john.blog.domain.Album;
import john.blog.domain.AlbumComment;
import john.blog.domain.Visitor;
import john.blog.service.AlbumCommentService;
import john.blog.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AlbumCommentController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private HttpSession session;

    @Autowired
    private AlbumCommentService albumCommentService;

    @RequestMapping("/saveAlbumComment")
    public String saveAlbumComment(AlbumComment albumComment, String albumId) {
        // Need to set created time, album, and visitor manually

        // Set created time
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createdTime = format.format(date);
        albumComment.setCreatedTime(createdTime);

        // Set album
        Album album = albumService.findAlbumById(Long.valueOf(albumId));
        albumComment.setAlbum(album);

        // Set visitor
        Object visitor = session.getAttribute("visitor");

        if (visitor != null) {
            albumComment.setVisitor((Visitor) visitor);
        }

        albumCommentService.saveAlbumComment(albumComment);

        return "redirect:/albumDetail?id=" + albumId;
    }
}
