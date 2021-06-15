package br.com.jardel.service;

import br.com.jardel.config.FileStorageConfig;
import br.com.jardel.exception.FileStorageException;
import br.com.jardel.exception.MyFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Date: 6/6/21
 * Author: jardel
 */

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the upload file will be storage", e);
        }
    }

    public String storageFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path target = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e) {
            throw new FileStorageException("Could not storage file " + fileName + ". Please try again.", e);
        }
    }

    public Resource getFileAsResource(String fileName) {
        Path filePath = this.fileStorageLocation.resolve(fileName);

        try {
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new MyFileNotFoundException("File not found " + fileName);
            }

            return resource;

        } catch (Exception e) {
            throw new MyFileNotFoundException("File not found " + fileName, e);
        }
    }
}
