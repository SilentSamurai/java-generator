package com.silent.samurai.service;

import com.silent.samurai.generators.BuilderPatternGenerator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.tools.databaseconnector.DatabaseConnectionSource;
import schemacrawler.tools.databaseconnector.SingleUseUserCredentials;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class JavaGeneratorTest {

    static final Logger logger = Logger.getLogger(JavaGeneratorTest.class);

    private static Connection getConnection() {
        final String connectionUrl =
                "jdbc:postgresql://localhost:5432/athena_db";
        final DatabaseConnectionSource dataSource =
                new DatabaseConnectionSource(connectionUrl);
        dataSource.setUserCredentials(new SingleUseUserCredentials("postgres", "root"));
        return dataSource.get();
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void mainTest() throws SQLException, SchemaCrawlerException, IOException {

        Package aPackage = this.getClass().getPackage();

        logger.info(this.getClass().getResource("templates/controller.jt"));
        logger.info(aPackage.getName());

        BuilderPatternGenerator.makeBuilderOf(JavaGenerator.class, "/Users/i353584/OneDrive - SAP SE/Personal-projects/spring-boot-generate");

        JavaGenerator generator = JavaGeneratorBuilder.builder()
                .schema("public")
                .projectRoot("path")
                .packageName("")
                .connection(getConnection())
                .build();


//        JavaGenerator.newGenerator(getConnection(), "public")
//                .createRestAPI("category");

    }
}