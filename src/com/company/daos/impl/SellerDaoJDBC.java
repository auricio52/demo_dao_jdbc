package com.company.daos.impl;

import com.company.daos.SellerDao;
import com.company.db.DB;
import com.company.db.DBException;
import com.company.entities.Department;
import com.company.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SellerDaoJDBC implements SellerDao {
    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                    "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, seller.getName());
            preparedStatement.setString(2, seller.getEmail());
            preparedStatement.setDate(3, new Date(seller.getBirthDate().getTime()));
            preparedStatement.setDouble(4, seller.getBaseSalary());
            preparedStatement.setInt(5, seller.getDepartment().getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    seller.setId(id);
                }
                DB.closeResultSet(resultSet);
            } else {
                throw new DBException("Unexpected error! No rows affected!");
            }
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        } finally {
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Seller findById(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT seller.*, department.Name AS DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON department.Id = seller.DepartmentId WHERE seller.Id = ?");
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Department department = instantiateDepartment(resultSet);
                Seller seller = instantiateSeller(resultSet, department);
                return seller;
            }

            return null;
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT seller.*, department.Name AS DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON department.Id = seller.DepartmentId ORDER BY Name");

            resultSet = preparedStatement.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departmentMap = new HashMap<>();

            while (resultSet.next()) {
                int departmentId = resultSet.getInt("DepartmentId");
                Department dep = departmentMap.get(departmentId);

                if (dep == null) {
                    dep = instantiateDepartment(resultSet);
                    departmentMap.put(departmentId, dep);
                }
                Seller seller = instantiateSeller(resultSet, dep);
                sellers.add(seller);
            }

            return sellers;
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preparedStatement);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT seller.*, department.Name AS DepName " +
                    "FROM seller INNER JOIN department " +
                    "ON department.Id = seller.DepartmentId WHERE DepartmentId = ? ORDER BY Name");
            preparedStatement.setInt(1, department.getId());

            resultSet = preparedStatement.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            Map<Integer, Department> departmentMap = new HashMap<>();

            while (resultSet.next()) {
                int departmentId = resultSet.getInt("DepartmentId");
                Department dep = departmentMap.get(departmentId);

                if (dep == null) {
                    dep = instantiateDepartment(resultSet);
                    departmentMap.put(departmentId, dep);
                }
                Seller seller = instantiateSeller(resultSet, dep);
                sellers.add(seller);
            }

            return sellers;
        } catch (SQLException exception) {
            throw new DBException(exception.getMessage());
        } finally {
            DB.closeResultSet(resultSet);
            DB.closeStatement(preparedStatement);
        }
    }

    private Seller instantiateSeller(ResultSet resultSet, Department department) throws SQLException {
        Seller seller = new Seller();
        seller.setId(resultSet.getInt("Id"));
        seller.setName(resultSet.getString("Name"));
        seller.setEmail(resultSet.getString("Email"));
        seller.setBaseSalary(resultSet.getDouble("BaseSalary"));
        seller.setBirthDate(resultSet.getDate("BirthDate"));
        seller.setDepartment(department);
        return seller;
    }

    private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt("DepartmentId"));
        department.setName(resultSet.getString("DepName"));
        return department;
    }
}
