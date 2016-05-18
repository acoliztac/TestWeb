package main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    final static String connectionParameter = "jdbc:mysql://localhost:3306/testweb?" +
                                              "user=root&" +
                                              "password=root&" +
                                              "useSSL=false";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(connectionParameter);
    }

    public static List<Employee> getEmployees() throws SQLException, ClassNotFoundException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM employees");
             ResultSet resultSet = ps.executeQuery()) {
            return getEmployeesArray(resultSet);
        }
    }

    private static List<Employee> getEmployeesArray(ResultSet resultSet) throws SQLException {
        ArrayList<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String position = resultSet.getString("position");
            String department = resultSet.getString("department");
            employees.add(new Employee(id, name, surname, position, department));
        }
        return employees;
    }

    public static void deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM employees WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public static void addEmployee(String name, String surname, String position, String department) throws SQLException, ClassNotFoundException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO employees (name, surname, position, department) VALUES (?,?,?,?)")) {
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, position);
            ps.setString(4, department);
            ps.executeUpdate();
        }
    }

    public static List<Employee> filterEmployees(String name, String surname) throws SQLException, ClassNotFoundException {
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM employees WHERE name=\"" + name + "\" or " +
                     "surname=\"" + surname + "\"");
             ResultSet resultSet = ps.executeQuery()) {
            return getEmployeesArray(resultSet);
        }
    }
}
