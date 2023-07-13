package com.shvaiba.liga.library.service;

import com.shvaiba.liga.library.persitance.BookRepository;
import com.shvaiba.liga.library.persitance.BorrowInfoRepository;
import com.shvaiba.liga.library.persitance.ClientRepository;
import com.shvaiba.liga.library.persitance.entity.Book;
import com.shvaiba.liga.library.persitance.entity.BorrowInfo;
import com.shvaiba.liga.library.persitance.entity.Client;
import com.shvaiba.liga.library.rest.dto.BookDto;
import com.shvaiba.liga.library.rest.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    BorrowInfoRepository borrowInfoRepository;

    @Test
    void addBook_whenValidBookDto_thenBookShouldBeSaved() {
        //given
        BookService bookService = new BookService(bookRepository, clientRepository, borrowInfoRepository);
        BookDto bookDto = new BookDto(1L, "Alex");
        Book book = new Book(null, bookDto.getName(), false);

        //when
        when(bookRepository.save(book)).thenReturn(book);
        Book actual = bookService.addBook(bookDto);

        //then
        //verify(bookRepository).save(book); verify can be removed due to when() existence
        assertEquals(book, actual);
    }

    @Test
    void deleteBook_whenDataCorrect_thenDeleteBook() {
        BookService bookService = new BookService(bookRepository, clientRepository, borrowInfoRepository);
        Long bookId = 34L;

        bookService.deleteBook(bookId);

        verify(bookRepository).deleteById(bookId);
    }

    @Test
    void getBook_whenIdCorrectAndExist_thenReturnBook() {
        BookService bookService = new BookService(bookRepository, clientRepository, borrowInfoRepository);
        Long bookId = 34L;
        String name = "BookName";
        Book book = new Book(bookId, name, false);
        //ClientDto expected = new ClientDto(clientId, name);

        //when
        when(bookRepository.getReferenceById(bookId)).thenReturn(book);
        Book actual = bookService.getBook(bookId);

        //then
        assertEquals(book, actual);
    }

    @Test
    void getBooks_whenBooksExist_ThenReturnAllBooks() {
        BookService bookService = new BookService(bookRepository, clientRepository, borrowInfoRepository);
        Long bookOneId = 34L;
        Long bookTwoId = 35L;
        String nameOne = "Alex";
        String nameTwo = "Gena";

        Book bookOne = new Book(bookOneId, nameOne, false);
        Book bookTwo = new Book(bookTwoId, nameTwo, false);
        List<Book> expected = Arrays.asList(bookOne, bookTwo);

        //when
        when(bookRepository.findAll()).thenReturn(expected);
        List<Book> actual = bookService.getBooks();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void updateBook_whenDataValid_thenUpdateBook() {
        BookService bookService = new BookService(bookRepository, clientRepository, borrowInfoRepository);
        Long bookId = 34L;
        String newBookName = "newBookName";
        String oldBookName = "oldBookName";
        BookDto bookDto = new BookDto(null, newBookName);
        Book oldBook = new Book(bookId, oldBookName, false);
        Book newBook = new Book(bookId, newBookName, false);

        //when
        when(bookRepository.getReferenceById(bookId)).thenReturn(oldBook);
        when(bookRepository.save(newBook)).thenReturn(newBook);
        Book actual = bookService.updateBook(bookDto, bookId);
        //then
        assertEquals(newBook, actual);
    }

    @Test
    void borrowBook_whenBookAndClientExists_thenBorrowBook() {
        //given
        BookService bookService = new BookService(bookRepository, clientRepository, borrowInfoRepository);
        Long bookId = 34L;
        Long clientId = 256L;
        Book book = new Book(bookId, "BookName", false);
        Book borrowedBook = new Book(bookId, "BookName", true);
        Client client = new Client(clientId, "ClientName");
        BorrowInfo borrowInfo = new BorrowInfo(null, client, book, false);
        Book expected = new Book(bookId, "BookName", true);

        //when
        when(bookRepository.getReferenceById(bookId)).thenReturn(book);
        when(clientRepository.getReferenceById(clientId)).thenReturn(client);
        when(bookRepository.save(borrowedBook)).thenReturn(borrowedBook);
        Book actual = bookService.borrowBook(bookId, clientId);

        //then
        verify(borrowInfoRepository).save(borrowInfo);
        assertEquals(expected, actual);
    }

    @Test
    void returnBook_whenIdsValidAndBookWasBorrowed_thenReturnBook() {
        BookService bookService = new BookService(bookRepository, clientRepository, borrowInfoRepository);

        //given
        Long bookId = 34L;
        Long clientId = 256L;
        Book book = new Book(bookId, "BookName", true);
        Client client = new Client(clientId, "ClientName");
        BorrowInfo borrowInfo = new BorrowInfo(null, client, book, false);
        BorrowInfo updatedBorrowInfo = new BorrowInfo(null, client, book, true);
        Book expected = new Book(bookId, "BookName", false);

        //when
        when(borrowInfoRepository.findByBookIdAndClientIdAndIsReturned(bookId, clientId, true)).thenReturn(borrowInfo);
        when(bookRepository.getReferenceById(bookId)).thenReturn(book);
        when(bookRepository.save(expected)).thenReturn(expected);
        Book actual = bookService.returnBook(bookId, clientId);

        //then
        verify(borrowInfoRepository).save(updatedBorrowInfo);
        assertEquals(expected, actual);
    }
}