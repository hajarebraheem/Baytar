package uqu.graduationtwenty.baytar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class database extends SQLiteOpenHelper {
    private SQLiteDatabase myDataBase;
    private final Context myContext;
    private static String DB_PATH = "/data/data/uqu.graduationtwenty.example/databases/";
    private static String DB_NAME = "newdb.db";

    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String DRUG = "drug";
        public static final String AGE = "age";
        public static final String AREA = "area";
        public static final String GENDER = "gender";

    }

    static final String SQL_USER_CREATE_ENTRIES =
            "CREATE TABLE " + UserEntry.TABLE_NAME + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserEntry.DRUG + " TEXT," +
                    UserEntry.AGE + " TEXT," +
                    UserEntry.AREA + " TEXT," +
                    UserEntry.GENDER + " TEXT)";


    public database(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = context.getFilesDir().getPath();
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbFile = new File(myPath);
            if (!dbFile.exists()) {
                copyDataBase();
            }
        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("Path 1", DB_PATH);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_USER_CREATE_ENTRIES);
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copy database");
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            try {
                copyDataBase();
                onCreate(db);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copy database");
            }
        }
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException e) {

        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }

    private void copyDataBase() throws IOException {
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {
        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    public Cursor query(String table, String selection) {
        return myDataBase.query(table, null, selection, null, null, null, null);
    }

    public void insertDrugInfoSearch(String drugName, String age, String gender, String area) {
        ContentValues values = new ContentValues();
        values.put(UserEntry.DRUG, drugName);
        values.put(UserEntry.AGE, age);
        values.put(UserEntry.GENDER, gender);
        values.put(UserEntry.AREA, area);

        SQLiteDatabase db = getWritableDatabase();
        try {
            long rowID = db.insert(UserEntry.TABLE_NAME, null, values);
            Log.i("InsertedRowID", "row instered in to table, " + rowID);
        } finally {
            db.close();
        }
    }

    public List<UserSearch> getAllUserSearches() {
        List<UserSearch> searches = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try {
            // returns all rows as SELECT * FROM USER;
            Cursor cursor = db.query(UserEntry.TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                UserSearch userSearch = new UserSearch(cursor.getString(cursor.getColumnIndex(UserEntry.DRUG)),
                        cursor.getString(cursor.getColumnIndex(UserEntry.AGE)),
                        cursor.getString(cursor.getColumnIndex(UserEntry.GENDER)),
                        cursor.getString(cursor.getColumnIndex(UserEntry.AREA)));
                searches.add(userSearch);
            }
            cursor.close();
        } finally {
            db.close();
        }
        return searches;
    }


    public void insertInteraction(String interaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("interactions", interaction);
        db.insert("user", null, content);
    }
}