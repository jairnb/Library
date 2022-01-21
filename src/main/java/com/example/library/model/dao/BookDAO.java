package com.example.library.model.dao;

import com.example.library.StaticHelpers;
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
        String sql = "INSERT INTO book(title, isbn, availability, borrow_day_number, author_idAuthor, numberAvailable, numberOfCopy) VALUES(?,?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.setString(3, book.getAvailability());
            stmt.setString(4, book.getMaxDate());
            stmt.setString(5, Integer.toString(book.getAuthor().getId()));
            stmt.setInt(6, book.getNumberAvailable());
            stmt.setInt(7, book.getNumberOfCopy());
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
                Book b = new Book(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), AuthorDAO.getById(rs.getInt(6)));
                b.setId(rs.getInt(1));
                b.setNumberAvailable(rs.getInt(7));
                b.setNumberOfCopy(rs.getInt(8));

                bookList.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public static Book getById(int id){
        String sql = "SELECT * FROM book where id=?";

        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Book b = new Book(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), AuthorDAO.getById(rs.getInt(6)));
                b.setId(rs.getInt(1));
                b.setNumberAvailable(rs.getInt(7));
                b.setNumberOfCopy(rs.getInt(8));
                return b;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean updateCopyNumber(int idBook, int number){
        Book b = getById(idBook);
        int new_number = b.getNumberOfCopy() + number;
        int new_number_available = b.getNumberAvailable() + number;

        String sql = "Update book set numberOfCopy=?, numberAvailable=? WHERE id=?";

        try {
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setInt(1, new_number);
            stmt.setInt(2, new_number_available);
            stmt.setInt(3, idBook);
            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Book getByISBN(String isbn){
        String sql = "SELECT * FROM book where isbn=?";

        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setString(1, isbn);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Book b = new Book(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), AuthorDAO.getById(rs.getInt(6)));
                b.setId(rs.getInt(1));
                b.setNumberAvailable(rs.getInt(7));
                b.setNumberOfCopy(rs.getInt(8));
                return b;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void updateNumberBookAvailable(Book book, String type){
        int new_number_available = book.getNumberAvailable();
        if ("checkout".equals(type)){
            new_number_available -= 1;
        }
        else if ("checkin".equals(type)){
            new_number_available += 1;
        }

        String sql = "Update book set numberAvailable=? WHERE id=?";

        try {
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setInt(1, new_number_available);
            stmt.setInt(2, book.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
