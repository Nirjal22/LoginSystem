package com.login;

import java.sql.*;

public class UsersDAO {
    private final String url = "jdbc:mysql://localhost:3306/aliens";
    private final String uName = "root";
    private final String password = "Brinjal@32";

    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            conn = DriverManager.getConnection(url, uName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public Users loginUser(String username, String passKey) {
        String sql = "select * from users where username=? and passKey=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, passKey);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Users(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("passKey")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Users registerUser(String username, String passKey) {
        String sql = "INSERT INTO users(username,passKey) VALUES(?,?)";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, passKey);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean verifyUser(String username) {
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
