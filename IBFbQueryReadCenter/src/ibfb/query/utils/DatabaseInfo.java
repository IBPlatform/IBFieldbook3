
package ibfb.query.utils;


public class DatabaseInfo {

    private DatabaseType databaseType;
    private String driverName;
    private String hibernateDialect;
    private String jdbcPrefix;
    private String serverName;
    private String port;
    private String databaseName;
    private String userName;
    private String password;

    public DatabaseInfo() {
    }

    public DatabaseInfo(DatabaseType databaseType) {
        initFields();
        this.databaseType = databaseType;
        setDatabaseType(databaseType);
    }

    public DatabaseInfo(String driverName, String hibernateDialect, String jdbcPrefix) {
        initFields();
        this.driverName = driverName;
        this.hibernateDialect = hibernateDialect;
        this.jdbcPrefix = jdbcPrefix;
    }

    public DatabaseInfo(DatabaseType databaseType, String serverName, String port, String databaseName, String userName, String password) {
        initFields();
        this.databaseType = databaseType;
        this.serverName = serverName;
        this.port = port;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;

        setDatabaseType(databaseType);
    }

    private void initFields() {
        driverName = "";
        hibernateDialect = "";
        jdbcPrefix = "";
        serverName = "";
        port = "";
        databaseName = "";
        userName = "";
        password = "";

    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getHibernateDialect() {
        return hibernateDialect;
    }

    public void setHibernateDialect(String hibernateDialect) {
        this.hibernateDialect = hibernateDialect;
    }

    public String getJdbcPrefix() {
        return jdbcPrefix;
    }

    public void setJdbcPrefix(String jdbcPrefix) {
        this.jdbcPrefix = jdbcPrefix;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DatabaseType getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(DatabaseType databaseType) {
        this.databaseType = databaseType;
        switch (databaseType) {
            case ACCESS:
                jdbcPrefix = "jdbc:odbc:";
                driverName = "sun.jdbc.odbc.JdbcOdbcDriver";
                break;
            case MYSQL:
                jdbcPrefix = "jdbc:mysql://";
                driverName = "com.mysql.jdbc.Driver";
                break;
        }
    }

    public String getUrl() {
        StringBuilder url = new StringBuilder();
        url.append(jdbcPrefix);

        if (databaseType.equals(DatabaseType.MYSQL)) {
            url.append(serverName);
            url.append(":").append(port);
            url.append("/").append(databaseName);
        } else {
            url.append(databaseName);
        }
        return url.toString();
    }
}
