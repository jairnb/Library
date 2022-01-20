package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.domain.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressDAO {

    public static Address insert(Address address){
        String sql = "INSERT INTO adress(street, city, state, zip) VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setString(1, address.getStreet());
            stmt.setString(2, address.getCity());
            stmt.setString(3, address.getState());
            stmt.setString(4, address.getPostalCode());
            stmt.execute();
        }catch(SQLException ex){
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectLastId();
    }

    private static Address selectLastId(){
        String sql = "SELECT * FROM adress ORDER BY ID DESC LIMIT 1";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
               Address a = new Address(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
               a.setId(rs.getInt(1));
               return  a;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
