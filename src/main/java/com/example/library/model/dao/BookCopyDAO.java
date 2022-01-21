package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.domain.Book;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookCopyDAO {

    public static boolean  addBookCopy(Book book){
        String sql = "INSERT INTO book_copy(book_id) VALUES(?)";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setInt(1, book.getId());
            stmt.execute();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


}
