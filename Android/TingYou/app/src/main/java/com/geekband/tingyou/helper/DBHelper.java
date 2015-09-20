package com.geekband.tingyou.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.geekband.tingyou.Env;

/**
 * Database to store the detail of the scenes by SQLite
 */
public class DBHelper extends SQLiteOpenHelper {

    private Context mContext;
    public static final String DATABASE_NAME = "myDatabase.db";
    public static final String DATABASE_TABLE = "SceneDetail";
    public static final int DATABASE_VERSION = 1;


    public static final String DATABASE_CREATE = "create table " + DATABASE_TABLE + " (" +
                                    Env.KEY_ID + " integer primary key autoincrement, " +
                                    Env.KEY_PROVINCE_COLUMN + " text not null, " +
                                    Env.KEY_CITY_COLUMN + " text, " +
                                    Env.KEY_SCENE_COLUMN + " text not null, " +
                                    Env.KEY_LEVEL_COLUMN + " text not null, " +
                                    Env.KEY_VOICE_COLUMN + " integer, " +
                                    Env.KEY_KNOWLEDGE_COLUMN + " integer, " +
                                    Env.KEY_BOUGHT_COLUMN + " integer);";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        Log.i("TravisDebug", "Table created inside DBHelper");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("TravisDebug", "Upgrading from version " + oldVersion +" to " + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}
