package com.x2021.mycloud.multidatasoucexa.holder;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *  动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.get();
    }
}
