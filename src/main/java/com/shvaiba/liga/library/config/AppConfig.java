package com.shvaiba.liga.library.config;

import com.shvaiba.liga.library.persitance.entity.Book;
import com.shvaiba.liga.library.persitance.entity.Books;
import com.shvaiba.liga.library.persitance.entity.Client;
import com.shvaiba.liga.library.persitance.entity.Clients;
import com.shvaiba.liga.library.persitance.entity.Library;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Library configureLibrary() {
        Library library = new Library();
        library.setBooks(configureBooks(configureClients()));
        library.setClients(configureClients());
        return library;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Books configureBooks(Clients clients) {
        Books books = new Books(new ArrayList<>(), 0L);
        books.addBook(new Book(1L, "Bible", clients.getClients().get(0).getId()));
        books.addBook(new Book(1L, "Torah", clients.getClients().get(1).getId()));
        books.addBook(new Book(1L, "Quran", clients.getClients().get(2).getId()));
        books.addBook(new Book(1L, "Tripitaka", clients.getClients().get(3).getId()));
        return books;
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Clients configureClients() {
        Clients clients = new Clients(new ArrayList<>(), 0L);
        clients.addClient(new Client(1L, "Jesus Christ"));
        clients.addClient(new Client(2L, "G-d"));
        clients.addClient(new Client(3L, "Allah"));
        clients.addClient(new Client(4L, "Buddah"));
        return clients;
    }


}
