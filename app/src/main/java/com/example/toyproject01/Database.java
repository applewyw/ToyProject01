package com.example.toyproject01;

import android.provider.BaseColumns;

public final class Database {
    private Database() {
    }

    public static class DatabaseEntity implements BaseColumns {
        public static final String TABLE_NAME = "TBL_VISITCITY";
        public static final String COLUMN_NAME_CUSTID = "CUST_ID";
        public static final String COLUMN_NAME_LANGCD = "LANG_CD";
        public static final String COLUMN_NAME_CITYCD = "CITY_CD";
        public static final String COLUMN_NAME_VISITYN = "VISIT_YN";
    }
}
