package com.silent.samurai;

import schemacrawler.schemacrawler.SchemaCrawlerException;

import java.sql.Connection;
import java.sql.SQLException;

public class JavaGeneratorBuilder {
    private String schema;
    private String projectRoot;
    private String packageName;
    private Connection connection;

    public static JavaGeneratorBuilder builder() {
        return new JavaGeneratorBuilder();
    }


    public JavaGeneratorBuilder schema(String schema) {
        this.schema = schema;
        return this;
    }

    public JavaGeneratorBuilder projectRoot(String projectRoot) {
        this.projectRoot = projectRoot;
        return this;
    }

    public JavaGeneratorBuilder packageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public JavaGeneratorBuilder connection(Connection connection) {
        this.connection = connection;
        return this;
    }


    //Return the finally constructed object
    public JavaGenerator build() throws SQLException, SchemaCrawlerException {
        validate();
        return new JavaGenerator(
                this.projectRoot,
                this.packageName,
                this.connection,
                this.schema
        );
    }

    private void validate() {
        assert this.projectRoot != null;
        assert this.packageName != null;
    }

}