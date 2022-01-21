package com.example.library.services;

import com.example.library.model.dao.BookDAO;
import com.example.library.model.dao.CheckoutDAO;
import com.example.library.model.domain.Book;
import com.example.library.model.domain.Checkout;

import java.util.List;

public class CheckinService {
    public static List<Checkout> selectAllUserCheckout(int userId) throws Exception {
        return CheckoutDAO.selectAllUserCheckout(userId);
    }

    public static boolean checkin(Checkout checkout){
        CheckoutDAO.updateIsReturned(checkout);
        updateBookNumberAvailable(checkout.getBook());
        return true;
    }
    public static boolean updateBookNumberAvailable(Book book){
        BookDAO.updateNumberBookAvailable(book, "checkin");
        return true;
    }
}
