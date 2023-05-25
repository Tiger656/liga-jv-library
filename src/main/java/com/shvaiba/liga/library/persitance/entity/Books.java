package com.shvaiba.liga.library.persitance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Books {
    private List<Book> books;
    private Long availableId;

    public Books(List<Book> books, Long availableId) {
        this.books = books;
        this.availableId = availableId;
    }

    public Book addBook(Book book) {
        List<Book> booksList = getBooks();
        Long availableId = getAvailableId();
        booksList.add(new Book(availableId, book.getName(), book.getClientId()));

        return booksList.get(availableId.intValue());
    }

    private Long getAvailableId() {
        return availableId++;
    }
}
