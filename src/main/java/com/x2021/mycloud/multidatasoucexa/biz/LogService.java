package com.x2021.mycloud.multidatasoucexa.biz;

import com.x2021.mycloud.multidatasoucexa.dao.LogDao;
import com.x2021.mycloud.multidatasoucexa.dao.LogDao2;
import com.x2021.mycloud.multidatasoucexa.entity.Log;
import com.x2021.mycloud.multidatasoucexa.holder.DatabaseContextHolder;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogService {

    @Autowired
    private LogDao logDao;

    @Autowired
    private LogDao2 logDao2;

    public List<Log> listAll() {
        return logDao.listAll();
    }

    public Long insert() {
        Log log = Log.builder().content("rxxx" + LocalDateTime.now().toString())
                .describe(DatabaseContextHolder.get().name()).build();
        return logDao.insertOne(log);
    }

    public int insert2() {
        Log log = Log.builder().content("rxxx" + LocalDateTime.now().toString())
                .describe(DatabaseContextHolder.get().name()).build();
        return logDao2.insertOne(log);
    }

    public Log selectOne() {
        return logDao.getLog(1);
    }

    public Log selectOne2() {
        return logDao2.getLog(1);
    }


    @Transactional(rollbackFor = Exception.class)
    public int transaction() {
        int c=0;
        Log log = Log.builder().content("rxxx" + LocalDateTime.now().toString())
                .describe(DatabaseContextHolder.get().name()).build();
        logDao.insertOne(log);
        c = logDao2.insertOne(log);
        int b=1; int a=1;
        try {
            c = a / b;
        } catch (Exception e) {
            throw  new RuntimeException("xxxxxx");
        }
        return c;
    }
}
