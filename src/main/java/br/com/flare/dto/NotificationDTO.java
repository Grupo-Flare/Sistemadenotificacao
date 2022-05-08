package br.com.flare.dto;

import br.com.flare.model.Category;
import br.com.flare.model.Note;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NotificationDTO {

    @NotNull
    @NotBlank
    private java.lang.String title;

    @NotNull
    @NotBlank
    private java.lang.String message;

    private String category;

    private String urlImage;

    public NotificationDTO(String title, String message) {
        this.title = title;
        this.message = message;

    }

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public java.lang.String getMessage() {
        return message;
    }

    public void setMessage(java.lang.String message) {
        this.message = message;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Note toModel() {

        return new Note(getTitle(), getMessage(), "", new Category(getCategory()));

    }

}
