package com.example.petshopapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class basket_dbHandler extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "basket_db";
    private static final String TABLE_Items = "basketItems";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PRICE = "price";

    public basket_dbHandler(Context context) { super(context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Items + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_PRICE + " INTEGER"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Items);
        onCreate(db);
    }

    void insertBasketItem(String name, Integer price) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_PRICE, price);

        long newRowId = db.insert(TABLE_Items, null, cValues);

        db.close();
    }

    public void deleteBasketItem(int itemId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Items, KEY_ID + " =?", new String[]{String.valueOf(itemId)});

        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> GetItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> itemList = new ArrayList<>();

        String query = "SELECT name, price FROM " + TABLE_Items;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            HashMap<String,String> item = new HashMap<>();
            item.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            item.put("price", cursor.getString(cursor.getColumnIndex(KEY_PRICE)));
            itemList.add(item);
        }

        return itemList;
    }

    @SuppressLint("Range")
    public ArrayList<Integer> getPrices() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Integer> priceList = new ArrayList<>();

        String query = "SELECT price FROM " + TABLE_Items;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            priceList.add(cursor.getInt(cursor.getColumnIndex(KEY_PRICE)));
        }

        return priceList;
    }

    public void ResetDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        String clearDBQuery = "DELETE FROM " + TABLE_Items;
        db.execSQL(clearDBQuery);
    }
}
