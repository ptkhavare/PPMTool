package com.pranavkhavare.ppmtool.exceptions.excpetionResponses;

public class SingleStringExceptionResponse {
    private String projectIdentifier;

    public SingleStringExceptionResponse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
