package com.company.daos.impl;

import com.company.daos.DepartmentDao;
import com.company.entities.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {
    private Connection connection;

    public DepartmentDaoJDBC() {

    }

    @Override
    public void insert(Department department) {

    }

    @Override
    public void update(Department department) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Department findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;



        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
