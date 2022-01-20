package com.example.library.services;

import com.example.library.model.dao.BookDAO;
import com.example.library.model.domain.Book;

public class BookService {
    public static void addBook(Book book){
        BookDAO.insert(book);
    }
}
