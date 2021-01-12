package com.x2021.mycloud.multidatasoucexa.holder;


import com.x2021.mycloud.multidatasoucexa.type.DatabaseType;

/**
 * @author cui
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> type = ThreadLocal.withInitial(() -> DatabaseType.DB1);


    public static void set(DatabaseType databaseType) {
        type.set(databaseType);
    }

    public static DatabaseType get() {
        return type.get();
    }

    public static void remove() {
        type.remove();
    }
}
