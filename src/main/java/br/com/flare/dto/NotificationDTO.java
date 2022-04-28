package br.com.flare.dto;

import br.com.flare.model.Note;

public class NotificationDTO {

    private String title;
    private String message;

    public NotificationDTO(String title, String message) {
        this.title = title;
        this.message = message;

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
    
    public Note toModel() {
       
        return new Note(getTitle(), getMessage(), "");

    }

}
