package com.shvaiba.liga.library.rest;

import com.shvaiba.liga.library.persitance.entity.Book;
import com.shvaiba.liga.library.rest.dto.BookDto;
import com.shvaiba.liga.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(path = "/")
    public Book addBook(@RequestBody BookDto bookDto) {
        return bookService.addBook(bookDto);
    }

    @PutMapping(path = "/{id}")
    public Book updateBook(@PathVariable("id") Integer bookId, BookDto bookDto) {
        return bookService.updateBook(bookDto);

    }

    @GetMapping(path = "/{id}")
    public Book getBook(@PathVariable("id") Long bookId) {
        return bookService.getBook(bookId);
    }

    @GetMapping("/")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) {
        bookService.deleteBook(bookId);
    }

    @PostMapping(path = "/{bookId}/borrow") //TO DO: Should be
    public Book borrowBook(@PathVariable("bookId") Long bookId,
                           @RequestParam(name = "clientId", required = true) Long clientId) {
        return bookService.borrowBook(bookId, clientId);
    }






}
