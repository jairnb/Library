package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.domain.Address;
import com.example.library.model.domain.Author;
import com.example.library.services.AddressService;

import java.sql.*;
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
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new Author(rs.getString(4), rs.getString(5), rs.getString(6), AddressDAO.getById(rs.getInt(7)), rs.getString(2), rs.getString(3));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static int addAuthor(Author author){
        if(author == null)
            new Exception("Author cannot be null");

        String sql = "INSERT INTO author(credential, shortBio, first_name, last_name, phone_number, adress_id) VALUES(?,?,?,?,?,?)";

        try{
            Connection con = StaticHelpers.connection;
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            AddressService addressService = new AddressService();
            Address address = author.getAddress();
            int address_id = addressService.addAddress(new Address(address.getStreet(),address.getCity(),address.getState(), address.getPostalCode()));

            stmt.setString(1, author.getCredentials());
            stmt.setString(2, author.getShortBio());
            stmt.setString(3, author.getFirstName());
            stmt.setString(4, author.getLastName());
            stmt.setString(5, author.getPhoneNumber());
            stmt.setInt(6, address_id);

            int rowAffected = stmt.executeUpdate();

            if(rowAffected == 1){
                int id = 0;
                ResultSet rs = stmt.getGeneratedKeys();
                if(rs.next()){
                    id = rs.getInt(1);
                    return  id;
                }
            }

        }catch(SQLException ex){
            Logger.getLogger(AuthorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
