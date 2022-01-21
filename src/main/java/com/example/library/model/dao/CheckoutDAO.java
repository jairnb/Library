package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.domain.Checkout;
import com.example.library.services.BookService;
import com.example.library.services.MemberService;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckoutDAO {

    public static Checkout addCheckout(Checkout checkout){
        String sql = "INSERT INTO checkout_entriers(due_date, checkout_date, book_id, member_id) VALUES(?,?,?,?)";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(checkout.getDueDate()));
            stmt.setDate(2, Date.valueOf(checkout.getCheckoutDate()));
            stmt.setInt(3, checkout.getBook().getId());
            stmt.setInt(4, checkout.getMember().getId());
            stmt.execute();
        }catch(SQLException ex){
            Logger.getLogger(AddressDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return selectLast();
    }

    public static Checkout getById(int id){
        String sql = "SELECT * FROM checkout_entriers Where id=? ORDER BY id DESC LIMIT 1";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Checkout c = new Checkout(rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate(), BookService.getById(rs.getInt(4)), MemberService.getStaticMemberById(rs.getInt(5)));
                c.setId(rs.getInt(1));
                return c;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Checkout> selectAll(){
        List<Checkout> checkList = new ArrayList<>();
        String sql = "SELECT * FROM checkout_entriers ORDER BY due_date DESC";

        try {
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Checkout c = new Checkout(rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate(), BookService.getById(rs.getInt(4)), MemberService.getStaticMemberById(rs.getInt(5)));
                c.setId(rs.getInt(1));
                c.setReturned(rs.getBoolean(6));
                checkList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkList;
    }

    public static List<Checkout> selectAllUserCheckout(int userId){
        List<Checkout> checkList = new ArrayList<>();
        String sql = "SELECT * FROM checkout_entriers where member_id=? ORDER BY due_date DESC";

        try {
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Checkout c = new Checkout(rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate(), BookService.getById(rs.getInt(4)), MemberService.getStaticMemberById(rs.getInt(5)));
                c.setId(rs.getInt(1));
                c.setReturned(rs.getBoolean(6));
                checkList.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkList;
    }

    private static Checkout selectLast(){
        String sql = "SELECT * FROM checkout_entriers ORDER BY id DESC LIMIT 1";
        try{
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Checkout c = new Checkout(rs.getDate(2).toLocalDate(), rs.getDate(3).toLocalDate(), BookService.getById(rs.getInt(4)), MemberService.getStaticMemberById(rs.getInt(5)));
                c.setId(rs.getInt(1));
                return c;
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateIsReturned(Checkout checkout){

        String sql = "Update checkout_entriers set is_returned=? WHERE id=?";

        try {
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, checkout.getId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
