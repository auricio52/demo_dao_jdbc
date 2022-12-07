package com.company;

import com.company.daos.SellerDao;
import com.company.entities.Seller;
import com.company.factories.DaoFactory;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("--- TEST 1: find seller by id");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
    }
}
