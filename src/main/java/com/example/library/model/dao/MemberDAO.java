package com.example.library.model.dao;

import com.example.library.StaticHelpers;
import com.example.library.model.database.MySQLDatabase;
import com.example.library.model.domain.*;
import com.example.library.services.AddressService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberDAO {
    PreparedStatement statement;

    public void addMember(Member member) throws  Exception{
        if(member == null)
            new Exception("Member cannot be null");

        try{

            AddressService addressService = new AddressService();
            Address address = member.getAddress();
            int address_id = addressService.addAddress(new Address(address.getStreet(),address.getCity(),address.getState(), address.getPostalCode()));

            Connection con = StaticHelpers.connection;
            statement = con.prepareStatement("insert into member(password,first_name,last_name,phone_number,adress_id,role,user_id) values(?,?,?,?,?,?,?)");

            statement.setString(1, member.getPassword());
            statement.setString(2, member.getFirstName());
            statement.setString(3,member.getLastName());
            statement.setString(4, member.getPhoneNumber());
            statement.setInt(5, address_id);
            statement.setString(6,member.getRole());
            statement.setString(7,member.getUserId());
            statement.executeUpdate();

        }catch (SQLException ex){
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public  Member getMemberById(int id) throws  Exception{
        Member member = null;
        AddressService addressService = new AddressService();
        try{
            Connection con = StaticHelpers.connection;
            statement = con.prepareStatement("select * from member where id = ? limit 1");

            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Address address = addressService.getAddressById(rs.getInt("adress_id"));
                member = new Member(
                        id,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number"),
                        address,
                        "",
                        rs.getString("role"),
                        rs.getString("user_id"));
            }


        }catch (SQLException ex){
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  member;
    }


    public void updateMember(Member member) throws  Exception{
        if(member == null)
            new Exception("Member cannot be null");

        try{
            Connection con = StaticHelpers.connection;

            String query = "update member set first_name=?,last_name=?,phone_number=?, role=? where id=? and adress_id=?";

            statement = con.prepareStatement(query);

            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getLastName());
            statement.setString(3,member.getPhoneNumber());
            statement.setString(4, member.getRole());
            statement.setInt(5, member.getId());
            statement.setInt(6, member.getAddress().getId());

            statement.executeUpdate();

            AddressService addressService = new AddressService();
            addressService.updateAddress(member.getAddress());

        }catch (Exception ex){
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Member> getAllMembers(){
        List<Member> memberList = new ArrayList<>();

        String sql = "SELECT * FROM member";

        try {
            PreparedStatement stmt = StaticHelpers.connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Member member = new Member(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number"),
                        null,
                        "",
                        rs.getString("role"),
                        rs.getString("user_id"));

                memberList.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberList;
    }

    public Boolean isUserIdExisted(String userId) throws  Exception{
        Boolean isExisted = false;

        try{
            Connection con = StaticHelpers.connection;
            statement = con.prepareStatement("select * from member where user_id = ? limit 1");

            statement.setString(1, userId);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                isExisted = true;
            }

        }catch (SQLException ex){
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

        return  isExisted;
    }

}
