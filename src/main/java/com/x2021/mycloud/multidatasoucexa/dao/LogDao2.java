package com.x2021.mycloud.multidatasoucexa.dao;


import com.x2021.mycloud.multidatasoucexa.entity.Log;
import com.x2021.mycloud.multidatasoucexa.holder.DatabaseContextHolder;
import com.x2021.mycloud.multidatasoucexa.mapper.db2.LogMapper2;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogDao2 implements DB2Selector {

    @Autowired
    private LogMapper2 logMapper;

    public Log getLog(int id) {
        return logMapper.getLog(id);
    }

    public int insertOne(Log log) {
        log.setDescribe(DatabaseContextHolder.get().name());
        logMapper.insertSelective(log);
        return Optional.ofNullable(log.getId()).map(Long::intValue).orElse(1);
    }
}
