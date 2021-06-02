package com.etherapy.etherapyproject.music.controller;

import com.etherapy.etherapyproject.music.model.Music;
import com.etherapy.etherapyproject.music.response.MusicResponse;
import com.etherapy.etherapyproject.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Music")
public class MusicController {
    @Autowired
    MusicService musicService;

    @PostMapping("/Upload")
    public MusicResponse uploadFile(@RequestParam("file") MultipartFile file) {

        Music model =musicService.saveFile(file);
        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").
                path(model.getFileId()).toUriString();
        return new MusicResponse(model.getFileName(), model.getFileType(), fileUri);
    }

    @PostMapping("/UploadMultipleFiles")
    public List<MusicResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files).
                stream().
                map(file -> uploadFile(file)).
                collect(Collectors.toList());
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        Music model = musicService.getFile(fileName);
        return ResponseEntity.ok().
                header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + model.getFileName() + "\"").
                body(new ByteArrayResource(model.getFileData()));


    }


    @GetMapping("/Allfiles")
    public  List<Music>  getListFiles(Model model) {
        List<Music> fileDetails = musicService.getListOfFiles();

        return fileDetails;
    }
}
