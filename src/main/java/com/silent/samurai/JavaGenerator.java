package com.silent.samurai;

import com.silent.samurai.generators.BuilderPatternGenerator;
import com.silent.samurai.generators.GenerateDtoClass;
import com.silent.samurai.utils.DatabaseUtil;
import org.apache.log4j.Logger;
import schemacrawler.schemacrawler.SchemaCrawlerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JavaGenerator {

    static final Logger logger = Logger.getLogger(JavaGenerator.class);

    private final String projectRoot;
    private final String packageName;
    private final Connection connection;
    private final String schema;

    public JavaGenerator(String projectRoot, String packageName, Connection connection, String schema) throws SQLException, SchemaCrawlerException {
        this.projectRoot = projectRoot;
        this.packageName = packageName;
        this.connection = connection;
        this.schema = schema;
        if (connection != null) {
            DatabaseUtil.buildCatalog(connection, schema);
        }
    }

    public void createRestAPI(String entityTableName) {

    }

    public void makeBuilderOf(Class<?> clazz) throws IOException {
        List<String> generatedClasses = BuilderPatternGenerator.makeBuilderOf(clazz, projectRoot);
        logger.info(generatedClasses);
    }

    public void makeDto(Class<?> entity, String suffix, String tableName) throws IOException {
        List<String> generatedClasses = GenerateDtoClass.make(entity, suffix, true, tableName, projectRoot);
        logger.info(generatedClasses);
    }

    public void makeDto(Class<?> entity, String suffix) throws IOException {
        List<String> generatedClasses = GenerateDtoClass.make(entity, suffix, false, null, projectRoot);
        logger.info(generatedClasses);
    }
}
