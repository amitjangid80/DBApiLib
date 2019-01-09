package com.amit.db;

/**
 * Created by AMIT JANGID on 04/01/2019.
**/
public class DbDataTypes
{
    private String type = "";

    // Data Types
    private String TEXT = " TEXT ";
    private String REAL = " REAL ";
    private String BLOB = " BLOB ";
    private String INTEGER = " INTEGER ";

    // constrains
    private String asUnique = " UNIQUE ";
    private String notNull = " NOT NULL ";

    // Data types setters
    public DbDataTypes text()
    {
        type += TEXT;
        return this;
    }

    public DbDataTypes integer()
    {
        type += INTEGER;
        return this;
    }

    public DbDataTypes blob()
    {
        type += BLOB;
        return this;
    }

    public DbDataTypes real()
    {
        type += REAL;
        return this;
    }

    // constrains setters
    public DbDataTypes unique()
    {
        type += asUnique;
        return this;
    }

    public DbDataTypes notNull()
    {
        type += notNull;
        return this;
    }

    public String done()
    {
        return type;
    }
}
