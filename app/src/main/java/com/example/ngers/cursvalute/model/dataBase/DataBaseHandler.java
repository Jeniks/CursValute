package com.example.ngers.cursvalute.model.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ngers.cursvalute.model.ValCurs;
import com.example.ngers.cursvalute.model.Valute;


/**
 * Created by ngers on 15.10.16.
 */

public class DataBaseHandler extends SQLiteOpenHelper implements IDataBaseHandler {

    private static final int DATABASE_VERSION = 20;
    private static final String DATABASE_NAME = "valCurs";
    private static final String TABLE_VALCURS = "valuteCurs";

    private static final String KEY_DATE = "date";

    private static final String TABLE_VALUTE = "valute";
    private static final String KEY_NUMCODE = "numCode";
    private static final String KEY_CHARCODE = "charCode";
    private static final String KEY_NOMINAL = "nominal";
    private static final String KEY_VALUE = "value";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_VALCURS_TABLE = "CREATE TABLE " + TABLE_VALCURS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_DATE + " TEXT, "
                + KEY_NAME + " TEXT"
                + ");";
        sqLiteDatabase.execSQL(CREATE_VALCURS_TABLE);

        String CREATE_VALUTE_TABLE = "CREATE TABLE " + TABLE_VALUTE + " ("
                + KEY_ID + " TEXT PRIMARY KEY, "
                + KEY_NUMCODE + " INTEGER, "
                + KEY_CHARCODE + " TEXT, "
                + KEY_NOMINAL + " INTEGER, "
                + KEY_NAME + " TEXT, "
                + KEY_VALUE + " DOUBLE"
                + ");";
        sqLiteDatabase.execSQL(CREATE_VALUTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void addValcurs(ValCurs valCurs) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, valCurs.getId());
        values.put(KEY_NAME, valCurs.getName());
        values.put(KEY_DATE, valCurs.getDate());

        db.insert(TABLE_VALCURS, null, values);

        for (Valute valute : valCurs.getValutes()) {
            values.clear();
            values.put(KEY_ID, valute.getId());
            values.put(KEY_NUMCODE, valute.getNumCode());
            values.put(KEY_CHARCODE, valute.getCharCode());
            values.put(KEY_NOMINAL, valute.getNominal());
            values.put(KEY_NAME, valute.getName());
            values.put(KEY_VALUE, valute.getValue());
            db.insert(TABLE_VALUTE, null, values);
        }

        db.close();
    }

    @Override
    public ValCurs getValcurs() {
        ValCurs valCurs;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCurs = db.query(TABLE_VALCURS, null, null, null, null, null, null);
        if (cursorCurs != null) {
            cursorCurs.moveToFirst();
            valCurs = new ValCurs(Integer.parseInt(cursorCurs.getString(0)), cursorCurs.getString(1), cursorCurs.getString(2));
            cursorCurs.close();
        } else {
            cursorCurs.close();
            return null;
        }


        Cursor cursorValuta = db.query(TABLE_VALUTE, null, null, null, null, null, null);
        if (cursorValuta != null) {
            cursorValuta.moveToFirst();
            do {
                valCurs.getValutes().add(new Valute(cursorValuta.getString(0),
                        Integer.parseInt(cursorValuta.getString(1)),
                        cursorValuta.getString(2),
                        Integer.parseInt(cursorValuta.getString(3)),
                        cursorValuta.getString(4),
                        cursorValuta.getString(5)));
            } while (cursorValuta.moveToNext());
        }

        cursorValuta.close();

        return valCurs;
    }

    @Override
    public void deleteValCurs() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_VALCURS, null, null);
        db.delete(TABLE_VALUTE, null, null);
        db.close();
    }
}
