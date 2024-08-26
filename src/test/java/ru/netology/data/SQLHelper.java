package ru.netology.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();
    public static String expectedPaymentApproved = "APPROVED";
    public static String expectedPaymentDeclined = "DECLINED";

    private SQLHelper() {};

//    @SneakyThrows
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    public static String getDebitCardStatus() throws SQLException {
        val statusSQL = "SELECT status FROM payment_entity;";
        String status = "";

        try (val statusStmt = getConnection().createStatement();) {

            try (val result = statusStmt.executeQuery(statusSQL)) {
                if (result.next()) {
                    status = result.getString(1);
                }
            }
        }
        return status;
    }

    public static String getCreditCardStatus() throws SQLException {
        val statusSQL = "SELECT status FROM credit_request_entity;";
        String status = "";

        try (val statusStmt = getConnection().createStatement();) {

            try (val result = statusStmt.executeQuery(statusSQL)) {
                if (result.next()) {
                    status = result.getString(1);
                }
            }
        }
        return status;
    }

    @SneakyThrows
    public static void cleanBase() {
        val connection = getConnection();
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
    }
}