package com.example.library.services;

import com.example.library.model.dao.BookDAO;
import com.example.library.model.domain.Book;

public class BookService {
    public static void addBook(Book book){
        BookDAO.insert(book);
    }

    public static Book getById(int id) { return BookDAO.getById(id);}

    public static Book getByISBN(String isbn) { return BookDAO.getByISBN(isbn);}

    public static boolean updateCopyNumber(Book book, int n) {
        BookDAO.updateCopyNumber(book.getId(), n);
        BookCopyService.addBookCopy(book, n);
        return true;
    }
}
