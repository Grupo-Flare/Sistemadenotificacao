package br.com.flare.dto;

import br.com.flare.annotations.Unique;
import br.com.flare.model.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class UserDTO {

    @NotBlank
    private String name;

    @NotBlank @Email
    @Unique(domainClass = User.class, fieldName = "email", message = "Este email já esta sendo usado!")
    private String email;

    @NotBlank
    private String permissao;

    public UserDTO(String name, String email, String permissao) {
        this.name = name;
        this.email = email;
        this.permissao = permissao;
    }

    public String getPermissao() {
        return permissao;
    }

    public void setPermissao(String permissao) {
        this.permissao = permissao;
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
