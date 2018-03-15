package com.example;

import oracle.jdbc.pool.OracleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class OracleDb {
    private static final Logger logger = LoggerFactory.getLogger(OracleDb.class);

    private DataSource oracleDataSource;

    public OracleDb(String username, String password, String service, String url) {
        this.oracleDataSource = buildDataSource(username, password, service, url);
    }

    public void withConnection(final Consumer<Connection> connectionConsumer) {
        try(Connection conn = oracleDataSource.getConnection()) {
            connectionConsumer.accept(conn);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void executeQuery(final String sql, final Consumer<ResultSet> resultSetConsumer) {
        withConnection(connection -> {
            try(PreparedStatement stmt = connection.prepareStatement(sql)) {
                final ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    resultSetConsumer.accept(rs);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private static DataSource buildDataSource(String username, String password, String service, String url) {
        try {
            final OracleDataSource ds = new OracleDataSource();
            ds.setServiceName(service);
            ds.setPortNumber(1521);
            ds.setUser(username);
            ds.setPassword(password);
            ds.setURL(url);
            return ds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
