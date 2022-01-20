package com.example.library.services;

import com.example.library.Main;
import com.example.library.StaticHelpers;
import com.example.library.model.database.DatabaseFactory;
import com.example.library.model.database.MySQLDatabase;
import com.example.library.model.domain.Address;
import com.example.library.model.domain.Member;
import com.example.library.model.domain.Role;
import com.example.library.model.domain.RoleType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MemberService {
    PreparedStatement statement;

    public void addMember(Member member) throws  Exception{
        if(member == null)
            new Exception("Member cannot be null");

        try{

            AddressService addressService = new AddressService();
            Address address = member.getAddress();
            int id = addressService.addAddress(new Address(address.getId(),address.getStreet(),address.getCity(),address.getState(), address.getPostalCode()));

            Connection con = StaticHelpers.connection;
            statement = con.prepareStatement("insert into member(password,first_name,last_name,phone_number,adress_id) values(?,?,?,?,?)");

            statement.setString(1, member.getPassword());
            statement.setString(2, member.getFirstName());
            statement.setString(3,member.getLastName());
            statement.setString(4, member.getPhoneNumber());
            statement.setInt(5, id);
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
                        new Role(2,RoleType.MEMEBER.toString()));
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

            String query = "update member set first_name=?,last_name=?,phone_number=? where id=? and adress_id=?";

            statement = con.prepareStatement(query);

            statement.setString(1, member.getFirstName());
            statement.setString(2, member.getLastName());
            statement.setString(3,member.getPhoneNumber());
            statement.setInt(4, member.getId());
            statement.setInt(5, member.getAddress().getId());
            statement.executeUpdate();

            AddressService addressService = new AddressService();
            addressService.updateAddress(member.getAddress());

        }catch (Exception ex){
            Logger.getLogger(MySQLDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
