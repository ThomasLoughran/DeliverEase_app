package com.deliverease_group.webapp.dtos;

public class LoginDTO {

    private Long id;
    private String password;

    public LoginDTO(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public LoginDTO() {
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
