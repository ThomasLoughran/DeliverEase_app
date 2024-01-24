package com.deliverease_group.webapp.dtos;

public class PasswordDTO {

    private Long id;

    private String newPassword;
    private String oldPassword;

    public PasswordDTO() {
    }

    public PasswordDTO(Long id, String newPassword, String oldPassword) {
        this.id = id;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
