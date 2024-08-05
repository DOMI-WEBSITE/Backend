package com.c1848tjavaangular.domi.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFileService {

    public String saveFoto(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte [] bytes=file.getBytes();
            String url = "src/main/resources/static/IMG/fotos/";
            Path path = Paths.get(url +file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }

    public String savePortada(MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            byte [] bytes=file.getBytes();
            String url = "src/main/resources/static/IMG/portada/";
            Path path = Paths.get(url +file.getOriginalFilename());
            Files.write(path, bytes);
            return file.getOriginalFilename();
        }
        return "default.jpg";
    }

}
