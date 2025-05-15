package org.yasinkanli.librarymanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthorRequestDto {

    @NotBlank(message = "Author name cannot be blank")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}