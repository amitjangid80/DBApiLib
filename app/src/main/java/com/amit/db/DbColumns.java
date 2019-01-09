package com.amit.db;

/**
 * Created by AMIT JANGID on 04/01/2019.
**/
public class DbColumns
{
    String mColumnName, mColumnDataType;

    public DbColumns(String columnName, String columnDataType)
    {
        this.mColumnName = columnName;
        this.mColumnDataType = columnDataType;
    }

    public DbColumns(String columnName, String[] columnDataTypes)
    {
        StringBuilder finalDataType = new StringBuilder();
        this.mColumnName = columnName;

        for (int i = 0; i < columnDataTypes.length; i++)
        {
            if (!columnDataTypes[i].startsWith(" "))
            {
                columnDataTypes[i] = " " + columnDataTypes[i];
            }

            if (!columnDataTypes[i].endsWith(" "))
            {
                columnDataTypes[i] = columnDataTypes[i] + " ";
            }

            finalDataType.append(columnDataTypes[i].toUpperCase());
        }

        this.mColumnDataType = finalDataType.toString();
    }
}
