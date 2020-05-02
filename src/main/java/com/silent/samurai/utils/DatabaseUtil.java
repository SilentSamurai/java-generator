package com.silent.samurai.utils;

import org.apache.log4j.Logger;
import schemacrawler.inclusionrule.RegularExpressionInclusionRule;
import schemacrawler.schema.Catalog;
import schemacrawler.schema.Schema;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaCrawlerOptionsBuilder;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.utility.SchemaCrawlerUtility;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseUtil {

    static final Logger logger = Logger.getLogger(DatabaseUtil.class);
    private static DatabaseUtil instance;
    private Catalog catalog;

    private static SchemaCrawlerOptions getOptions(String schemaName) {
        final SchemaCrawlerOptionsBuilder optionsBuilder = SchemaCrawlerOptionsBuilder.builder();
        optionsBuilder.withSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
        if (schemaName != null && !schemaName.isEmpty()) {
            optionsBuilder.includeSchemas(new RegularExpressionInclusionRule(schemaName));
        }
        // Set what details are required in the schema - this affects the
        // time taken to crawl the schema


        //                        .includeTables(tableFullName -> !tableFullName.contains("books"));
        return optionsBuilder.toOptions();
    }

    public static void buildCatalog(DataSource dataSource, String schemaName) throws SQLException, SchemaCrawlerException {
        buildCatalog(dataSource.getConnection(), schemaName);
    }

    public static void buildCatalog(Connection connection, String schemaName) throws SQLException, SchemaCrawlerException {
        if (instance == null) {
            instance = new DatabaseUtil();
            instance.catalog = SchemaCrawlerUtility.getCatalog(connection, getOptions(schemaName));
        }
    }

    public static DatabaseUtil getInstance() {
        return instance;
    }

    public Table getTable(String tableName) {
        Schema schema = catalog.getSchemas().stream().findAny().orElse(null);
        return catalog.getTables(schema).parallelStream().filter(tbl -> tbl.getName().equals(tableName))
                .findAny().orElse(null);
    }

}
