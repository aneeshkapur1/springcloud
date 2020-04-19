package com.work.bookservice.controller;

import com.work.bookservice.exception.BookAlreadyExistsException;
import com.work.bookservice.exception.BookNotFoundException;
import com.work.bookservice.model.Book;
import com.work.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;


    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Book>> getAllBooks(final HttpServletRequest req, HttpServletResponse res){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable("bookId") long bookId){
        Book book = null;
        try{
            book = bookService.getBookById(bookId);
        } catch(BookNotFoundException e){
            return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book,HttpStatus.OK);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable long bookId){
        Book book1 = null;
        try{
            book1 = bookService.updateBook(book,bookId);
        } catch(BookNotFoundException e){
            return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book1,HttpStatus.OK);
    }

    @PostMapping("/savebook")
    public ResponseEntity<?> saveBook(@RequestBody Book book){
        try{
            bookService.createBook(book);
        } catch(BookAlreadyExistsException e){
            return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Book>(book,HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable long bookId){

        try{
             bookService.deleteBook(bookId);
        } catch(BookNotFoundException e){
            return new ResponseEntity<String>("{ \"message\":\"" + e.getMessage() + "\"}", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("Book Deleted Successfully",HttpStatus.OK);
    }

}
