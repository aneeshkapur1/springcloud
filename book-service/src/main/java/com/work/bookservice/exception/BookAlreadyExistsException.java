package com.work.bookservice.exception;

public class BookAlreadyExistsException  extends Exception{

    public BookAlreadyExistsException(String message) {
        super(message);
    }
}
