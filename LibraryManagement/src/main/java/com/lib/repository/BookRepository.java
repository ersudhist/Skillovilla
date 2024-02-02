package com.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lib.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
