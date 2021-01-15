package com.x2021.mycloud.multidatasoucexa.dao;


import com.x2021.mycloud.multidatasoucexa.entity.Log;
import com.x2021.mycloud.multidatasoucexa.mapper.db1.LogMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogDao implements DB1Selector {

    @Autowired
    private LogMapper logMapper;

    public List<Log> listAll() {
        return logMapper.findAll();
    }

    public Log getLog(int id) {
        return logMapper.getLog(id);
    }

    public Long insertOne(Log log) {
        logMapper.insertSelective(log);
        return log.getId();
    }
}
