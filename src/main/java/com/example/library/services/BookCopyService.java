package com.example.library.services;

import com.example.library.model.dao.BookCopyDAO;
import com.example.library.model.domain.Book;

public class BookCopyService {

    public static void addBookCopy(Book book, int copyNumber){
        for (int i = 0; i < copyNumber; i++){
            BookCopyDAO.addBookCopy(book);
        }
    }
}
