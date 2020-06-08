package john.blog.controller;

import john.blog.domain.Album;
import john.blog.dto.PageBean;
import john.blog.service.AlbumCommentService;
import john.blog.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumCommentService albumCommentService;

    @RequestMapping("/albums")
    public ModelAndView findAllAlbums(Model modal) {
        List<Album> albumList = albumService.findAllAlbums();
        modal.addAttribute("albumList", albumList);
        return new ModelAndView("/album", "albumModal", modal);
    }

    @RequestMapping("/addAlbum")
    public String addImage() {
        return "add-album.html";
    }

    @RequestMapping("/saveAlbum")
    public String saveAlbum(Album album) {
        // Set default created time
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createdTime = format.format(date);
        album.setCreatedTime(createdTime);

        // Set default image number
        album.setImageNumber(0L);

        // Save the new Album into database
        albumService.saveAlbum(album);

        return "redirect:/albums";
    }

    @RequestMapping("/previewAlbum")
    public String previewAlbum(Album album, ServletRequest request) {
        // Set default created time
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String createdTime = format.format(date);
        album.setCreatedTime(createdTime);

        request.setAttribute("album", album);

        return "preview-album.html";
    }

    /** Select all available Albums */
    @RequestMapping("/selectAlbums")
    public ModelAndView selectAlbums(Model model) {

        // Obtain all available albums
        List<Album> albumList = albumService.findAllAlbums();

        // Add all albums to the front end pages
        model.addAttribute("albumList", albumList);

        return new ModelAndView("select-album.html", "albumModal", model);
    }

    @RequestMapping("/albumDetail")
    public ModelAndView albumDetail(String id, Integer currentPage, Model model) {
        // Search the album through its id
        Album album = albumService.findAlbumById(Long.valueOf(id));
        // Obtain all comments under the blog (with pagination)
        PageBean albumCommentPageBean = albumCommentService.getAlbumCommentPageBean(Long.valueOf(id), currentPage);

        // Set the album into the model
        model.addAttribute("album", album);
        // Put all comments in the album into the model
        model.addAttribute("albumCommentPageBean", albumCommentPageBean);

        return new ModelAndView("album-detail.html", "albumModel", model);
    }

}
