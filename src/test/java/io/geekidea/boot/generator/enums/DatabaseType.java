package io.geekidea.boot.generator.enums;

/**
 * @author geekidea
 * @date 2022/7/3
 **/
public enum DatabaseType {
    MYSQL("mysql", "MySql数据库"),
    MARIADB("mariadb", "MariaDB数据库"),
    ORACLE("oracle", "Oracle11g及以下数据库(高版本推荐使用ORACLE_NEW)"),
    ORACLE_12C("oracle12c", "Oracle12c+数据库"),
    POSTGRE_SQL("postgresql", "Postgres数据库"),
    SQL_SERVER2005("sqlserver2005", "SQLServer2005数据库"),
    SQL_SERVER("sqlserver", "SQLServer数据库");

    private final String db;
    private final String desc;

    private DatabaseType(final String db, final String desc) {
        this.db = db;
        this.desc = desc;
    }

    public static DatabaseType getDatabaseType(String dbType) {
        for (DatabaseType databaseType : values()) {
            if (databaseType.getDb().equals(dbType)) {
                return databaseType;
            }
        }
        return MYSQL;
    }

    public String getDb() {
        return this.db;
    }

    public String getDesc() {
        return this.desc;
    }
}
