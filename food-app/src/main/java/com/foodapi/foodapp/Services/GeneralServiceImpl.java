package com.foodapi.foodapp.Services;

import com.foodapi.foodapp.dao.GeneralDao;
import com.foodapi.foodapp.models.ORMDeleteRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneralServiceImpl implements GeneralService {
    @Autowired
    GeneralDao generalDao;
    @Override
    public int deleteRecord(ORMDeleteRecord ormDeleteRecord) {
        return generalDao.deleteRecord(ormDeleteRecord);
    }
}
