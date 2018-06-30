package com.example.puneeth.lovecalculatorteen.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.puneeth.lovecalculatorteen.Modal.QuoteModal;
import java.util.ArrayList;
import java.util.List;

public class SqliteHelper extends SQLiteOpenHelper {

    private Context context;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "quotes_generator.db";
    private static final String TABLE_NAME = "quotes_table";

//    private static final String KEY_ID = "";
    private static final String QUOTE = "quote";
    private static final String AUTHOR = "quthor";
    private static final String CATEGORY = "category";

    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_QUOTE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" /*+ KEY_ID + " INTEGER PRIMARY KEY,"*/ + QUOTE + " TEXT,"
        + AUTHOR + " TEXT," + CATEGORY + " TEXT" + ")";
        db.execSQL(CREATE_QUOTE_TABLE);
        Log.d("Table:", "Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addTable(QuoteModal quoteModal) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUOTE, quoteModal.getQuote());
        values.put(AUTHOR, quoteModal.getAuthor());
        values.put(CATEGORY, quoteModal.getCategory());

        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d("Added to:", "Table");
    }

    public List<QuoteModal> getDetails() {

        List<QuoteModal> taskModalList = new ArrayList<QuoteModal>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                QuoteModal quoteModal = new QuoteModal();
                quoteModal.setQuote(cursor.getString(0));
                quoteModal.setAuthor(cursor.getString(1));
                quoteModal.setCategory(cursor.getString(2));
                taskModalList.add(quoteModal);
            } while (cursor.moveToNext());
        }
        return taskModalList;
    }
}
