package br.com.flare.dto;

import javax.validation.constraints.NotBlank;

public class ChannelDTO {

    @NotBlank
    private String name;

    public ChannelDTO() {
    }

    public ChannelDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
