package com.bootcampProject.BootcampProject.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@ConfigurationProperties(prefix = "file")
@Entity
@Table(name = "Image")
@Component
public class ImageTable extends BaseDomain {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Users userId;
    private String fileName;
    private String uploadDir;

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}
