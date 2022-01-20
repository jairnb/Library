package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.domain.Author;
import com.example.library.model.domain.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
            stmt.setString(5, Integer.toString(book.getAuthor().getId()));
            stmt.execute();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<Book> selectAll(){
        List<Book> bookList = new ArrayList<>();

        String sql = "SELECT * FROM book";

        try {
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book b = new Book(rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(5), AuthorDAO.getById(rs.getInt(5)));
                b.setId(rs.getInt(1));
                bookList.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }
}
