package org.yasinkanli.librarymanagement.dto;

import jakarta.validation.constraints.NotBlank;

public class MemberRequestDto {

    @NotBlank(message = "Member name is required")
    private String name;

    @NotBlank(message = "Card number is required")
    private String cardNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}