package br.com.jardel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Date: 6/6/21
 * Author: jardel
 */

@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
