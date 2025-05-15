package org.yasinkanli.librarymanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorResponseDto {

    private Long id;
    private String name;
    private Set<BookResponseDto> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookResponseDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookResponseDto> books) {
        this.books = books;
    }
}
