package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.domain.Address;
import com.example.library.model.domain.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorDAO {

    public static Author insert(Author author, Address address){
        String sql = "INSERT INTO author(credential, shortBio, first_name, last_name, phone_number, adress_id) VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setString(1, author.getCredentials());
            stmt.setString(2, author.getShortBio());
            stmt.setString(3, author.getFirstName());
            stmt.setString(4, author.getLastName());
            stmt.setString(5, author.getPhoneNumber());
            stmt.setString(6, Integer.toString(author.getAddress().getId()));
            stmt.execute();
        }catch(SQLException ex){
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectLastId(address);
    }

    private static Author selectLastId(Address address){
        String sql = "SELECT * FROM author ORDER BY idAuthor DESC LIMIT 1";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Author a = new Author(rs.getString(4), rs.getString(5), rs.getString(6), address, rs.getString(2), rs.getString(3));
                a.setId(rs.getInt(1));
                return a;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static Author getById(int id){
        String sql = "SELECT * FROM author where idAuthor=?";

        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setInt(1, 1);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Author(rs.getString(4), rs.getString(5), rs.getString(6), AddressDAO.getById(rs.getInt(7)), rs.getString(2), rs.getString(3));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
