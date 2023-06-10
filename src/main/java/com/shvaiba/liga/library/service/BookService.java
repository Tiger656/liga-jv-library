package com.shvaiba.liga.library.service;

import com.shvaiba.liga.library.persitance.BookRepository;
import com.shvaiba.liga.library.persitance.BorrowInfoRepository;
import com.shvaiba.liga.library.persitance.ClientRepository;
import com.shvaiba.liga.library.persitance.entity.Book;
import com.shvaiba.liga.library.persitance.entity.BorrowInfo;
import com.shvaiba.liga.library.persitance.entity.Client;
import com.shvaiba.liga.library.rest.dto.BookDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    private final BorrowInfoRepository borrowInfoRepository;

    @Autowired
    public BookService(BookRepository bookRepository, ClientRepository clientRepository, BorrowInfoRepository borrowInfoRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
        this.borrowInfoRepository = borrowInfoRepository;
    }

    public Book addBook(BookDto bookDto) {
        return bookRepository.save(new Book(bookDto.getId(), bookDto.getName(), null));
    }

    public void deleteBook(Long bookId) {
         bookRepository.deleteById(bookId);
    }

    public Book getBook(Long bookId) {
        return bookRepository.getReferenceById(bookId);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book updateBook(BookDto bookDto, Long bookId) { // TO DO: Check how save works
        Book book = bookRepository.getReferenceById(bookId);
        book.setName(bookDto.getName());
        return bookRepository.save(book);
    }

    @Transactional
    public Book borrowBook(Long bookId, Long clientId) {
        Book book = bookRepository.getReferenceById(bookId);
        Client client = clientRepository.getReferenceById(clientId);
        BorrowInfo borrowInfo = new BorrowInfo(null, client, book, false);
        borrowInfoRepository.save(borrowInfo);
        book.setIsBorrowed(true);
        bookRepository.save(book);
        return bookRepository.save(book);
    }

    @Transactional
    public Book returnBook(Long bookId, Long clientId) {
        BorrowInfo borrowInfo = borrowInfoRepository.findByBookIdAndClientIdAndIsReturned(bookId, clientId, true);
        borrowInfo.setIsReturned(true);
        borrowInfoRepository.save(borrowInfo);
        Book book = bookRepository.getReferenceById(bookId);
        book.setIsBorrowed(false);
        bookRepository.save(book);
        return bookRepository.save(book);
    }

}
