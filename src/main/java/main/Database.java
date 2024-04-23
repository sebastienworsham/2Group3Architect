package main;

import org.h2.tools.Server;

import java.sql.*;

public class Database {
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost:9092/./DBDemo;DB_CLOSE_ON_EXIT=FALSE;";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa";

    private Server server;
    private Connection conn;
    private Statement stmt;

    public Database() throws SQLException {
        server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers", "-ifNotExists").start();
        conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
        stmt = conn.createStatement();

        stmt.execute("CREATE TABLE IF NOT EXISTS users(username VARCHAR(64) PRIMARY KEY )");
    }

    public void insert(String username) throws SQLException {
        String sql = "INSERT INTO users(username) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ps.executeUpdate();
    }
}
