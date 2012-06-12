/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider;

import java.sql.Types;

import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;

/**
 *
 * @author TMSANCHEZ
 */
public class MSAccessDialect extends Dialect {

    public MSAccessDialect() {
        super();
        registerColumnType(Types.BIT, "BIT");
        registerColumnType(Types.BIGINT, "INTEGER");
        registerColumnType(Types.SMALLINT, "SMALLINT");
        registerColumnType(Types.TINYINT, "BYTE");
        registerColumnType(Types.INTEGER, "INTEGER");
        registerColumnType(Types.CHAR, "VARCHAR(1)");
        registerColumnType(Types.VARCHAR, "VARCHAR($l)");
        registerColumnType(Types.FLOAT, "DOUBLE");
        registerColumnType(Types.DOUBLE, "DOUBLE");
        registerColumnType(Types.DATE, "DATETIME");
        registerColumnType(Types.TIME, "DATETIME");
        registerColumnType(Types.TIMESTAMP, "DATETIME");
        registerColumnType(Types.VARBINARY, "VARBINARY($l)");
        registerColumnType(Types.NUMERIC, "NUMERIC");

        getDefaultProperties().setProperty(Environment.STATEMENT_BATCH_SIZE, NO_BATCH);
    }

    public String getIdentityColumnString() {
//return " counter ";
        return "not null auto_number";
    }

    public String getIdentitySelectString() {
        return "select @@IDENTITY";
    }

    /**
     * Returns for update syntax for access, which is non-existant, so I *think*
     * we return an empty string...
     * @return String an beautifully constructed empty string...
     */
    public String getForUpdateString() {
        return "";
    }
}
