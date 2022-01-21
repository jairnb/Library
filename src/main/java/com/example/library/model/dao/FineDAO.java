package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.domain.Checkout;
import com.example.library.model.domain.Fine;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FineDAO {

    public static boolean  addFine(Checkout checkout){
        String sql = "INSERT INTO fine_record(datePaiment, checkoutEntriers_id) VALUES(?,?)";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setInt(2, checkout.getId());
            stmt.execute();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<Fine> selectAll(){
        List<Fine> fineList = new ArrayList<>();

        String sql = "SELECT * FROM fine_record";

        try {
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fine f = new Fine(rs.getDate(2).toLocalDate(), CheckoutDAO.getById(rs.getInt(3)));
                f.setId(rs.getInt(1));

                fineList.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fineList;
    }
}
