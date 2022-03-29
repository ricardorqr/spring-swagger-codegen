package com.spring.swagger.codegen.repository;

import com.spring.swagger.codegen.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
