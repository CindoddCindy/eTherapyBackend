package com.etherapy.etherapyproject.music.service;

import com.etherapy.etherapyproject.music.constans.FileErrors;
import com.etherapy.etherapyproject.music.exception.FileNotFoundException;
import com.etherapy.etherapyproject.music.exception.FileSaveException;
import com.etherapy.etherapyproject.music.model.Music;
import com.etherapy.etherapyproject.music.repository.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MusicService {

    @Autowired
    MusicRepository musicRepository;

    public Music saveFile(MultipartFile file) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if (filename.contains("...")) {
                throw new FileSaveException(FileErrors.INVALID_FILE + filename);
            }

            Music model = new Music(filename, file.getContentType(), file.getBytes());
            return musicRepository.save(model);

        } catch (Exception e) {

            throw new FileSaveException(FileErrors.FILE_NOT_STORED, e);
        }

    }

    public Music getFile(String fileId) {

        return musicRepository.findById(fileId).orElseThrow(() -> new FileNotFoundException(FileErrors.FILE_NOT_FOUND + fileId));
    }

    public List<Music> getListOfFiles(){

        return musicRepository.findAll();
    }
}
