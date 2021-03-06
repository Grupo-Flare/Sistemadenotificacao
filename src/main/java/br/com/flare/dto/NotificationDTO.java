package br.com.flare.dto;

import br.com.flare.model.Category;
import br.com.flare.model.Note;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NotificationDTO {

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String message;

    private String category;

    private String urlImage;

    private String date;
    private String time;

    public NotificationDTO(String title, String message) {
        this.title = title;
        this.message = message;

    }

    public Note toModel() {

        if (getDate().isBlank() || getDate().isEmpty()) {
            return new Note(getTitle(), getMessage(), getUrlImage(), new Category(getCategory().isBlank() ? "Geral" : getCategory()), LocalDateTime.now());
        } else {
            return new Note(getTitle(), getMessage(), getUrlImage(), new Category(getCategory().isBlank() ? "Geral" : getCategory()), getLocalDateTime());
        }

    }

    public LocalDateTime getLocalDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(getDate() + " " + getTime(), formatter);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
