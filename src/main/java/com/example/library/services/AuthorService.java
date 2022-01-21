package com.example.library.services;

import com.example.library.model.dao.AuthorDAO;
import com.example.library.model.domain.Author;

public class AuthorService {
    AuthorDAO authorDAO;
    public void addAuthor(Author author){
        AuthorDAO.addAuthor(author);
    }
}
