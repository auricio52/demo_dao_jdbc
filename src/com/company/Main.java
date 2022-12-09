package com.company;

import com.company.daos.DepartmentDao;
import com.company.daos.SellerDao;
import com.company.entities.Department;
import com.company.entities.Seller;
import com.company.factories.DaoFactory;

import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

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

        System.out.println("--- TEST 5: update seller");
        seller.setName("Maria");
        seller.setEmail("maria@gmail.com");
        sellerDao.update(seller);
        System.out.println("Updated! New id = " + seller.getId());
        System.out.println();

        System.out.println("--- TEST 6: delete seller");
        sellerDao.deleteById(12);
        System.out.println("Delete completed!");
        System.out.println();

        System.out.println("--- TEST 7: insert department");
        Department newDepartment = new Department(null, "Departmento 10");
        departmentDao.insert(newDepartment);
        System.out.println("Inserted! New id = " + newDepartment.getId());
        System.out.println();

        System.out.println("--- TEST 8: update department");
        newDepartment = new Department(10, "Departmento 11");
        departmentDao.update(newDepartment);
        System.out.println("Department updated!");
        System.out.println();

        System.out.println("--- TEST 9: delete department");
        departmentDao.deleteById(12);
        System.out.println("Department deleted!");
        System.out.println();

        System.out.println("--- TEST 10: find all departments");
        List<Department> departments = departmentDao.findAll();
        for (Department department1 : departments) {
            System.out.println(department1);
        }
        System.out.println();

        System.out.println("--- TEST 11: find department by id");
        Department dep = departmentDao.findById(4);
        System.out.println("Department: " + dep);
        System.out.println();
    }
}
