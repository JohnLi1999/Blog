package john.blog.service.impl;

import john.blog.domain.Image;
import john.blog.repository.ImageRepository;
import john.blog.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    @Transactional
    public void saveImage(Image image) {
        imageRepository.save(image);
        image.getAlbum().setImageNumber(image.getAlbum().getImageNumber() + 1);
    }
}
