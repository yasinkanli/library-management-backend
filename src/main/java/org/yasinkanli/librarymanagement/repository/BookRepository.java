package org.yasinkanli.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yasinkanli.librarymanagement.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);
}
