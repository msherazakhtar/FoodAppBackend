package com.foodapi.foodapp.dao;

import com.foodapi.foodapp.db.DBOperations;
import com.foodapi.foodapp.models.ORMDeleteRecord;
import com.foodapi.foodapp.models.ParamList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GeneralDaoImpl implements GeneralDao {
    @Autowired
    DBOperations db;
    @Override
    public int deleteRecord(ORMDeleteRecord ormDeleteRecord) {
        List<ParamList> paramList = new ArrayList<>();
        paramList.add(new ParamList("table_name",ormDeleteRecord.getTable_name(),String.class));
        paramList.add(new ParamList("column_name",ormDeleteRecord.getColumn_name(),String.class));
        paramList.add(new ParamList("column_value",ormDeleteRecord.getColumn_value(),String.class));
        paramList.add(new ParamList("modified_user",ormDeleteRecord.getModified_user(),String.class));
        paramList.add(new ParamList("system_ip", ormDeleteRecord.getSystem_ip(), String.class));

        return db.executeUpdateProcedure("spDeleteRecord",paramList);



    }
   
}
