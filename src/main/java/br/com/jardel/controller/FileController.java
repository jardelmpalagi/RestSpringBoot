package br.com.jardel.controller;

import br.com.jardel.data.vo.UploadFileResponseVO;
import br.com.jardel.service.FileStorageService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date: 6/7/21
 * Author: jardel
 */

@Api(value = "Api File", tags = "Api File")
@RestController
@RequestMapping("v1/file")
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    private final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping
    public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storageFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/v1/file/").path(fileName).toUriString();

        return new UploadFileResponseVO(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/multiple")
    public List<UploadFileResponseVO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {

        return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.getFileAsResource(fileName);
        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

        } catch (Exception e) {
            LOG.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachement; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
