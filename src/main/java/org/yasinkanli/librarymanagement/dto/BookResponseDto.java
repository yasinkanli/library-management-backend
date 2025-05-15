package org.yasinkanli.librarymanagement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponseDto {
    private Long id;
    private String title;
    private Set<AuthorResponseDto> authors;
    private Set<ReviewResponseDto> reviews;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<AuthorResponseDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorResponseDto> authors) {
        this.authors = authors;
    }

    public Set<ReviewResponseDto> getReviews() {
        return reviews;
    }

    public void setReviews(Set<ReviewResponseDto> reviews) {
        this.reviews = reviews;
    }
}
