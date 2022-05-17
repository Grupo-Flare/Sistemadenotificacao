package br.com.flare.dto;

import br.com.flare.annotations.Unique;
import br.com.flare.model.Category;
import br.com.flare.model.User;

import java.util.List;

public class UserDTO {
    private final String name;

    @Unique(domainClass = User.class, fieldName = "email", message = "Este email já está sendo usado.")
    private final String email;

    private final List<String> categories;

    public UserDTO(String name, String email, List<String> categories) {
        this.name = name;
        this.email = email;
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getCategories() {
        return categories;
    }

    public User toModel(List<Category> categories) {
        User user = new User(getName(), getEmail());
        user.setCategory(categories);
        return user;
    }
}
