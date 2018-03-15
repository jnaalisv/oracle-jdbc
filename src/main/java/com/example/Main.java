package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final String sql = "declare outParam1 varchar2(100); begin demoSp('Juho', outParam1); DBMS_OUTPUT.PUT_LINE('dbms_out: '||outParam1); end;";

    public static void main(String[] args) {
        logger.info("starting");

        final OracleDb oracleDb = new OracleDb("system", "oracle", "xe", "jdbc:oracle:thin:@localhost:1521:xe");

        oracleDb.executeSql(sql);
    }
}
