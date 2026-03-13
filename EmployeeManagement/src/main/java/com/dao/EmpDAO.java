package com.dao;

import com.model.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {

    public boolean addEmp(Emp emp) {
        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();
            if (con == null) {
                System.out.println("Connection is null! Cannot insert employee");
                return false;
            }

            String sql = "INSERT INTO emp (emp_name, job, salary, dept, hire_date, photo, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getJob());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getDept());
            ps.setDate(5, Date.valueOf(emp.getHireDate()));

            // photo (LONGBLOB)
            ps.setBytes(6, emp.getPhoto());

            // status (ENUM stored as String)
            ps.setString(7, emp.getStatus().name().toLowerCase());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    public List<Emp> getAllEmps() {
        List<Emp> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM emp ORDER BY emp_id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Emp emp = new Emp();
                emp.setId(rs.getInt("emp_id"));
                emp.setName(rs.getString("emp_name"));
                emp.setJob(rs.getString("job"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDept(rs.getString("dept"));
                emp.setHireDate(rs.getDate("hire_date").toLocalDate());
                emp.setPhoto(rs.getBytes("photo"));

                // Convert status string to enum
                emp.setStatus(Status.valueOf(rs.getString("status").toUpperCase()));

                list.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public Emp getEmpById(int id) {
        Emp emp = null;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM emp WHERE emp_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emp = new Emp();
                emp.setId(rs.getInt("emp_id"));
                emp.setName(rs.getString("emp_name"));
                emp.setJob(rs.getString("job"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDept(rs.getString("dept"));
                emp.setHireDate(rs.getDate("hire_date").toLocalDate());
                emp.setPhoto(rs.getBytes("photo"));
                emp.setStatus(Status.valueOf(rs.getString("status").toUpperCase()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emp;
    }
    public boolean updateEmp(Emp emp) {
        boolean status = false;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE emp SET emp_name=?, job=?, salary=?, dept=?, hire_date=?, status=?" 
                       + (emp.getPhoto() != null ? ", photo=?" : "") 
                       + " WHERE emp_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getJob());
            ps.setDouble(3, emp.getSalary());
            ps.setString(4, emp.getDept());
            ps.setDate(5, Date.valueOf(emp.getHireDate()));
            ps.setString(6, emp.getStatus().name().toLowerCase());

            int index = 7;
            if (emp.getPhoto() != null) {
                ps.setBytes(index++, emp.getPhoto());
            }

            ps.setInt(index, emp.getId());

            int rows = ps.executeUpdate();
            if (rows > 0) status = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    public boolean deleteEmp(int id) {
        boolean status = false;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM emp WHERE emp_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if(rows > 0) status = true;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return status;
    }


}
