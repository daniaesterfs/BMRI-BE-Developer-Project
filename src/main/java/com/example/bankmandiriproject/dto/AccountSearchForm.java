package com.example.bankmandiriproject.dto;

public class AccountSearchForm {

    private final String searchNumber;
    private final String searchName;
    private final String searchType;

    public AccountSearchForm(String searchNumber, String searchName, String searchType) {
        this.searchNumber = searchNumber;
        this.searchName = searchName;
        this.searchType = searchType;
    }

    public String getSearchNumber() {
        return searchNumber;
    }

    public String getSearchName() {
        return searchName;
    }

    public String getSearchType() {
        return searchType;
    }
}
