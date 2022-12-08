package com.company;

import com.company.daos.SellerDao;
import com.company.entities.Department;
import com.company.entities.Seller;
import com.company.factories.DaoFactory;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("--- TEST 1: find seller by id");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);
        System.out.println();

        System.out.println("--- TEST 2: find sellers by department");
        Department department = new Department(2, null);
        List<Seller> sellers = sellerDao.findByDepartment(department);
        for (Seller seller1 : sellers) {
            System.out.println(seller1);
        }
        System.out.println();

        System.out.println("--- TEST 3: find all sellers");
        sellers = sellerDao.findAll();
        for (Seller seller1 : sellers) {
            System.out.println(seller1);
        }
        System.out.println();

        System.out.println("--- TEST 4: insert seller");
        seller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, department);
        sellerDao.insert(seller);
        System.out.println("Inserted! New id = " + seller.getId());
        System.out.println();
    }
}
