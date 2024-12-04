package ua.nure.st.kpp.example.demo.dao;

import ua.nure.st.kpp.example.demo.entity.Property;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDAO implements IDAO {

    
	private static final String URL = "jdbc:mysql://localhost:3306/real_estate_management"; // Ваша база даних
    private static final String USER = "your_user"; // Ваш користувач
    private static final String PASSWORD = "your_password"; // Ваш пароль
    
    private Connection connection;

    public MySQLDAO() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Property> getAllProperties() {
        List<Property> properties = new ArrayList<>();
        String query = "SELECT * FROM properties"; // Предположим, что у вас таблица 'properties'

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                String address = rs.getString("address");
                double price = rs.getDouble("price");

                Property property = new Property(name, type, address, price);
                properties.add(property);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }

    @Override
    public void addProperty(Property property) {
        String query = "INSERT INTO properties (name, type, address, price) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, property.getName());
            pstmt.setString(2, property.getType());
            pstmt.setString(3, property.getAddress());
            pstmt.setDouble(4, property.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePropertiesByName(String name) {
        String query = "DELETE FROM properties WHERE name = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Property> getPropertiesByType(String type) {
        List<Property> properties = new ArrayList<>();
        String query = "SELECT * FROM properties WHERE type = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, type);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    double price = rs.getDouble("price");

                    Property property = new Property(name, type, address, price);
                    properties.add(property);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return properties;
    }
}
