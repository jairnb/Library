package com.example.library.services;

import com.example.library.model.dao.BookDAO;
import com.example.library.model.domain.Book;

public class BookService {
    public static void addBook(Book book){
        BookDAO.insert(book);
    }

    public static Book getById(int id) { return BookDAO.getById(id);}

    public static boolean updateCopyNumber(int id, int n) { return BookDAO.updateCopyNumber(id, n);}
}
