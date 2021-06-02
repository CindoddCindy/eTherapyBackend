package com.etherapy.etherapyproject.music.response;

public class MusicResponse {
    String fileName;
    String fileType;
    String fileUri;

    public MusicResponse(String fileName, String fileType, String fileUri) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileUri = fileUri;
    }

    public String getFileUri() {
        return fileUri;
    }

    public void setFileUri(String fileUri) {
        this.fileUri = fileUri;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
