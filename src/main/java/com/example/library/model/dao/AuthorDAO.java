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

    public static int insert(Author author){
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
        return selectLastId();
    }

    private static int selectLastId(){
        String sql = "SELECT * FROM author ORDER BY idAuthor DESC LIMIT 1";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return -1;
    }
}
