package br.com.flare.model;

import java.util.Map;

public class Note {

    private String title;
    private String message;
    private String imageUrl;

    public Note(String title, String message, String imageUrl) {
        this.title = title;
        this.message = message;
        this.imageUrl = imageUrl;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
