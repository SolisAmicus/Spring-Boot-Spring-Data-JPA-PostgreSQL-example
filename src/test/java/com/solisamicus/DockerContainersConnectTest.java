package com.solisamicus;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DockerContainersConnectTest {
    @Container
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @BeforeAll
    static void setUp() {
        postgres.start();
    }

    @Test
    void testSimpleSelect() throws Exception {
        Connection connection = postgres.createConnection("");
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT 1");
            resultSet.next();
            int result = resultSet.getInt(1);
            assert result == 1;
        }
    }
}
