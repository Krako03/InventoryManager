package com.InventoryManager.repositories;

import com.InventoryManager.model.Employee;
import com.InventoryManager.utilities.DataBaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements CrudRepository<Employee> {
    private final DataBaseConnection db;

    public EmployeeRepository(DataBaseConnection db) {
        this.db = db;
    }

    @Override
    public void save(Employee employee) {
        String query = "INSERT INTO employees (name, mail) VALUES (?, ?)";
        db.execute(query, employee.getName(), employee.getMail());
    }

    @Override
    public Optional<Employee> findById(int id) {
        String query = "SELECT * FROM employees WHERE id = ?";
        return Optional.ofNullable(db.findOne(query, this::mapResultSetToEmployee, id));
    }

    @Override
    public List<Employee> findAll() {
        String query = "SELECT * FROM employees";
        return db.findMany(query, this::mapResultSetToEmployee);
    }

    @Override
    public void update(Employee employee) {
        String query = "UPDATE employees SET name = ?, mail = ? WHERE id = ?";
        db.execute(query, employee.getName(), employee.getMail(), employee.getId());
    }

    @Override
    public void deleteById(int id) {
        String query = "DELETE FROM employees WHERE id = ?";
        db.execute(query, id);
    }

    private Employee mapResultSetToEmployee(ResultSet rs) {
        try {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setMail(rs.getString("mail"));
            return employee;
        } catch (SQLException e) {
            throw new RuntimeException("Error mapping Employee", e);
        }
    }
}
