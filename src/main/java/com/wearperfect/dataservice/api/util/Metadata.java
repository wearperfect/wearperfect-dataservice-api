package com.wearperfect.dataservice.api.util;

import java.util.List;

public class Metadata {
    private String name;
    private String fileName;
    private List<String> supportedTargetFileTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<String> getSupportedTargetFileTypes() {
        return supportedTargetFileTypes;
    }

    public void setSupportedTargetFileTypes(List<String> supportedTargetFileTypes) {
        this.supportedTargetFileTypes = supportedTargetFileTypes;
    }
}
