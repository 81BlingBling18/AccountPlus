package com.happycoding.uniquehust.accountplus.database;

import android.provider.BaseColumns;


public final class AccountDatabaseContract {

    public static abstract class AccountEntry implements BaseColumns {
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_TYPE = "TYPE";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_YEAR = "year";
        public static final String COLUMN_NAME_MONTH = "month";
        public static final String COLUMN_NAME_DAY = "day";
        public static final String COLUMN_NAME_PIC_TIMESTAMP = "pic_timestamp";
        public static final String COLUMN_NAME_ICON_ID = "icon_id";
        public static final String COLUMN_NAME_ISINCOME = "is_income";
    }

}