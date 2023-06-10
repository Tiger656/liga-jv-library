package com.shvaiba.liga.library.persitance;

import com.shvaiba.liga.library.persitance.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByIsBorrowedTrue();
}
