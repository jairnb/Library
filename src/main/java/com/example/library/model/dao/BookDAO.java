package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.domain.Author;
import com.example.library.model.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDAO {
    public static boolean insert(Book book){
        try{
            Connection con = StaticHelpers.connection;
            String query = "INSERT INTO book(title, isbn, availability, borrow_day_number, numberAvailable, numberOfCopy) VALUES(?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getIsbn());
            stmt.setString(3, book.getAvailability());
            stmt.setString(4, book.getMaxDate());
            stmt.setInt(5, book.getNumberAvailable());
            stmt.setInt(6, book.getNumberOfCopy());

            int rowAffected = stmt.executeUpdate();
            int bookId = 0;
            if(rowAffected == 1){
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    bookId = rs.getInt(1);
                }
            }

            for(Author author : book.getAuthor()){
                int authorId = AuthorDAO.addAuthor(author);
                String book_authorQuery = "insert into book_author(book_id,author_id) values(?,?)";

                stmt = con.prepareStatement(book_authorQuery);
                stmt.setInt(1, bookId);
                stmt.setInt(2, authorId);
                stmt.executeUpdate();
            }

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
                Book b = new Book(
                        rs.getString("title"),
                        rs.getString("isbn"),
                        rs.getString("availability"),
                        rs.getString("borrow_day_number"),
                        null);
                b.setId(rs.getInt(1));
                b.setNumberAvailable(rs.getInt("numberAvailable"));
                b.setNumberOfCopy(rs.getInt("numberOfCopy"));

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
                Book b = new Book(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        null);
                b.setId(rs.getInt(1));
                b.setNumberAvailable(rs.getInt("numberAvailable"));
                b.setNumberOfCopy(rs.getInt("numberOfCopy"));
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
                Book b = new Book(rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        null);
                b.setId(rs.getInt(1));
                b.setNumberAvailable(rs.getInt("numberAvailable"));
                b.setNumberOfCopy(rs.getInt("numberOfCopy"));
                return b;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void updateNumberBookAvailable(Book book, String type){
        int number_available = getById(book.getId()).getNumberAvailable();
        int n = 0;
        if ("checkout".equals(type)){
            n = number_available - 1;
        }
        else if ("checkin".equals(type)){
            n = number_available + 1;
        }

        String sql = "Update book set numberAvailable=? WHERE id=?";

        try {
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setInt(1, n);
            stmt.setInt(2, book.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
