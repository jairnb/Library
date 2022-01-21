package com.example.library.services;

import com.example.library.model.dao.FineDAO;
import com.example.library.model.domain.Checkout;
import com.example.library.model.domain.Fine;

import java.util.List;

public class FineService {

    public static boolean addFine(Checkout checkout){
        return FineDAO.addFine(checkout);
    }

    public static List<Fine> selectAll() { return FineDAO.selectAll(); }
}
