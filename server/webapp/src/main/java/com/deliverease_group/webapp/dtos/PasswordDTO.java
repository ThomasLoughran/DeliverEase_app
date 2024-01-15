package com.deliverease_group.webapp.dtos;

public class PasswordDTO {

    private Long id;

    private String password;

    public PasswordDTO() {
    }

    public PasswordDTO(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
