package com.example.library.services;

import com.example.library.model.dao.BookDAO;
import com.example.library.model.dao.CheckoutDAO;
import com.example.library.model.domain.Book;
import com.example.library.model.domain.Checkout;
import com.example.library.model.domain.Member;

import java.time.LocalDate;
import java.util.List;

public class CheckoutService {

    public List<Checkout> selectAll(){
        return CheckoutDAO.selectAll();
    }

    public static String checkoutBook(String memberId, String isbn) throws Exception {
        Book book = BookService.getByISBN(isbn);
        Member member = MemberService.getMemberByUserId(memberId);
        String msg = "500 Server Error!";
        if (member != null){
            if (book != null){
                if (book.getNumberAvailable() > 0){
                    Checkout checkout = new Checkout(calculateDueDate(book.getMaxDate()), LocalDate.now(), book, member);
                    CheckoutDAO.addCheckout(checkout);
                    updateBookNumberAvailable(book);
                    msg = "Success";
                } else {
                    msg = "Book not available!";
                }
            } else {
                msg = "Book not found!";
            }
        } else {
            msg = "Id Member not found!";
        }

        return msg;
    }

    private static LocalDate calculateDueDate(String date){
        LocalDate localDate = LocalDate.now();
        return localDate.plusDays(Long.parseLong(date));
    }

    private static boolean updateBookNumberAvailable(Book book){
        BookDAO.updateNumberBookAvailable(book, "checkout");
        return true;
    }
}
