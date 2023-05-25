package com.shvaiba.liga.library.service;

import com.shvaiba.liga.library.persitance.entity.Book;
import com.shvaiba.liga.library.persitance.entity.Books;
import com.shvaiba.liga.library.persitance.entity.Clients;
import com.shvaiba.liga.library.persitance.entity.Library;
import com.shvaiba.liga.library.rest.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private Library library;
    private Books books;
    private Clients clients;

    @Autowired
    public BookService(Library library, Books books, Clients clients) {
        this.library = library;
        this.books = books;
        this.clients = clients;
    }

    public Book addBook(BookDto bookDto) {
        return books.addBook(new Book(bookDto.getId(), bookDto.getName(), null));
    }

    public void deleteBook(Long bookId) {
        books.getBooks().remove(bookId.intValue());
    }

    public Book getBook(Long bookId) {
        return books.getBooks().get(bookId.intValue());
    }

    public List<Book> getBooks() {
        return books.getBooks();
    }

    public Book updateBook(BookDto bookDto) {
        Book book = books.getBooks().get(bookDto.getId().intValue());
        book.setName(bookDto.getName());
        return book;
    }

    public Book borrowBook(Long bookId, Long clientId) {
        Book book = getBook(bookId);
        book.setClientId(clientId);
        return book;
    }

    public List<Book> borrowedBook() {
        return books.getBooks().stream().filter(book -> book.getClientId() != null).toList();
    }

}
