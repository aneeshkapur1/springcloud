package com.work.bookservice.service;

import com.work.bookservice.exception.BookAlreadyExistsException;
import com.work.bookservice.exception.BookNotFoundException;
import com.work.bookservice.model.Book;
import com.work.bookservice.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public boolean createBook( Book book) throws BookAlreadyExistsException{
        final Optional<Book> book1 = bookRepo.findById(book.getId());
        if (book1.isPresent()){
            throw new BookAlreadyExistsException("Book with this id already exists.");
        }
        bookRepo.save(book);
        return true;
    }


    public Book updateBook(Book book, Long bookId) throws BookNotFoundException{
        final Optional<Book> book1 = bookRepo.findById(bookId);
        if(!book1.isPresent()){
            throw new BookNotFoundException("Book with this id not found");
        }
        Book book2 = book1.get();
        book2.setId(book.getId());
        book2.setAuthor(book.getAuthor());
        book2.setTitle(book.getTitle());
        return book2;
    }

    public Boolean deleteBook(Long bookId) throws BookNotFoundException{
        final Optional<Book> book1 = bookRepo.findById(bookId);
        if(!book1.isPresent()){
            throw new BookNotFoundException("Book with this id not found");
        }
        bookRepo.deleteById(bookId);
        return true;
    }

    public Book getBookById(Long bookId) throws BookNotFoundException{

        final Optional<Book> book1 = bookRepo.findById(bookId);
        if(!book1.isPresent()){
            throw new BookNotFoundException("Book with this id not found");
        }
        return book1.get();
    }

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

}
