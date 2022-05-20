package br.com.flare.dto;

import br.com.flare.annotations.Unique;
import br.com.flare.model.Category;
import br.com.flare.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotEmpty
    private List<String> permission;

    public UserDTO(String name, String email, List<String> permission) {
        this.name = name;
        this.email = email;
        this.permission = permission;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    public User toModel() {
        return new User(getName(), getEmail());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
