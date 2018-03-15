package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("starting");

        final OracleDb oracleDb = new OracleDb("system", "oracle", "xe", "jdbc:oracle:thin:@localhost:1521:xe");

        oracleDb.executeQuery("select sysdate from dual", rs -> {
            try {
                logger.info("rs[0] "+ rs.getString(1));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
