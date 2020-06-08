package john.blog.service.impl;

import john.blog.domain.Album;
import john.blog.repository.AlbumRepository;
import john.blog.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void saveAlbum(Album album) {
        albumRepository.save(album);
    }

    @Override
    public List<Album> findAllAlbums() {
        return (List<Album>) albumRepository.findAll();
    }

    @Override
    public Album findAlbumById(Long id) {
        Optional<Album> album = albumRepository.findById(id);
        return album.get();
    }

    @Override
    public List<Album> findAllAlbumsOrderByCreatedTime() {
        return albumRepository.findAllAlbumsOrderByCreatedTime();
    }

    @Override
    public List<String> findAlbumTimeLine() {
        return albumRepository.findAlbumTimeLine();
    }

    @Override
    public List<Album> findAlbumsByKeyword(String keyword) {
        return albumRepository.findAlbumsByKeyword(keyword);
    }

    @Override
    public List<Album> findIndexAlbums() {
        return albumRepository.findIndexAlbums();
    }
}
