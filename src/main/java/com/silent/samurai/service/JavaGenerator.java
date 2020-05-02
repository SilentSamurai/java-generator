package com.silent.samurai.service;

import com.silent.samurai.utils.DatabaseUtil;
import org.apache.log4j.Logger;
import schemacrawler.schemacrawler.SchemaCrawlerException;

import java.sql.Connection;
import java.sql.SQLException;

public class JavaGenerator {

    static final Logger logger = Logger.getLogger(JavaGenerator.class);

    private final String projectRoot;
    private final String packageName;
    private final Connection connection;
    private final String schema;

    public JavaGenerator(String projectRoot, String packageName, Connection connection, String schema) {
        this.projectRoot = projectRoot;
        this.packageName = packageName;
        this.connection = connection;
        this.schema = schema;
    }

    JavaGenerator buildCatalog() throws SQLException, SchemaCrawlerException {
        DatabaseUtil.buildCatalog(connection, schema);
        return this;
    }

    public void createRestAPI(String entityTableName) {

    }
}
