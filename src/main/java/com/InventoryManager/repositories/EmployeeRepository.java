package com.InventoryManager.repositories;

import com.InventoryManager.model.Employee;
import com.InventoryManager.model.Role;
import com.InventoryManager.utilities.DBController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements CrudRepository<Employee> {
    private final DBController db;

    public EmployeeRepository(DBController db) {
        this.db = db;
    }

    @Override
    public void save(Employee employee) {
        Connection connection = null;
        try {
            connection = db.getConnection(Connection.TRANSACTION_REPEATABLE_READ); // Obtiene una única conexión
            connection.setAutoCommit(false); // Inicia la transacción

            // Inserta Employee y obtiene su ID
            String insertEmployeeQuery = "INSERT INTO employees (name, mail) VALUES (?, ?) RETURNING id";
            Integer employeeId = db.findOne(connection, insertEmployeeQuery, rs -> {
                try {
                    return rs.getInt("id");
                } catch (SQLException e) {

                }
                return 0;
            }, employee.getName(), employee.getMail());

            if (employeeId != null) {
                String username = employee.getMail().split("@")[0];
                String defaultPassword = "default123";
                Role defaultRole = Role.EMPLOYEE;

                String insertUserQuery = "INSERT INTO users (employee_id, role, username, password) VALUES (?, ?, ?, ?)";
                db.execute(connection, insertUserQuery, employeeId, defaultRole.name(), username, defaultPassword);
            }

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    throw new RuntimeException("Error al hacer rollback", rollbackEx);
                }
            }
            throw new RuntimeException("Error al guardar empleado y usuario", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Restauramos el auto-commit
                    connection.close(); // Cerramos la conexión
                } catch (SQLException ignored) {}
            }
        }
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
        try (Connection connection = db.getConnection(Connection.TRANSACTION_SERIALIZABLE)) {
        //try (Connection connection = db.getConnection()) {
            connection.setAutoCommit(false);

            String query = "UPDATE employees SET name = ?, mail = ? WHERE id = ?";
            db.execute(connection, query, employee.getName(), employee.getMail(), employee.getId());

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando empleado", e);
        }
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
