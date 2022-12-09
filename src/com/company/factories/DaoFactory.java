package com.company.factories;

import com.company.daos.DepartmentDao;
import com.company.daos.SellerDao;
import com.company.daos.impl.DepartmentDaoJDBC;
import com.company.daos.impl.SellerDaoJDBC;
import com.company.db.DB;

public class DaoFactory {
    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }
    public static DepartmentDao createDepartmentDao() { return new DepartmentDaoJDBC(DB.getConnection()); }
}
