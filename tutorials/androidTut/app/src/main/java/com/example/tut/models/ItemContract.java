package com.example.tut.models;

import android.provider.BaseColumns;

import com.example.tut.R;

public final class ItemContract {
    private static final    String TEXT_TYPE = " TEXT";
    private static final    String INT_TYPE = " INT";
    private static final    String REAL_TYPE = " REAL";

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ItemEntry.TABLE_NAME + " ("+
                    ItemEntry._ID + INT_TYPE + " PRIMARY KEY, " +
                    ItemEntry.NAME + TEXT_TYPE + " NOT NULL, " +
                    ItemEntry.PRICE + REAL_TYPE + " NOT NULL, " +
                    ItemEntry.IMAGE + INT_TYPE +
                    ItemEntry.TIME + " DATETIME DEFAULT CURRENT_TIMESTAMP"
            + ");";

    public static String CREATE_ITEMS(){
        String str = "";
        str += "INSERT INTO " + ItemEntry.TABLE_NAME + "(name,price,image) " +
                "VALUES('Dell Laptop', 2000.00, " + R.drawable.laptop+"),";
        str += " ('iPhone', 400.00, " + R.drawable.iphone + "),";
        str += " ('iPad', 300.00, " + R.drawable.ipad + "),";
        str += " ('LCD Television', 5000.00, " + R.drawable.tv + ");";
        return str;
    }

    public static String DROP_TABLE(){
        String str = "DROP TABLE " + ItemEntry.TABLE_NAME + ";";
        return str;
    }

    public static abstract class ItemEntry implements BaseColumns{
        public static final String TABLE_NAME = "items";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String IMAGE = "image";
        public static final String TIME = "timecreated";
    }
}
