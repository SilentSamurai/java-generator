package com.silent.samurai.service;

import com.sun.tools.javac.util.Assert;
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
        ).buildCatalog();
    }

    private void validate() {
        Assert.checkNonNull(this.schema);
        Assert.checkNonNull(this.projectRoot);
        Assert.checkNonNull(this.packageName);
        Assert.checkNonNull(this.connection);
    }

}