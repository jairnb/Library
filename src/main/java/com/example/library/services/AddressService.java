package com.example.library.services;

import com.example.library.StaticHelpers;
import com.example.library.model.database.MySQLDatabase;
import com.example.library.model.domain.Address;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressService {
    PreparedStatement statement;

    public int addAddress(Address address) throws  Exception{
        if(address == null)
            new Exception("Address cannot be null");

        try{
            Connection con = StaticHelpers.connection;

            String query = "insert into adress(street,city,state,zip) " +
                    "VALUE(?,?,?,?)";

            statement = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            System.out.println(address.getStreet());

            statement.setString(1, address.getStreet());
            statement.setString(2, address.getCity());
            statement.setString(3,address.getState());
            statement.setString(4, address.getPostalCode());

            int rowAffected = statement.executeUpdate();

            if(rowAffected == 1){
                int id = 0;
                ResultSet rs = statement.getGeneratedKeys();
                if(rs.next()){
                    id = rs.getInt(1);
                    return  id;
                }
            }
            return  0;
        }catch (Exception ex){
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return  0;
        }
    }
}
