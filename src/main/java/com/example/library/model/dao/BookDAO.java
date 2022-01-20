package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.domain.Author;
import com.example.library.model.domain.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDAO {
    public static boolean insert(Book book){
        String sql = "INSERT INTO book(title, isbn, availability, borrow_day_number, author_idAuthor) VALUES(?,?,?,?,?)";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.setString(3, book.getAvailability());
            stmt.setString(4, book.getMaxDate());
            stmt.setString(5, Integer.toString(book.getAuthor_id()));
            stmt.execute();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
