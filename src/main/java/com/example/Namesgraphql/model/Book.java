package com.example.Namesgraphql.model;

public class Book {
    private Integer id;
    private String title;
    private Integer pages;

    public Book(Integer id, String title, Integer pages) {
        this.id = id;
        this.title = title;
        this.pages = pages;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}


