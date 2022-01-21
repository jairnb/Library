package com.example.library.services;

import com.example.library.model.dao.BookDAO;
import com.example.library.model.dao.CheckoutDAO;
import com.example.library.model.domain.Book;
import com.example.library.model.domain.Checkout;

import java.util.List;

public class CheckinService {
    public static List<Checkout> selectAllUserCheckout(int id) throws Exception {
        return CheckoutDAO.selectAllUserCheckout(id);
    }

    public static boolean updateBookNumberAvailable(Book book){
        BookDAO.updateNumberBookAvailable(book, "checkin");
        return true;
    }
}
