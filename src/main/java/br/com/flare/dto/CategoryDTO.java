package br.com.flare.dto;

import br.com.flare.model.Category;

public class CategoryDTO {

    private String name;

    private boolean registred;

    @Deprecated
    public CategoryDTO() {
    }

    public CategoryDTO(String name) {
        this.name = name;
    }

    public CategoryDTO(String name, boolean registred) {
        this.name = name;
        this.registred = registred;
    }

    public CategoryDTO(Category category) {
        this.name = category.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsRegistred(boolean isRegistred) {
        this.registred = isRegistred;
    }

    public boolean isRegistred() {
        return this.registred;
    }

    @Override
    public String toString() {
        return "CategoryDTO { Name: " + getName() + ", Registred: " + isRegistred()  +  " }";
    }
}
