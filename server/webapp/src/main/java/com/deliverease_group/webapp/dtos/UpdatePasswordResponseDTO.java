package com.deliverease_group.webapp.dtos;

public class UpdatePasswordResponseDTO {

    String response;

    public UpdatePasswordResponseDTO() {
    }

    public UpdatePasswordResponseDTO(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
