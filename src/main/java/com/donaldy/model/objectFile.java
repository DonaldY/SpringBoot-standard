package com.donaldy.model;

import java.util.Date;

public class objectFile {
    private Integer fileId;

    private Integer userId;

    private String clientId;

    private String fileName;

    private String path;

    private Boolean type;

    private Byte accessType;

    private Date createdAt;

    private Date updatedAt;

    public objectFile(Integer fileId, Integer userId, String clientId, String fileName, String path, Boolean type, Byte accessType, Date createdAt, Date updatedAt) {
        this.fileId = fileId;
        this.userId = userId;
        this.clientId = clientId;
        this.fileName = fileName;
        this.path = path;
        this.type = type;
        this.accessType = accessType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public objectFile() {
        super();
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Byte getAccessType() {
        return accessType;
    }

    public void setAccessType(Byte accessType) {
        this.accessType = accessType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}