package john.blog.controller;

import john.blog.domain.Album;
import john.blog.domain.Image;
import john.blog.service.AlbumService;
import john.blog.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class ImageController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ImageService imageService;

    @RequestMapping("/uploadMultipleImages")
    public String uploadMultipleImages(@RequestParam(value = "files") MultipartFile[] files, String albumId) {

        // Obtain the album to which the images will be uploaded
        Album album = albumService.findAlbumById(Long.valueOf(albumId));

        // Upload images one by one
        for (MultipartFile file : files) {
            // Get file name
            String fileName = file.getOriginalFilename();

            try {
                // Obtain upload path
                String path = ResourceUtils.getURL("classpath:").getPath() + "../resources/static/upload/album-" + album.getId() + "/";
                File destPath = new File(path + fileName);

                // If the path does not exist, we need to create one
                if (!destPath.getParentFile().exists()) {
                    destPath.getParentFile().mkdir();
                }

                // Save file
                file.transferTo(destPath);

            } catch (IOException e) {
                e.printStackTrace();
                return "error happened when saving the image";
            }

            // Save the image and Maintain the association between Album and Images
            Image image = new Image(fileName, album);
            imageService.saveImage(image);
        }

        return "redirect:/album";
    }

}
