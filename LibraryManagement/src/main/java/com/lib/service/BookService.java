package com.lib.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lib.model.Book;
import com.lib.model.User;
import com.lib.repository.BookRepository;
import com.lib.repository.UserRepository;

@Service
public class BookService {
	
	@Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
    	
        return bookRepository.findById(id).orElse(null);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book borrowBook(Long bookId, Long userId) {
        Book book = findById(bookId);
        User user = userRepository.findById(userId).orElse(null);

        if (book != null && user != null) {
            book.setBorrowedBy(user);
            book.setBorrowed(true);
            book.setDueDate(LocalDate.now().plusDays(14));
            return save(book);
        }
        return null;
    }

    public Book returnBook(Long bookId, Book book) {
        Book retrieved_book = findById(bookId);
//        System.out.println(book);
        if (book != null ) {
            LocalDate currentDate = book.getDueDate();
            LocalDate dueDate = retrieved_book.getDueDate();

            if (currentDate.isAfter(dueDate)) {
//                long daysLate = ChronoUnit.DAYS.between(dueDate, currentDate);
            	int daysLate = Period.between(dueDate, currentDate).getDays();
                double fine = daysLate * 10.0; 
                book.setFine(fine);
            }

            book.setBorrowedBy(null);
            book.setBorrowed(false);
//            book.setDueDate(null);
            return save(book);
        }
        return null;
    }
}