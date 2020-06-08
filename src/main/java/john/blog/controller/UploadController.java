package john.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
public class UploadController {

    @RequestMapping("/uploadImage")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        // Get file name
        String fileName = file.getOriginalFilename();

        try {
            // Obtain upload path
            String path = ResourceUtils.getURL("classpath:").getPath() + "../resources/static/upload/";
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

        return "redirect:/index";
    }
}
