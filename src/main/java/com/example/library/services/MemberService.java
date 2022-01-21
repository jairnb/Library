package com.example.library.services;
import com.example.library.UserSingleton;
import com.example.library.model.dao.MemberDAO;
import com.example.library.model.domain.Member;

import java.util.List;

public class MemberService {

    public void addMember(Member member) throws  Exception{
        if(member == null)
            new Exception("Member cannot be null");

        MemberDAO dao = new MemberDAO();
        dao.addMember(member);
    }

    public  Member getMemberById(int id) throws  Exception{
        MemberDAO dao = new MemberDAO();
        Member member = dao.getMemberById(id);

        return  member;
    }


    public void updateMember(Member member) throws  Exception{
        if(member == null)
            new Exception("Member cannot be null");

        MemberDAO dao = new MemberDAO();
        dao.updateMember(member);
    }

    public List<Member> getAllMembers() throws  Exception{
        MemberDAO dao= new MemberDAO();
        return dao.getAllMembers();
    }

    public Boolean isUserIdExisted(String userId) throws Exception {
        MemberDAO dao= new MemberDAO();
        return dao.isUserIdExisted(userId);
    }

    public boolean isUserPasswordCorrect(String userId,String password) throws Exception {
        MemberDAO dao= new MemberDAO();
        Member member = dao.getUser(userId,password);
        if(member != null){
            UserSingleton.createInstance(member.getUserId(),member.getRole());
            return true;
        }
        return false;
    }

    public static Member getMemberByUserId(String isbn) throws Exception {
        MemberDAO dao= new MemberDAO();
        return dao.getMemberByUserId(isbn);
    }

    public static  Member getStaticMemberById(int id) throws  Exception{
        MemberDAO dao = new MemberDAO();
        return  dao.getMemberById(id);

    }
}
