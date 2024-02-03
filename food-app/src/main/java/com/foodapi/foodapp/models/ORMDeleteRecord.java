package com.foodapi.foodapp.models;

public class ORMDeleteRecord {
    String table_name;
    String column_name;
    String column_value;
    String modified_user;
    String system_ip;

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getColumn_value() {
        return column_value;
    }

    public void setColumn_value(String column_value) {
        this.column_value = column_value;
    }

    public String getModified_user() {
        return modified_user;
    }

    public void setModified_user(String modified_user) {
        this.modified_user = modified_user;
    }

    public String getSystem_ip() {
        return system_ip;
    }

    public void setSystem_ip(String system_ip) {
        this.system_ip = system_ip;
    }


  }
