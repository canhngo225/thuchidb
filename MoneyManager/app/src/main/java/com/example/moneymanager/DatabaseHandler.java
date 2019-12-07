package com.example.moneymanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    static final String DATABASE_NAME="moneymanager";
    static final int DATABASE_VERSION=1;
    static final String TABLE_NAME="khoanthu";
    static final String TABLE_NAME2="khoanchi";
    static  final String NOIDUNGTHU="noidung";
    static  final String SOTIENTHU="sotien";
    static  final String STTT="stt";
    static  final String NOIDUNGCHI="noidung";
    static  final String SOTIENCHI="sotien";
    static  final String STTC="stt";

    private static Context context;
    private static Context context2;
    static SQLiteDatabase db;
    SQLiteDatabase db1;
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + NOIDUNGTHU + " text," + SOTIENTHU + " text);"
        );
        db.execSQL("create table " + TABLE_NAME2 + " ( " + NOIDUNGCHI + " text," + SOTIENCHI + " text);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public List<KhoanThuChi> getAllKT() {
        List<KhoanThuChi> list  = new ArrayList<KhoanThuChi>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do { KhoanThuChi khoanThuChi = new KhoanThuChi();
                khoanThuChi.setmNoiDung(cursor.getString(0));
                khoanThuChi.setmSoTien(cursor.getString(1));
                list.add(khoanThuChi);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
    public List<KhoanThuChi> getAllKC() {
        List<KhoanThuChi> list  = new ArrayList<KhoanThuChi>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME2 ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do { KhoanThuChi khoanThuChi = new KhoanThuChi();
                khoanThuChi.setmNoiDung(cursor.getString(0));
                khoanThuChi.setmSoTien(cursor.getString(1));
                list.add(khoanThuChi);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;

    }
    public void themkhoanthu(KhoanThuChi khoanThuChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOIDUNGTHU, khoanThuChi.getmNoiDung());
        cv.put(SOTIENTHU, khoanThuChi.getmSoTien());
        db.insert(TABLE_NAME, null, cv);
        db.close();
    }


    public void xoathu(KhoanThuChi khoanThuChi) {
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + NOIDUNGTHU
                + "='" + khoanThuChi.getmNoiDung() + "'" + " AND " + SOTIENTHU
                + " = '" + khoanThuChi.getmSoTien() + "'");
        db.close();
    }

    public long themkhoanchi(KhoanThuChi khoanThuChi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NOIDUNGCHI, khoanThuChi.getmNoiDung());
        cv.put(SOTIENCHI, khoanThuChi.getmSoTien());
        return db.insert(TABLE_NAME2, null, cv);
    }

    public void xoachi(KhoanThuChi khoanThuChi) {
        db.execSQL("DELETE FROM " + TABLE_NAME2 + " WHERE " + NOIDUNGCHI
                + "='" + khoanThuChi.getmNoiDung() + "'" + " AND " + SOTIENCHI
                + " = '" + khoanThuChi.getmSoTien()  + "'");
        db.close();
    }


    public List<String> taikhoanthu(KhoanThuChi khoanThuChi) {
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT sum ( " + SOTIENTHU + " ) FROM "
                + TABLE_NAME ;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            if (cursor != null & cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;

    }
    public List<String> taikhoanchi(KhoanThuChi khoanThuChi) {
        List<String> list = new ArrayList<String>();
        String selectQuery = "SELECT sum ( " + SOTIENCHI + " ) FROM "
                + TABLE_NAME2 ;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            if (cursor != null & cursor.moveToFirst()) {
                do {
                    list.add(cursor.getString(0));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return list;

    }


}
