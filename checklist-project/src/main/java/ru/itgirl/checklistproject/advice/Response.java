package ru.itgirl.checklistproject.advice;

import lombok.Getter;

@Getter
public class Response {

    private String message;

    public Response() {
    }

    public Response(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
