package com.deliverease_group.webapp.dtos;

public class MessageResponseDTO {

    String response;

    public MessageResponseDTO() {
    }

    public MessageResponseDTO(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
