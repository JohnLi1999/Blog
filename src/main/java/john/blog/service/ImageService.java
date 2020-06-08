package john.blog.service;

import john.blog.domain.Image;
import org.springframework.stereotype.Service;

@Service
public interface ImageService {

    /** Save a new Image */
    void saveImage(Image image);

}
