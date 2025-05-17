package org.yasinkanli.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yasinkanli.librarymanagement.entity.Author;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long>{
}
