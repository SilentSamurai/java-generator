package com.silent.samurai;

import com.silent.samurai.generators.*;
import com.silent.samurai.utils.DatabaseUtil;
import org.apache.log4j.Logger;
import schemacrawler.schemacrawler.SchemaCrawlerException;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class JavaGenerator {

    static final Logger logger = Logger.getLogger(JavaGenerator.class);

    private final String projectRoot;
    private final String packageName;
    private final Connection connection;
    private final String schema;

    public JavaGenerator(String projectRoot, String packageName, Connection connection, String schema) throws SchemaCrawlerException {
        this.projectRoot = projectRoot;
        this.packageName = packageName;
        this.connection = connection;
        this.schema = schema;
        if (connection != null) {
            DatabaseUtil.buildCatalog(connection, schema);
        }
    }

    private void needDB() throws RuntimeException {
        if (this.connection == null) {
            throw new RuntimeException("Database Connection is Required for this generation");
        }
    }

    public void createRestAPI(String entityTableName) {

    }

    public void makeBuilderOf(Class<?> clazz) throws IOException {
        List<String> generatedClasses = BuilderPatternGenerator.makeBuilderOf(clazz, projectRoot);
        logger.info(generatedClasses);
    }

    public void makeDtoWithValidation(Class<?> entity, String suffix, String tableName) throws IOException {
        this.needDB();
        List<String> generatedClasses = DtoClassGenerator.make(entity, suffix, true, tableName, this);
        logger.info(generatedClasses);
    }

    public void makeDto(Class<?> entity, String suffix) throws IOException {
        List<String> generatedClasses = DtoClassGenerator.make(entity, suffix, false, null, this);
        logger.info(generatedClasses);
    }


    public void makeHttpClient(Class<?> entity, String pathFromRoot) throws IOException {
        List<String> generatedClasses = HttpClientGenerator.make(entity, pathFromRoot, this);
        logger.info(generatedClasses);
    }

    public void makeRepository(Class<?> entity, Class<?> idClass, String tableName) throws IOException {
        this.needDB();
        List<String> generatedClasses = RepositoryGenerator.make(entity, idClass, tableName, this);
        logger.info(generatedClasses);
    }

    public void makeController(Class<?> entity) throws IOException {
        List<String> generatedClasses = ControllerGenerator.make(entity, this);
        logger.info(generatedClasses);
    }


    public String getProjectRoot() {
        return projectRoot;
    }

    public String getPackageName() {
        return packageName;
    }

    public Connection getConnection() {
        return connection;
    }

    public String getSchema() {
        return schema;
    }
}
